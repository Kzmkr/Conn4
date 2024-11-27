package org.example.utils;

import java.io.IOException;

import org.example.input.Database;
import org.example.input.File;
import org.example.input.InAndOutput;
import org.example.models.Board;
import org.example.models.Player;
import org.example.ui.Draw;
import org.example.ui.HighscorePrinter;
import org.example.ui.Menu;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;

/**
 * The Game class manages the main game logic, including initialization, menu handling,
 * game loop, and player moves.
 */
public class Game {

    private final Database dbd = new Database();
    private final int boardWidth = 10;
    private final int boardHeight = 10;
    private Terminal terminal;
    private Player player1;
    private Player player2;
    private Board board;
    private InAndOutput inAndOut;
    private File file;
    private Menu menu;
    private Draw drawer;
    private Validate validator;
    private Step stepper;
    private int width;

    /**
     * Starts the game by initializing components, handling menu selection, and processing the selection.
     */
    public void start() {
        initializeGameComponents();
        int select = handleMenuSelection();
        processMenuSelection(select);
    }

    /**
     * Initializes the game components, including players, board, database, file, menu, validator, stepper, and terminal.
     */
    private void initializeGameComponents() {
        player1 = new Player(1, "Player 1", 9, "X");
        player2 = new Player(2, "player 2", 11, "O");
        board = new Board(boardHeight, boardWidth);
        dbd.createNewTable("hs.db");
        file = new File();
        menu = new Menu();
        validator = new Validate();
        stepper = new Step(board);
        HighscorePrinter highscorePrinter = new HighscorePrinter();
        inAndOut = new InAndOutput();
        terminal = inAndOut.setupTerminal();
        drawer = new Draw(board, player1, player2);
        width = terminal.getWidth();
    }

    /**
     * Handles the menu selection process, including reading input and updating the menu display.
     *
     * @return the selected menu option index
     */
    private int handleMenuSelection() {
        if ("dumb".equals(terminal.getType())) {
            printMenu(-1);
            return inAndOut.read();
        }

        int i = 0;
        terminal.puts(Capability.clear_screen);
        printMenu(i);
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            int key = inAndOut.terminalRead(100);
            if (key == 13) { // Enter key
                isMenuOpen = false;
            }
            if (key == 27) { // Escape character
                i = inAndOut.handleArrowKey(i, 3);
                printMenu(i);
            }
            if (terminal.getWidth() != width) {
                width = terminal.getWidth();
                terminal.puts(InfoCmp.Capability.clear_screen);
                printMenu(i);
            }
        }
        return i + 1;
    }

    /**
     * Prints the menu to the terminal.
     *
     * @param i the index of the currently selected menu option
     */
    private void printMenu(int i) {
        terminal.puts(Capability.cursor_address, 0, 0);
        System.out.print(menu.menu(i, width));
    }

    /**
     * Processes the selected menu option and performs the corresponding action.
     *
     * @param selectedOption the selected menu option index
     */
    private void processMenuSelection(int selectedOption) {
        switch (selectedOption) {
            case 3:
                terminal.puts(Capability.cursor_visible);
                System.exit(0);
                break;
            case 2:
                loadGame();
                break;
            case 1:
                startGameLoop();
                break;
            default:
                break;
        }
    }

    /**
     * Loads a saved game from a file and starts the game loop.
     */
    private void loadGame() {
        Object[] objects = file.read("src/main/resources/output.xml");
        Board b = (Board) objects[0];
        board.deepCopy(b.getH(), b.getW(), b.getB());
        player1 = (Player) objects[1];
        startGameLoop();
    }

    /**
     * Asks the player for their name and updates the database with the new name.
     */
    private void askName() {
        terminal.puts(Capability.cursor_visible);
        drawer.ask_name();
        String name = inAndOut.read_string();
        dbd.update(name, 1, 0);
    }

    /**
     * Handles the column selection process for a player.
     *
     * @param p the player making the move
     * @return the selected column index
     * @throws IOException if an I/O error occurs
     */
    private int handleColSelection(Player p) throws IOException {
        int i = 0;
        int flip = 1;
        terminal.puts(Capability.clear_screen);
        terminal.puts(Capability.cursor_address, 0, 0);
        System.out.print(drawer.draw_Board(0, p));

        boolean isMenuOpen = true;
        while (isMenuOpen) {
            int key = terminal.reader().read();
            if (key >= '0' && key <= '9') {
                int key2 = terminal.reader().read(300);
                if (key2 == -2) {
                    i = key - '0';
                } else {
                    i = (key - '0') * 10 + key2 - '0';
                }
                isMenuOpen = false;
            } else if (key == 13) {
                isMenuOpen = false;
            } else if (key == 'q') {
                processMenuSelection(3);
            } else if (key == 's') {
                file.write("src/main/resources/output.xml", board, player1);
                System.exit(0);
            } else if (key == 'r') {
                terminal.puts(Capability.clear_screen);
                board.clear();
                startGameLoop();
            } else if (key == 27) { // Escape character
                i = inAndOut.handleArrowKey(i, board.getW());
            }
            terminal.puts(Capability.cursor_address, 0, 0);
            System.out.print(drawer.draw_Board((i) * flip, p));
        }

        return i;
    }

    /**
     * Starts the main game loop, alternating between player moves.
     */
    private void startGameLoop() {
        terminal.puts(Capability.clear_screen);
        System.out.print(drawer.draw_Board(-1, player1));

        while (true) {
            handlePlayerMove(player1);
            handlePlayerMove(player2);
        }
    }

    /**
     * Handles a player's move, including column selection, move validation, and animation.
     *
     * @param p the player making the move
     */
    private void handlePlayerMove(Player p) {
        int row = -1;
        int col = -1;
        while (row == -1) {
            col = getPlayerMove(p);
            row = stepper.isStepValid(col, board);
        }

        animateMove(p, col, row);
        if (validator.validate(board, col, row)) {
            announceWinner(p);
        }
    }

    /**
     * Gets the player's move by reading input from the terminal.
     *
     * @param p the player making the move
     * @return the selected column index
     */
    private int getPlayerMove(Player p) {
        int col = -1;
        if ("dumb".equals(terminal.getType())) {
            col = inAndOut.read() - 1;
        } else {
            try {
                col = handleColSelection(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return col;
    }

    /**
     * Animates the player's move on the board.
     *
     * @param p the player making the move
     * @param col the column index
     * @param row the row index
     */
    private void animateMove(Player p, int col, int row) {
        if (!"dumb".equals(terminal.getType())) {
            board.setI(col, 0, p.getId());
            terminal.puts(Capability.clear_screen);
            System.out.print(drawer.draw_Board(-55, p));
            for (int j = 1; j <= row; j++) {
                try {
                    Thread.sleep(100 - j * 5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.setI(col, j - 1, 0);
                board.setI(col, j, p.getId());
                terminal.puts(Capability.cursor_address, 0, 0);
                System.out.print(drawer.draw_Board(-551, p));
            }
        } else {
            board.setI(col, row, p.getId());
            String move = drawer.draw_Board(-55, p);
            System.out.print(move);
        }
    }

    /**
     * Announces the winner of the game and updates the database with the winner's name.
     *
     * @param p the player who won the game
     */
    private void announceWinner(Player p) {
        String winner = "Player" + p.getId() + " wins";
        AttributedStyle style = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK);
        style = style.background(p.getColor());
        AttributedString attributeText = new AttributedString(winner, style);
        System.out.println("\n" + attributeText.toAnsi());
        if (p.getId() == 1) {
            askName();
        }
        System.exit(0);
    }
}