package org.example.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.input.File;
import org.example.input.Input;
import org.example.models.Board;
import org.example.models.Player;
import org.example.ui.Draw;
import org.example.ui.Menu;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;

public class Game {

    private Terminal terminal;
    private Player player1;
    private Player player2;
    private Board board;
    private Input input;
    private File file;
    private Menu menu;
    private Draw drawer;
    private Validate validator;
    private Step stepper;

    public void start() throws IOException, InterruptedException {
        setupTerminal();
        initializeGameComponents();
        handleMenuSelection();
    }

    private void setupTerminal() {
        Logger.getLogger("org.jline").setLevel(Level.SEVERE);
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .encoding(StandardCharsets.UTF_8)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        terminal.puts(Capability.cursor_invisible);
    }

    private void initializeGameComponents() {
        player1 = new Player(1, "Player 1", 9, "X");
        player2 = new Player(2, "player 2", 11, "O");
        board = new Board(10, 10);
        input = new Input();
        file = new File();
        menu = new Menu();
        validator = new Validate();
        stepper = new Step(board);
        drawer = new Draw(board, player1, player2);

    }


    private void handleMenuSelection() throws IOException, InterruptedException {
        if ("dumb".equals(terminal.getType())) {
            terminal.puts(Capability.clear_screen);
            System.out.print(menu.menu(- 1));
            int selection = input.read();
            processMenuSelection(selection);
        }

        int i = 0;
        terminal.puts(Capability.clear_screen);
        System.out.print(menu.menu(i));
        boolean e = true;
        while (e) {
            int key = terminal.reader().read();
            if (key == 13) {
                e = false;
            }
            if (key == 27) { // Escape character
                int next = terminal.reader().read();
                int arrow = terminal.reader().read();
                switch (arrow) {
                    case 'A':
                        if (i == 0) {
                            i = 2;
                        } else {
                            i--;
                        }
                        break;
                    case 'B':
                        if (i == 2) {
                            i = 0;
                        } else {
                            i++;
                        }
                        break;
                }
                terminal.puts(Capability.cursor_address, 0, 0);
                System.out.print(menu.menu(i));

            }

        }
        processMenuSelection(i + 1);
    }


    private void processMenuSelection(int selectedOption) throws IOException, InterruptedException {
        if (selectedOption == 3) {
            System.exit(0);
        } else if (selectedOption == 2) {
            Object[] objects = file.read("src/main/resources/output.xml");
            Board b = (Board) objects[0];
            board.deepcopy(b.getH(), b.getW(), b.getB());
            player1 = (Player) objects[1];
            startGameLoop();
        } else if (selectedOption == 1) {
            startGameLoop();
        }
    }

    private void askname() {
        terminal.puts(Capability.cursor_visible);
        drawer.ask_name();
        String name = input.read_string();
    }

    private int handleColSelection(Player p) throws IOException, InterruptedException {
        int i = 0;

        terminal.puts(Capability.cursor_address, 0, 0);
        drawer.draw_Board(0, p);

        boolean e = true;
        while (e) {
            int key = terminal.reader().read();
            if (key == 13) {
                e = false;
            }
            if (key == 'q') {
                System.exit(0);
            }
            if (key == 's') {
                file.write("src/main/resources/output.xml", board, player1);
                System.exit(0);
            }
            if (key == 'r') {
                initializeGameComponents();
                startGameLoop();
            }
            if (key == 27) { // Escape character
                int next = terminal.reader().read();  // '[' character
                int arrow = terminal.reader().read();
                switch (arrow) {

                    case 'C':
                        if (i > board.getW() - 2) {
                            i = 0;
                        } else {
                            i++;
                        }
                        break;
                    case 'D':
                        if (i < 1) {
                            i = board.getW() - 1;
                        } else {
                            i--;
                        }
                        break;

                }

            }
            terminal.puts(Capability.cursor_address, 0, 0);
            drawer.draw_Board(i, p);
        }

        return i;
    }

    private void startGameLoop() throws InterruptedException, IOException {
        terminal.puts(Capability.clear_screen);
        drawer.draw_Board(0, player1);

        while (true) {
            handlePlayerMove(player1);
            handlePlayerMove(player2);
        }
    }

    private void handlePlayerMove(Player p) throws InterruptedException, IOException {
        int row = - 1;
        int col = 0;
        while (row == - 1) {
            if ("dumb".equals(terminal.getType())) {
                col = input.read() - 1;
            } else {
                col = handleColSelection(p);
            }

            row = stepper.isStepValid(col, board);

        }
        board.seti(col, 0, p.getId());
        terminal.puts(Capability.clear_screen);
        drawer.draw_Board(- 1, p);
        Thread.sleep(100);

        for (int j = 1; j <= row; j++) {
            board.seti(col, j - 1, 0);
            board.seti(col, j, p.getId());
            terminal.puts(Capability.cursor_address, 0, 0);
            drawer.draw_Board(- 1, p);
            Thread.sleep(100);
        }
        if (validator.validate(board, col, row)) {
            System.out.println("Player" + p.getId() + " wins");
            if (p.getId() == 1) {
                askname();
            }
            System.exit(0);

        }
    }
}