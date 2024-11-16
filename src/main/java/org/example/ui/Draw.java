package org.example.ui;

import org.example.models.Board;
import org.example.models.Player;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

/**
 * Model class used to represent a connect4 map.
 */
public class Draw {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Draw(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;


    }

    /**
     * Draws the board with the current state.
     *
     * @param sel    The selected column.
     * @param player The current player.
     */
    public void draw_Board(int sel, Player player) {
        sel = sel + 1;
        StringBuilder boardText = new StringBuilder();
        String[] saveOptions = {"To save hit (S)", "To exit hit (Q)", "To restart hit (R)"};
        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK);

        AttributedStyle style1 = style.background(player1.getColor());
        AttributedStyle style2 = style.background(player2.getColor());

        // Draw column numbers
        for (int j = 1; j <= board.getW(); j++) {
            String pad = (j > 9) ? "" : " ";
            String number = " " + j + pad;
            if (j == sel) {
                AttributedStyle style3 = style.background(player.getColor());
                AttributedString selected = new AttributedString(" " + j + pad, style3);
                number = selected.toAnsi();
            }
            boardText.append("│").append(number);
        }
        boardText.append("│\n");

        // Draw board cells
        for (int i = 0; i < board.getH(); i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board.getW(); j++) {
                String cell = "( )";

                if (board.getB()[i][j] == player1.getId()) {
                    AttributedString attributeCell = new AttributedString("(O)", style1);
                    cell = attributeCell.toAnsi();
                } else if (board.getB()[i][j] == player2.getId()) {
                    AttributedString attributeCell = new AttributedString("(X)", style2);
                    cell = attributeCell.toAnsi();
                }
                row.append("│").append(cell).append("\u001B[0m");
            }
            String option = (i < 3) ? saveOptions[i] : " ";
            row.append("|").append(String.format("%" + (40 + option.length()) + "s%n", option.replaceAll(".", "$0 ")));
            boardText.append(row);
        }

        System.out.print(boardText);
    }

    /**
     * Prompts the user to enter their name.
     */
    public void ask_name() {
        System.out.println("Enter your name: ");
    }
}