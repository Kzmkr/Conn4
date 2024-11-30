package org.example.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.example.models.Board;

public class ValidateTest {

    private Validate validate;
    private Board board;

    @BeforeEach
    void setUp() {
        validate = new Validate();
        board = new Board(10, 10); // Assuming a 7x6 board for Connect4
    }

    @Test
    void rowValidationWithFourInARow() {
        // Set up a row with four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(1, 0, 1);
        board.setI(2, 0, 1);
        board.setI(3, 0, 1);
        assertTrue(validate.validate_row(board, 2, 0));
        assertTrue(validate.validate(board, 2, 0));
    }

    @Test
    void rowValidationWithLessThanFourInARow() {
        // Set up a row with less than four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(1, 0, 1);
        board.setI(2, 0, 1);
        assertFalse(validate.validate_row(board, 1, 0));
        assertFalse(validate.validate(board, 1, 0));

    }

    @Test
    void columnValidationWithFourInAColumn() {
        // Set up a column with four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(0, 1, 1);
        board.setI(0, 2, 1);
        board.setI(0, 3, 1);
        assertTrue(validate.validate_col(board, 0, 0));
        assertTrue(validate.validate(board, 0, 0));
    }

    @Test
    void columnValidationWithLessThanFourInAColumn() {
        // Set up a column with less than four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(0, 1, 1);
        board.setI(0, 2, 1);
        assertFalse(validate.validate_col(board, 0, 1));
        assertFalse(validate.validate(board, 0, 1));
    }

    @Test
    void diagonalValidationWithFourInADiagonal() {
        // Set up a diagonal with four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(1, 1, 1);
        board.setI(2, 2, 1);
        board.setI(3, 3, 1);
        assertTrue(validate.validate_diag(board, 2, 2));
        assertTrue(validate.validate(board, 2, 2));
    }

    @Test
    void diagonalValidationWithFourInOtherADiagonal() {
        // Set up a diagonal with four consecutive pieces
        board.setI(9, 0, 1);
        board.setI(8 ,1, 1);
        board.setI(7, 2, 1);
        board.setI(6, 3, 1);
        assertTrue(validate.validate_diag(board, 8, 1));
        assertTrue(validate.validate(board, 8, 1));
    }

    @Test
    void diagonalValidationWithLessThanFourInADiagonal() {
        // Set up a diagonal with less than four consecutive pieces
        board.setI(0, 0, 1);
        board.setI(1, 1, 1);
        board.setI(2, 2, 1);
        assertFalse(validate.validate_diag(board, 1, 1));
        assertFalse(validate.validate(board, 1, 1));
    }



}