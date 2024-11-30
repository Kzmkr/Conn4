package org.example.utils;

import org.example.models.Board;
import org.example.utils.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StepTest {

    private Board board;
    private Step step;

    @BeforeEach
    public void setUp() {
        board = new Board(6, 7); // Example dimensions
        step = new Step(board);
    }

    @Test
    public void step_ValidMove_ReturnsTrue() {
        assertTrue(step.step(0, 0, 1));
    }

    @Test
    public void step_InvalidMove_ReturnsFalse() {
        for (int i = 0; i < 6; i++) {
            board.setI(0, i, 1);
        } // Occupy the cell
        assertFalse(step.step(0, 0, 1));
    }

    @Test
    public void isStepValid_ValidRow_ReturnsColumnIndex() {
        assertEquals(5, step.isStepValid(0, board));
    }

    @Test
    public void isStepValid_InvalidRow_ReturnsMinusOne() {
        assertEquals(-1, step.isStepValid(-1, board));
        assertEquals(-1, step.isStepValid(7, board));
    }

    @Test
    public void isStepValid_FullColumn_ReturnsMinusOne() {
        for (int i = 0; i < 6; i++) {
            board.setI(0, i, 1); // Fill the column
        }
        assertEquals(-1, step.isStepValid(0, board));
    }
}