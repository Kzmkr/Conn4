package org.example.input;


import org.example.models.Board;
import org.example.models.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class FileTest {

    private File fileHandler;
    private Board board;
    private Player player;
    private String testFilePath;

    @BeforeEach
    public void setUp() {
        fileHandler = new File();
        board = new Board(10, 10);
        player = new Player(1, "Test Player", 9, "X",true);
        testFilePath = "test_game.xml";
    }

    @Test
    public void testWriteAndRead() {
        // Write to file
        boolean writeResult = fileHandler.write(testFilePath, board, player);
        assertTrue(writeResult);

        // Read from file
        Object[] result = fileHandler.read(testFilePath);
        assertNotNull(result);
        assertEquals(2, result.length);

        Board readBoard = (Board) result[0];
        Player readPlayer = (Player) result[1];

        assertEquals(board.getH(), readBoard.getH());
        assertEquals(board.getW(), readBoard.getW());
        assertEquals(player.getName(), readPlayer.getName());
        assertEquals(player.getColor(), readPlayer.getColor());
        assertEquals(player.getSymbol(), readPlayer.getSymbol());
    }




    @AfterEach
    public void tearDown() {
        java.io.File file = new java.io.File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
