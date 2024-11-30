package org.example.input;

import org.example.models.Player;
import org.jline.terminal.Terminal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.*;
import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InAndOutTest {

        private InAndOutput inAndOut;
        private Terminal terminal;

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;
        private final InputStream originalIn = System.in;


        @BeforeEach
        public void setUp() {
            inAndOut = new InAndOutput();
            System.setOut(new PrintStream(outContent));

        }
        @AfterEach
        public void tearDown() {
            System.setOut(originalOut);
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
            Terminal terminal = inAndOut.setupTerminal();
            int key =inAndOut.terminalRead(1);
            assertEquals(-2, key);
        }

        @Test
        public void testArrowWhitTimeOut() {
            Terminal terminal = inAndOut.setupTerminal();
            int key =inAndOut.handleArrowKey(1, 10);
            assertEquals(1, key);
        }


        @Test
        public void testAnnounceWinner() {
            Database dbd = new Database();
            dbd.createNewTable("test.db");
            Player player = new Player(1, "TestName", 9, "X",true);
            String actual = inAndOut.announceWinner(player,"TestName",dbd);
            String expected =
                    "┌────────────┬───────┬────────┬─────────┐\r\n" +
                            "│    Name    │  Win  │  Lose  │  Ratio  │\r\n" +
                            "├────────────┼───────┼────────┼─────────┤\r\n" +
                            "│  TestName  │   1   │   0    │  1,00   │\r\n" +
                            "└────────────┴───────┴────────┴─────────┘\n" +
                            "\u001B[30;101mTestName wins\u001B[0m";

            assertEquals(expected, actual);
        }
        @Test
        public void testAnnounceWinnerLost() {
            Database dbd = new Database();
            dbd.createNewTable("test.db");
            Player player = new Player(1, "TestName", 9, "X",false);
            String actual = inAndOut.announceWinner(player,"TestName",dbd);
            String expected =
                    "┌────────────┬───────┬────────┬─────────┐\r\n" +
                            "│    Name    │  Win  │  Lose  │  Ratio  │\r\n" +
                            "├────────────┼───────┼────────┼─────────┤\r\n" +
                            "│  TestName  │   0   │   1    │  0,00   │\r\n" +
                            "└────────────┴───────┴────────┴─────────┘\n" +
                            "\u001B[30;101mTestName wins\u001B[0m";

            assertEquals(expected, actual);
        }




    }