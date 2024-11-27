package org.example.utils;

import org.example.models.Board;

/**
 * The Validate class provides methods to validate winning conditions on a game board.
 */
public class Validate {

    /**
     * Validates if there is a winning condition on the board at the specified column and row.
     *
     * @param b the game board
     * @param col the column index
     * @param row the row index
     * @return true if there is a winning condition, false otherwise
     */
    public boolean validate(Board b, int col, int row) {
        return validate_row(b, col, row) || validate_col(b, col, row) || validate_diag(b, col, row);
    }

    /**
     * Validates if there is a winning condition in the row at the specified column and row.
     *
     * @param b the game board
     * @param col the column index
     * @param row the row index
     * @return true if there is a winning condition in the row, false otherwise
     */
    public boolean validate_row(Board b, int col, int row) {
        int count = 1;
        for (int i = col + 1; i < b.getW() && b.getI(i, row) == b.getI(col, row); i++) {
            count++;
        }
        for (int i = col - 1; i >= 0 && b.getI(i, row) == b.getI(col, row); i--) {
            count++;
        }
        return count > 3;
    }

    /**
     * Validates if there is a winning condition in the column at the specified column and row.
     *
     * @param b the game board
     * @param col the column index
     * @param row the row index
     * @return true if there is a winning condition in the column, false otherwise
     */
    public boolean validate_col(Board b, int col, int row) {
        int count = 1;
        for (int i = row + 1; i < b.getH() && b.getI(col, i) == b.getI(col, row); i++) {
            count++;
        }
        return count > 3;
    }

    /**
     * Validates if there is a winning condition diagonally at the specified column and row.
     *
     * @param b the game board
     * @param col the column index
     * @param row the row index
     * @return true if there is a winning condition diagonally, false otherwise
     */
    public boolean validate_diag(Board b, int col, int row) {
        int countl = 1;
        int countr = 1;

        for (int i = 1; i < 4; i++) {
            if (col + i < b.getW() && row + i < b.getH() && b.getI(col, row) == b.getI(col + i, row + i)) {
                countl++;
            }
            if (col - i >= 0 && row - i >= 0 && b.getI(col, row) == b.getI(col - i, row - i)) {
                countl++;
            }
            if (col - i >= 0 && row + i < b.getH() && b.getI(col, row) == b.getI(col - i, row + i)) {
                countr++;
            }
            if (col + i < b.getW() && row - i >= 0 && b.getI(col, row) == b.getI(col + i, row - i)) {
                countr++;
            }
        }

        return countl > 3 || countr > 3;
    }
}