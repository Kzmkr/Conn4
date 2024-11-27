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
    private final String[] options = {"Save", "Open", "Restart", "Exit"};



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
    public String draw_Board(int sel, Player player) {
        sel = sel + 1;
        StringBuilder boardText = new StringBuilder();

        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK);

        AttributedStyle style1 = style.background(player1.getColor());
        AttributedStyle style2 = style.background(player2.getColor());

        boardText.append(topMenu(sel, style1)).append("\n");
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

                row.append("│").append(cell);
            }

            boardText.append(row).append("│\n");


        }

        return boardText.toString();
    }

    private String topMenu(int sel, AttributedStyle style1) {
        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK).background(7);
        StringBuilder optionsText = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            if (i == sel * - 1) {
                AttributedString selected = new AttributedString("  " + option + "  ", style1);
                option = selected.toAnsi();
            } else {
                AttributedString selected = new AttributedString("  " + option + "  ", style);
                option = selected.toAnsi();
            }
            optionsText.append(option);
        }
        optionsText.append("\n");

        return optionsText.toString();
    }



    /**
     * Prompts the user to enter their name.
     */
    public void ask_name() {
        System.out.println("Enter your name: ");
    }
}