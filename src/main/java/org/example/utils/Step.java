package org.example.utils;

import org.example.models.Board;

/**
 * Makes steps.
 */

public class Step {

    private final Board board;

    public Step(Board b) {

        this.board = b;
    }

    public boolean step(int r, int c, int p) {
        if (isStepValid(r, this.board) != -1) {
            this.board.seti(r, c, p);
            return true;
        }
        return false;
    }

    /**
     * Check if step is possible.
     */

    public int isStepValid(int r, Board b) {
        if (r < 0 || r > b.getW()) {
            return -1;
        }
        for (int i = b.getH() - 1; i >= 0; i--) {
            if (b.geti(r, i) == 0) {
                return i;
            }
        }
        return -1;
    }
}
