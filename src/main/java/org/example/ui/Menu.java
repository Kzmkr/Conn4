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

    public String menu() throws IOException {

        String file = "C:\\Users\\User\\Documents\\J\\Con4\\src\\main\\resources\\epic.flf";
        String menuS = FigletFont.convertOneLine(new File(file), "C o n n e t c 4");
        String menuss = "\033c\n";


        String c = "\u001B[101;30m";
        String c2 = "\u001B[103;30m";
        String c3;
        String r = "\u001B[0m";
        int len = (menuS.length() / 9);

        System.out.println(len);
        for (int i = 0; i < menuS.length(); i++) {

            if ((i + 1) % (len / 8) == 0) {
                c3 = c;
                c = c2;
                c2 = c3;
            }
            if (menuS.charAt(i) != '\n') {
                menuss = menuss + c + menuS.charAt(i);

            } else {
                menuss = menuss + c + r + "\n";
            }
        }
        String[] opciok = {"Játék", "pálya beolvasása", "kilépés"};
        int i = 1;
        for (String s : opciok) {
            int p = 35 + s.length();

            String ss = String.format(i + "%" + (len / 2 + s.length()) + "s%n", s.toUpperCase().replaceAll(".", "$0 "));
            menuss = menuss + ss + "\n";
            i++;
        }
        return menuss;
    }

}

