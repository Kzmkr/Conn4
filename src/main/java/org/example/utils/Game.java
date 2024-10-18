package org.example.utils;

import java.io.IOException;

import org.example.board.Board;
import org.example.board.Step;
import org.example.board.Validate;
import org.example.input.Input;
import org.example.ui.Draw;
import org.example.ui.Menu;



/**
 * Initialise the game.
 */

public class Game {

    /**
     * Starts the game.
     */

    public  void start() throws IOException {

        Board b = new Board(10, 10);
        Input in = new Input();
        File f = new File();


        Menu menu = new Menu();
        System.out.print(menu.menu());
        int o = in.read();

        if (o == 3) {
            System.exit(0);
        }
        Validate valid = new Validate();
        int r;
        Step stepper = new Step();
        while (true) {
            if (valid.validater(b)) {
                break;
            }
            Draw.draw_Board(b);
            r = in.read();
            stepper.step(r, 1, b);
            Draw.draw_Board(b);
            if (valid.validater(b)) {
                break;
            }
            r = in.read();
            stepper.step(r, 2, b);


            f.filewrite(b, "out");
        }


    }
}
