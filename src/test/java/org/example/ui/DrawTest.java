package org.example.ui;

import org.example.models.Board;
import org.example.models.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrawTest {

    private Draw draw;
    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        board = new Board(10, 10);
        player1 = new Player(1, "Player 1", 9, "X",true);
        player2 = new Player(2, "Player 2", 11, "O",false);
        draw = new Draw(board, player1, player2);
    }

    @Test
    public void testDrawBoard() {
        String actual = draw.draw_Board(-1,-1, player1);
        String expected =
                "\u001B[30;47m  Save  \u001B[0m\u001B[30;47m  Open  \u001B[0m\u001B[30;47m  Restart  \u001B[0m\u001B[30;47m  Exit  \u001B[0m\n\n" +
                        "│ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ 10│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n";
        assertEquals(expected, actual);
    }
    @Test
    public void testDrawBoardWithTopSelection() {
        String actual = draw.draw_Board(1,-1, player1);
        String expected =
                "\u001B[30;47m  Save  \u001B[0m\u001B[30;47m  Open  \u001B[0m\u001B[30;47m  Restart  \u001B[0m\u001B[30;47m  Exit  \u001B[0m\n\n" +
                        "│\u001B[30;101m 1 \u001B[0m│ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ 10│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n";


        assertEquals(expected, actual);
    }
    @Test
    public void testDrawBoardWithCellSelection() {
        String actual = draw.draw_Board(0,0, player1);
        String expected =
                "\u001B[30;47m  Save  \u001B[0m\u001B[30;47m  Open  \u001B[0m\u001B[30;47m  Restart  \u001B[0m\u001B[30;47m  Exit  \u001B[0m\n\n" +
                        "│ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ 10│\n" +
                        "│\u001B[30;101m(O)\u001B[0m│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n";


        assertEquals(expected, actual);
    }

    @Test
    public void testDrawBoardNotEmpty() {
        board.setI(0,9, player1.getId());
        String actual = draw.draw_Board(-1,-1, player1);
        String expected =
                "\u001B[30;47m  Save  \u001B[0m\u001B[30;47m  Open  \u001B[0m\u001B[30;47m  Restart  \u001B[0m\u001B[30;47m  Exit  \u001B[0m\n\n" +
                        "│ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ 10│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│( )│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n" +
                        "│\u001B[30;101m(O)\u001B[0m│( )│( )│( )│( )│( )│( )│( )│( )│( )│\n";


        assertEquals(expected, actual);
    }






}