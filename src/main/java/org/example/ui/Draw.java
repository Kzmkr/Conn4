package org.example.ui;

import org.example.models.Board;

/**
 * Model class used to represent a connect4 map.
 */

public class Draw {
    private final Board board;

    public Draw(Board b) {

        this.board = b;
    }

    /**
     * Gets input from user.
     */

    public void draw_Board() {
        System.out.println("\033c");
        StringBuilder l = new StringBuilder();
        String c = "";
        String c1 = "";


        for (int j = 0; j < this.board.getW(); j++) {
            l.append("│ " + c1 + (j + 1) + " \u001B[0m");
            c = "( )";
        }
        l.append("│");
        System.out.print(l + "\n");
        for (int i = 0; i < this.board.getH(); i++) {
            l.setLength(0);
            for (int j = 0; j < this.board.getW(); j++) {
                if (this.board.getB()[i][j] == 1) {
                    c = "\u001B[41;30m(O)";
                }
                if (this.board.getB()[i][j] == 2) {
                    c = "\u001B[103;30m(X)";
                }

                l.append("│" + c + "\u001B[0m");
                c = "( )";
            }
            l.append("│\n");
            System.out.print(l);


        }


    }

    public void ask_name() {
        System.out.println("Enter your name: ");
    }

    /**
     * Model class used to represent a connect4 map.
     */

    public static void draw2(int[][] b) {
        StringBuilder l = new StringBuilder();
        final String r = "\u001B[0m";
        String c = "";
        for (int i = 0; i < 7; i++) {


            for (int j = 0; j < 6; j++) {
                if (b[i][j] == 1) {
                    c = "\u001B[41;30m";
                }
                if (b[i][j] == 2) {
                    c = "\u001B[103;30m";
                }

                l.append(" " + c + "/     \\" + r);
                c = "";
            }
            System.out.print(l + "\n");
            l.setLength(0);
            l.append("");
            for (int j = 0; j < 6; j++) {
                l.append(" " + c + "|     |" + r);
            }
            System.out.print(l + "\n");
            l.setLength(0);
            for (int j = 0; j < 6; j++) {
                l.append(" " + c + "\\     /" + r);
            }

            System.out.print(l + "\n");
            l.setLength(0);
        }
    }

    /**
     * Model class used to represent a connect4 map.
     */
    public static void draw5(int[][] b) {

        StringBuilder l = new StringBuilder();
        final String r = "\u001B[0m";
        String c = "\u001B[0m";
        String bc = "\u001B[104m";
        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 7; j++) {
                if (b[i][j] == 1) {
                    c = "\u001B[101;30m";
                }
                if (b[i][j] == 2) {
                    c = "\u001B[103;30m";
                }

                l.append(bc + "|" + c + "/       \\" + r);
                c = "\u001B[0m";
            }
            l.append(bc + "|" + r);
            System.out.print(l + "\n");
            l.setLength(0);

            for (int j = 0; j < 7; j++) {
                if (b[i][j] == 1) {
                    c = "\u001B[101;30m";
                }
                if (b[i][j] == 2) {
                    c = "\u001B[103;30m";
                }
                l.append(bc + "|" + c + "         " + r);
                c = "\u001B[0m";
            }
            l.append(bc + "|" + r);

            System.out.print(l + "\n");
            l.setLength(0);
            for (int j = 0; j < 7; j++) {
                if (b[i][j] == 1) {
                    c = "\u001B[101;30m";
                }
                if (b[i][j] == 2) {
                    c = "\u001B[103;30m";
                }
                l.append(bc + "|" + c + "\\       /" + r);
                c = "\u001B[0m";
            }
            l.append(bc + "|" + r);
            System.out.print(l + "\n");
            l.setLength(0);

        }
        System.out.print("\n\n");

    }


}

