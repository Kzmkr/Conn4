package org.example.utils;

import org.example.models.Board;

/**
 * This class is responsible for validating the win conditions for a connect4 game.
 */

public class Validate {

    public boolean validate(Board b, int col, int row) {
        return validate_row(b, col, row) || validate_col(b, col, row) || validate_diag(b, col, row);
    }

    public boolean validate_row(Board b, int col, int row) {
        int count = 1;
        for (int i = col + 1; i < b.getW() && b.geti(i, row) == b.geti(col, row); i++) {
            count++;
        }
        for (int i = col - 1; i >= 0 && b.geti(i, row) == b.geti(col, row); i--) {
            count++;
        }
        return count > 3;
    }


    public boolean validate_col(Board b, int col, int row) {

        int count = 1;

        for (int i = row+1; i < b.getH() && b.geti(col,i) == b.geti(col, row); i++) {
            count++;
        }
        return count > 3;
    }

    /**
     * Checks winner diagonally.
     */

    public boolean validate_diag(Board b, int col, int row) {
    int countl = 1;
    int countr = 1;

    for (int i = 1; i < 4; i++) {
        if (col + i < b.getW() && row + i < b.getH() && b.geti(col, row) == b.geti(col + i, row + i)) {
            countl++;
        }
        if (col - i >= 0 && row - i >= 0 && b.geti(col, row) == b.geti(col - i, row - i)) {
            countl++;
        }
        
        if (col - i >= 0 && row + i < b.getH() && b.geti(col, row) == b.geti(col - i, row + i)) {
            countr++;
        }
        if (col + i < b.getW() && row - i >= 0 && b.geti(col, row) == b.geti(col + i, row - i)) {
            countr++;
        }
    }

    return countl > 3 || countr > 3;
}
}
