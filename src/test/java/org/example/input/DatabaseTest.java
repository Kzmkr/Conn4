package org.example.input;

import org.example.models.HighScore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database database;
    private final String dbUrl = "test.db";

    @BeforeEach
    public void setUp() {
        database = new Database();
        database.createNewTable(dbUrl);
    }

    @AfterEach
    public void tearDown() {
        File dbFile = new File(dbUrl);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }


    @Test
    public void testUpdateAndRead() {
        database.update("Player1", 5, 2);
        database.update("Player2", 3, 4);

        HighScore highScoreRead = database.read();
        assertNotNull(highScoreRead);
        assertEquals(2, highScoreRead.getSize());
        assertEquals("Player1", highScoreRead.getNames().get(0));
        assertEquals(5, highScoreRead.getWinScores().get(0));
        assertEquals(2, highScoreRead.getLoseScores().get(0));
        assertEquals("Player2", highScoreRead.getNames().get(1));
        assertEquals(3, highScoreRead.getWinScores().get(1));
        assertEquals(4, highScoreRead.getLoseScores().get(1));
    }

    @Test
    public void testInvalidFile() {
        database.update("Player1;", 5, 2);
        database.update("Player2", 3, 4);

        HighScore highScoreRead = database.read();
        assertNotNull(highScoreRead);
    }

    @Test
    public void testUpdateExistingPlayer() {
        database.update("Player1", 5, 2);
        database.update("Player1", 3, 1);

        HighScore highScore = database.read();
        assertNotNull(highScore);
        assertEquals(1, highScore.getNames().size());
        assertEquals("Player1", highScore.getNames().get(0));
        assertEquals(8, highScore.getWinScores().get(0));
        assertEquals(3, highScore.getLoseScores().get(0));
    }
}
