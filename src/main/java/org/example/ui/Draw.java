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
     * @param selCol The selected column.
     * @param selRow The selected Row.
     * @param player The current player.
     */
    public String draw_Board(int selCol, int selRow, Player player) {

        StringBuilder boardText = new StringBuilder();

        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK);

        AttributedStyle style1 = style.background(player1.getColor());
        AttributedStyle style2 = style.background(player2.getColor());
        AttributedStyle style3 = style.background(player.getColor());

        boardText.append(topMenu(style1)).append("\n");
        // Draw column numbers
        for (int j = 1; j <= board.getW(); j++) {
            String pad = (j > 9) ? "" : " ";
            String number = " " + j + pad;

            if (j == selCol && selRow == - 1) {
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

                if (board.getI(j, i) != 0) {
                    style = (board.getI(j, i) == player1.getId()) ? style1 : style2;
                    AttributedString attributeCell = new AttributedString("(O)", style);
                    cell = attributeCell.toAnsi();
                }
                if (j == selCol && i == selRow) {
                    AttributedString attributeCell = new AttributedString("(O)", style3);
                    cell = attributeCell.toAnsi();
                }


                row.append("│").append(cell);
            }

            boardText.append(row).append("│\n");


        }

        return boardText.toString();
    }

    private String topMenu(AttributedStyle style1) {
        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK).background(7);
        StringBuilder optionsText = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            String option = options[i];

            AttributedString selected = new AttributedString("  " + option + "  ", style);
            option = selected.toAnsi();

            optionsText.append(option);
        }
        optionsText.append("\n");

        return optionsText.toString();
    }


}