package org.example.ui;

import java.io.File;
import java.io.IOException;

import com.github.lalyos.jfiglet.FigletFont;


/**
 * The Menu class provides methods to generate and display a menu with ASCII art and options.
 */
public class Menu {

    /**
     * Generates a menu with ASCII art and options.
     *
     * @param sel the index of the selected menu option
     * @param width the width of the terminal
     * @return a string representation of the menu
     */
    public String menu(int sel, int width) {

        // Path to the Figlet font file
        String font = "src/main/resources/epic.flf";
        String con4 = "Connetc4";
        String[] list = new String[con4.length()];

        // Convert the text "C o n n e t c 4" to ASCII art using the Figlet font
        try {
            String menuTxt = FigletFont.convertOneLine(new File(font), "C o n n e t c 4");

            String menuss = "";

            // Define color codes for the menu
            String c = "\u001B[101;30m";
            String c2 = "\u001B[103;30m";
            String c3;
            String selc;
            String r = "\u001B[0m";
            int len = (menuTxt.length() / 9);

            // Apply alternating colors to the ASCII art text
            int padding = (width - len) / 2;
            if (padding <= 0) {
                padding = 1;
            }

            String paddin = String.format("%" + padding + "s", "");
            menuss = menuss + paddin + c;

            for (int i = 0; i < menuTxt.length(); i++) {
                if ((i + 1) % (len / 8) == 0) {
                    c3 = c;
                    c = c2;
                    c2 = c3;
                    if (menuTxt.charAt(i) == '\n') {
                        menuss = menuss + r + "\n" + paddin;
                    } else {
                        menuss = menuss + c + menuTxt.charAt(i);
                    }
                } else if (i % (len) == 0) {
                    menuss = menuss + c + menuTxt.charAt(i);
                } else {
                    menuss = menuss + menuTxt.charAt(i);
                }
            }

            // Define the menu options
            String[] opciok = {"Játék", "pálya beolvasása", "kilépés"};
            int i = 0;

            // Format and add the menu options to the menu string
            for (String s : opciok) {
                int p = 35 + s.length();
                if (sel == i) {
                    selc = "\u001B[102;30m";
                } else {
                    selc = "";
                }

                String ss = String.format("%" + (len - s.length() * 2) / 2 + "s", "");
                ss = ss + selc + (s.toUpperCase().replaceAll(".", "$0 ")) + "  " + (i + 1) + r;
                menuss = menuss + ss + "\n" + paddin;
                i++;
            }
            return menuss;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}