package org.example.input;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

/**
 * The InAndOutput class provides methods for reading input from the terminal,
 * handling arrow key inputs, and setting up the terminal.
 */
public class InAndOutput {
    private static final Scanner scan = new Scanner(System.in);
    private Terminal terminal;

    /**
     * Reads a character from the terminal with a specified timeout.
     *
     * @param timeout the timeout in milliseconds
     * @return the character read, or -2 if an error occurs
     */
    public int terminalRead(int timeout) {
        try {
            return terminal.reader().read(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -2;
    }

    /**
     * Checks if a given string is numeric.
     *
     * @param strNum the string to check
     * @return true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Handles arrow key inputs and updates the index based on the arrow key pressed.
     *
     * @param i the current index
     * @param wrapValue the value to wrap around
     * @return the updated index
     */
    public int handleArrowKey(int i, int wrapValue) {
        int next = terminalRead(100);  // '[' character
        int arrow = terminalRead(100);
        switch (arrow) {
            case 'A':
                i = (i + wrapValue - 1) % wrapValue; // Move up
                break;
            case 'B':
                i = (i + 1) % wrapValue; // Move down
                break;
            case 'C':
                i = (i % wrapValue + 1) % wrapValue; // Move right
                break;
            case 'D':
                i = (i + wrapValue - 1) % wrapValue; // Move left
                break;
            default:
                break;
        }
        return i;
    }

    /**
     * Reads an integer from the standard input.
     *
     * @return the integer read
     */
    public int read() {
        while (true) {
            try {
                return scan.nextInt();
            } catch (Exception e) {
                System.out.println("Please try again");
                scan.next();
            }
        }
    }

    /**
     * Sets up the terminal with UTF-8 encoding and hides the cursor.
     *
     * @return the Terminal object, or null if an error occurs
     */
    public Terminal setupTerminal() {
        Logger.getLogger("org.jline").setLevel(Level.SEVERE);
        try {
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .encoding(StandardCharsets.UTF_8)
                    .build();
            terminal.puts(InfoCmp.Capability.cursor_invisible);
            this.terminal = terminal;
            return terminal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads a non-numeric string from the standard input.
     *
     * @return the string read
     */
    public String read_string() {
        while (true) {
            try {
                String in = scan.next();
                if (!isNumeric(in)) {
                    return in;
                }
            } catch (Exception e) {
                scan.next();
            }
            System.out.println("Please try again");
        }
    }
}