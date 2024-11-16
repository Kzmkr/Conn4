package org.example.ui;

import java.io.File;
import java.io.IOException;

import com.github.lalyos.jfiglet.FigletFont;



/**
 * Ui class to print menu.
 */


public class Menu {

    /**
     * Prints menu.
     */

    /**
     * Generates a menu string with selectable options.
     *
     * @param sel The index of the currently selected menu option.
     * @return The formatted menu string.
     * @throws IOException If an I/O error occurs.
     */
    public String menu(int sel) throws IOException {

        // Path to the Figlet font file
        String font = "src/main/resources/epic.flf";
        String con4 = "Connetc4";
        String[] list = new String[con4.length()];

        // Convert the text "C o n n e t c 4" to ASCII art using the Figlet font
        String menuTxt = FigletFont.convertOneLine(new File(font), "C o n n e t c 4");


        for (int i = 0; i < con4.length(); i++) {
            list[i] = FigletFont.convertOneLine(new File(font), String.valueOf(con4.charAt(i)));
        }


        String menuss = "";

        // Define color codes for the menu
        String c = "\u001B[101;30m";
        String c2 = "\u001B[103;30m";
        String c3;
        String selc;
        String r = "\u001B[0m";
        int len = (menuTxt.length() / 9);

        // Apply alternating colors to the ASCII art text
        for (int i = 0; i < menuTxt.length(); i++) {
            if ((i + 1) % (len / 8) == 0) {
                c3 = c;
                c = c2;
                c2 = c3;
            }
            if (menuTxt.charAt(i) != '\n') {
                menuss = menuss + c + menuTxt.charAt(i);
            } else {
                menuss = menuss + c + r + "\n";
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
            menuss = menuss + ss + "\n";
            i++;
        }
        return menuss;
    }
}

