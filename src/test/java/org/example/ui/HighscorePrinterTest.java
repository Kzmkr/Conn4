package org.example.ui;


import org.example.models.HighScore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighscorePrinterTest {

    private HighscorePrinter highscorePrinter;
    private HighScore hs;

    @BeforeEach
    void setUp() {
        highscorePrinter = new HighscorePrinter();
        hs = new HighScore();
    }


    @Test
    void renderWithNoScores() {
        String expected =
                        "┌────────┬───────┬────────┬─────────┐\r\n" +
                        "│  Name  │  Win  │  Lose  │  Ratio  │\r\n" +
                        "└────────┴───────┴────────┴─────────┘";
        assertEquals(expected, highscorePrinter.render(hs));
    }

    @Test
    void renderWithOneScore() {
        hs.addScore("Player1", 3, 1);
        String expected =
                "┌───────────┬───────┬────────┬─────────┐\r\n" +
                "│   Name    │  Win  │  Lose  │  Ratio  │\r\n" +
                "├───────────┼───────┼────────┼─────────┤\r\n" +
                "│  Player1  │   3   │   1    │  0,75   │\r\n" +
                "└───────────┴───────┴────────┴─────────┘";
        assertEquals(expected, highscorePrinter.render(hs));
    }

    @Test
    void renderWithMultipleScores() {
        hs.addScore("Player1", 3, 1);
        hs.addScore("Player2", 2, 2);
        String expected =
                "┌───────────┬───────┬────────┬─────────┐\r\n" +
                "│   Name    │  Win  │  Lose  │  Ratio  │\r\n" +
                "├───────────┼───────┼────────┼─────────┤\r\n" +
                "│  Player1  │   3   │   1    │  0,75   │\r\n" +
                "├───────────┼───────┼────────┼─────────┤\r\n" +
                "│  Player2  │   2   │   2    │  0,50   │\r\n" +
                "└───────────┴───────┴────────┴─────────┘";
        assertEquals(expected, highscorePrinter.render(hs));
    }

}