package org.example.models;

/**
 * Model class used to represent a connect4 board.
 */
public class Board {


    private final int width;
    private final int height;
    private int[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
       this.board = new int[height][width];
    }

    public void fill(int[][] b) {
        this.board = b;
    }
    /**
     * Model class used to represent a connect4 map.
     */




    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public int[][] getB() {
        return board;
    }

    public int geti(int x, int y) {
        return board[y][x];
    }

    public void seti(int x, int y, int c) {
        board[y][x] = c;
    }
}
