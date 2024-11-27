package org.example.models;

/**
 * The Board class represents a game board with a specified width and height.
 * It provides methods to manipulate and access the board's state.
 */
public class Board {

    private int width;
    private int height;
    private int[][] board;

    /**
     * Constructs a Board with the specified height and width.
     *
     * @param height the height of the board
     * @param width the width of the board
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new int[height][width];
    }

    /**
     * Deep copies the provided board into this board.
     *
     * @param height the height of the new board
     * @param width the width of the new board
     * @param b the 2D array representing the new board
     */
    public void deepCopy(int height, int width, int[][] b) {
        this.height = height;
        this.width = width;
        this.board = b;
    }

    /**
     * Clears the board by reinitializing it with the current height and width.
     */
    public void clear() {
        this.board = new int[height][width];
    }

    /**
     * Returns the width of the board.
     *
     * @return the width of the board
     */
    public int getW() {
        return width;
    }

    /**
     * Returns the height of the board.
     *
     * @return the height of the board
     */
    public int getH() {
        return height;
    }

    /**
     * Returns the 2D array representing the board.
     *
     * @return the 2D array representing the board
     */
    public int[][] getB() {
        return board;
    }

    /**
     * Returns the value at the specified position on the board.
     *
     * @param col the column index
     * @param row the row index
     * @return the value at the specified position
     */
    public int getI(int col, int row) {
        return board[row][col];
    }

    /**
     * Sets the value at the specified position on the board.
     *
     * @param col the column index
     * @param row the row index
     * @param c the value to set
     */
    public void setI(int col, int row, int c) {
        board[row][col] = c;
    }
}