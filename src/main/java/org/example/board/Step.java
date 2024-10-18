package org.example.board;

/**
 * Makes steps.
 */

public class Step {

    /**
     * Makes a step.
     */

    public boolean step(int r, int p, Board b) {
        if (isStepValid(r, b) != -1) {
            b.seti(isStepValid(r, b), r - 1, p);
            return true;
        }
        return false;
    }

    /**
     * Check if step is possible.
     */

    public int isStepValid(int r, Board b) {
        for (int i = b.getH() - 1; i >= 0; i--) {
            if (b.geti(i, r - 1) == 0) {
                return i;
            }
        }
        return -1;
    }
}
