package org.example.input;

import java.util.Scanner;

/**
 * Gets input from user.
 */

public class Input {
    private static final Scanner scan = new Scanner(System.in);

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
}

