package org.example.board;

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

    public void step(int r, int p) {

        for (int i = this.getH() - 1; i >= 0; i--) {
            if (this.geti(i, r - 1) == 0) {
                this.seti(i, r - 1, p);
                break;
            }
        }
    }


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
        return board[x][y];
    }

    public void seti(int x, int y, int c) {
        board[x][y] = c;
    }
}
