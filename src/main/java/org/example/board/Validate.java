package org.example.board;

/**
 * Checks if there is a winner.
 */

public class Validate {

    /**
     * Checks winner in rows.
     */

    public boolean validater(Board b) {


        int db;

        for (int i = 1; i <= b.getH() / 4; i++) {
            int r = b.getH() - 4 * i;

            for (int w = 0; w < b.getW(); w++) {
                db = 0;
                if (b.geti(r, w) != 0) {
                    for (int h = r; h >= 0; h--) {
                        if (b.geti(h, w) == b.geti(r, w)) {
                            db++;
                        } else {
                            break;
                        }
                    }
                    for (int h = r + 1; h < b.getH(); h++) {
                        if (b.geti(h, w) == b.geti(r, w)) {
                            db++;
                        } else {
                            break;
                        }
                    }

                    if (db == 4) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Checks winner in columns.
     */

    public int validatec(Board b) {
        int db;

        for (int i = 1; i <= b.getW() / 4; i++) {
            int r = b.getW() - 4 * i;

            for (int h = 0; h < b.getW(); h++) {
                db = 0;
                if (b.geti(h, r) != 0) {
                    for (int w = r; w >= 0; w--) {
                        if (b.geti(h, w) == b.geti(h, r)) {
                            db++;
                        } else {
                            break;
                        }
                    }
                    for (int w = r + 1; w < b.getH(); w++) {
                        if (b.geti(h, w) == b.geti(h, r)) {
                            db++;
                        } else {
                            break;
                        }
                    }

                    if (db == 4) {
                        System.out.print(b.geti(h, r));
                    }
                }

            }
        }
        return 0;
    }

    /**
     * Checks winner diagonally.
     */

    public int validated(Board b) {
        int db;

        for (int i = 1; i <= b.getH() / 4; i++) {
            int r = b.getH() - 4 * i;

            for (int w = 0; w < b.getW(); w++) {
                db = 0;
                if (b.geti(r, w) != 0) {
                    for (int h = r; h >= 0; h--) {
                        System.out.println(h + " " + r);
                        if (b.geti(h, w + h) == b.geti(r, w)) {
                            db++;
                        } else {
                            break;
                        }
                    }
                    for (int h = r + 1; h < b.getH(); h++) {
                        if (b.geti(h, (w + h) - 1) == b.geti(r, w)) {
                            db++;
                        } else {
                            break;
                        }
                    }

                    if (db == 4) {
                        System.out.print(b.geti(r, w));
                    }
                }

            }
        }
        return 0;
    }
}
