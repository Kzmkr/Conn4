package org.example.input;

import org.jline.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;



    public class InAndOutTest {

        private InAndOutput inAndOut;
        private Terminal terminal;

        @BeforeEach
        public void setUp() {
            inAndOut = new InAndOutput();

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

    }