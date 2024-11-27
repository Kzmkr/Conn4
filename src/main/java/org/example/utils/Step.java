package org.example.utils;

import org.example.models.Board;

/**
 * The Step class provides methods to handle and validate steps on a game board.
 */
public class Step {

    private final Board board;
    private int lastR;
    private int lastC;

    /**
     * Constructs a Step object with the specified board.
     *
     * @param b the board to be used for steps
     */
    public Step(Board b) {
        this.board = b;
    }

    /**
     * Attempts to place a piece on the board at the specified row and column for the given player.
     *
     * @param r the row index
     * @param c the column index
     * @param p the player identifier
     * @return true if the step is valid and the piece is placed, false otherwise
     */
    public boolean step(int r, int c, int p) {
        if (isStepValid(r, board) != -1) {
            lastC = c;
            lastR = r;
            board.setI(r, c, p);
            return true;
        }
        return false;
    }

    /**
     * Checks if a step is valid for the specified row on the given board.
     *
     * @param r the row index
     * @param b the board to check
     * @return the column index if the step is valid, -1 otherwise
     */
    public int isStepValid(int r, Board b) {
        if (r < 0 || r >= b.getW()) {
            return -1;
        }
        for (int i = b.getH() - 1; i >= 0; i--) {
            if (b.getI(r, i) == 0) {
                return i;
            }
        }
        return -1;
    }
}