package org.example.input;

import org.example.models.Player;
import org.jline.terminal.Terminal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.File;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InAndOutTest {

    private InAndOutput inAndOut;


    @BeforeEach
    public void setUp() {
        inAndOut = new InAndOutput();


    }

    @AfterEach
    public void tearDown() {
        java.io.File dbFile = new File("test.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testIsNumeric() {
        assertTrue(InAndOutput.isNumeric("123"));
        assertFalse(InAndOutput.isNumeric("abc"));
    }

    @Test
    public void testSetupTerminal() {
        Terminal terminal = inAndOut.setupTerminal();
        assertNotNull(terminal);
    }

    @Test
    public void testTerminalRead() {
        inAndOut.setupTerminal();
        int key = inAndOut.terminalRead(1);
        assertEquals(- 2, key);
    }


    @Test
    public void testArrowWhitTimeOut() {
        inAndOut.setupTerminal();
        int key = inAndOut.handleArrowKey(1, 10);
        assertEquals(1, key);
    }

    @Test
    public void handleArrowKeyMovesUp() {
        inAndOut = spy(new InAndOutput());
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        doReturn((int) '[').doReturn((int) 'A').when(inAndOut).terminalRead(100);
        int result = inAndOut.handleArrowKey(1, 10);
        assertEquals(0, result);
    }

    @Test
    public void handleArrowKeyMovesDown() {
        inAndOut = spy(new InAndOutput());
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        doReturn((int) '[').doReturn((int) 'B').when(inAndOut).terminalRead(100);
        int result = inAndOut.handleArrowKey(1, 10);
        assertEquals(2, result);
    }

    @Test
    public void handleArrowKeyMovesRight() {
        inAndOut = spy(new InAndOutput());
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        doReturn((int) '[').doReturn((int) 'C').when(inAndOut).terminalRead(100);
        int result = inAndOut.handleArrowKey(1, 10);
        assertEquals(2, result);
    }

    @Test
    public void handleArrowKeyMovesLeft() {
        inAndOut = spy(new InAndOutput());
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        doReturn((int) '[').doReturn((int) 'D').when(inAndOut).terminalRead(100);
        int result = inAndOut.handleArrowKey(1, 10);
        assertEquals(0, result);
    }


    @Test
    public void testAnnounceWinner() {
        Database dbd = new Database();
        dbd.createNewTable("test.db");
        Player player = new Player(1, "TestName", 9, "X", true);
        String actual = inAndOut.announceWinner(player, "TestName", dbd);
        String expected = "┌────────────┬───────┬────────┬─────────┐\r\n" + "│    Name    │  Win  │  Lose  │  Ratio  │\r\n" + "├────────────┼───────┼────────┼─────────┤\r\n" + "│  TestName  │   1   │   0    │  1,00   │\r\n" + "└────────────┴───────┴────────┴─────────┘\n" + "\u001B[30;101mTestName wins\u001B[0m";

        assertEquals(expected, actual);
    }

    @Test
    public void testAnnounceWinnerLost() {
        Database dbd = new Database();
        dbd.createNewTable("test.db");
        Player player = new Player(1, "TestName", 9, "X", false);
        String actual = inAndOut.announceWinner(player, "TestName", dbd);
        String expected = "┌────────────┬───────┬────────┬─────────┐\r\n" + "│    Name    │  Win  │  Lose  │  Ratio  │\r\n" + "├────────────┼───────┼────────┼─────────┤\r\n" + "│  TestName  │   0   │   1    │  0,00   │\r\n" + "└────────────┴───────┴────────┴─────────┘\n" + "\u001B[30;101mTestName wins\u001B[0m";

        assertEquals(expected, actual);
    }


    @Test
    public void testIsTerminalDumb() {
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        // Arrange
        when(mockTerminal.getType()).thenReturn("dumb");

        // Act
        boolean result = inAndOut.isTerminalDumb();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsTerminalNotDumb() {
        Terminal mockTerminal = mock(Terminal.class);
        inAndOut.setTerminal(mockTerminal);
        // Arrange
        when(mockTerminal.getType()).thenReturn("xterm");

        // Act
        boolean result = inAndOut.isTerminalDumb();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testReadString() {


        String expected = "Jack";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(expected.getBytes()));

        inAndOut = new InAndOutput();
        String actual = inAndOut.readString();

        System.setIn(stdin);

        assertEquals(expected, actual);

    }

    @Test
    public void testReadStringInt() {

        String expected = "Jack";
        String Send = "123 Jack";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(Send.getBytes()));

        inAndOut = new InAndOutput();
        String actual = inAndOut.readString();

        System.setIn(stdin);

        assertEquals(expected, actual);

    }

    @Test
    public void testReadInt() {
        int expected = 123;
        String Send = "123";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(Send.getBytes()));

        inAndOut = new InAndOutput();
        int actual = inAndOut.read();

        System.setIn(stdin);

        assertEquals(expected, actual);
    }

    @Test
    public void testReadIntString() {
        int expected = 123;
        String Send = "abc 123";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(Send.getBytes()));

        inAndOut = new InAndOutput();
        int actual = inAndOut.read();

        System.setIn(stdin);

        assertEquals(expected, actual);
    }

}





