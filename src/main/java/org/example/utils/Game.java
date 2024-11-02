package org.example.utils;

import java.io.IOException;

import org.example.input.File;
import org.example.models.Board;
import org.example.models.Player;
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

    public void start() throws IOException {

        Player p1 = new Player(1, "Player 1", "X", "X");

        Board b = new Board(10, 10);
        Input in = new Input();
        File f = new File();

        Menu menu = new Menu();
        System.out.print(menu.menu());
        int o = in.read();

        if (o == 3) {
            System.exit(0);
        }
        if (o == 2) {
            f.filewrite(b, "out");
        }


        Validate valid = new Validate();
        int col;
        Step stepper = new Step(b);
        Draw drawer = new Draw(b);

        drawer.ask_name();
        String name = in.read_string();
        Player p2 = new Player(2, name, "O", "O");

        drawer.draw_Board();
        while (true) {


            col = in.read() - 1;
            int row = stepper.isStepValid(col, b);

            while (row == -1) {
                System.out.println("Invalid move try again");
                col = in.read();
                row = stepper.isStepValid(col, b);
            }


            stepper.step(col, row, 1);
            drawer.draw_Board();

            if (valid.validate(b, col, row)) {
                System.out.println("Player 1 wins");
                break;
            }


            col = in.read() - 1;
            row = stepper.isStepValid(col, b);

            while (row == -1) {
                System.out.println("Invalid move try again");
                col = in.read();
                row = stepper.isStepValid(col, b);
            }
            stepper.step(col, row, 2);

            drawer.draw_Board();
            if (valid.validate(b, col, row)) {
                System.out.println("Player 2 wins");
                break;
            }


        }


    }
}
