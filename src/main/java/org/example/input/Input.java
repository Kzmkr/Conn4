package org.example.input;

import java.util.Scanner;

/**
 * Gets input from user.
 */

public class Input {
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Gets input from user.
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

    public String read_string() {
        while (true) {
            try {

                String in = scan.next();
                if(!isNumeric(in)){
                    return in;
                }
            } catch (Exception e) {

                scan.next();
            }
            System.out.println("Please try again");
        }
    }
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}




