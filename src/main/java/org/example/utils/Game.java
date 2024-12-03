package org.example.utils;

import java.io.IOException;
import java.util.Random;

import org.example.input.Database;
import org.example.input.File;
import org.example.input.InAndOutput;
import org.example.models.Board;
import org.example.models.Player;
import org.example.ui.Draw;
import org.example.ui.Menu;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;
import org.jline.utils.InfoCmp.Capability;



/**
 * The Game class manages the main game logic, including initialization, menu handling,
 * game loop, and player moves.
 */
public class Game {


    private final int boardWidth = 11;
    private final int boardHeight = 12;
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
    public void initializeGameComponents() {


        board = new Board(boardHeight, boardWidth);
        file = new File();
        menu = new Menu();
        validator = new Validate();
        stepper = new Step(board);
        inAndOut = new InAndOutput();
        terminal = inAndOut.setupTerminal();
        player1 = new Player(1, "player", 11, "O", true);
        player2 = new Player(2, "T-1000", 9, "X", false);
        drawer = new Draw(board, player1, player2);
        width = terminal.getWidth();

    }

    /**
     * Handles the menu selection process, including reading input and updating the menu display.
     *
     * @return the selected menu option index
     */
    public int handleMenuSelection() {

        if (inAndOut.isTerminalDumb()) {

            printMenu(- 1);
            return inAndOut.read();
        }

        int selectedIndex = 0;
        terminal.puts(Capability.clear_screen);
        printMenu(selectedIndex);
        boolean isMenuOpen = true;
        while (isMenuOpen) {
            int key = inAndOut.terminalRead(100);
            switch (key) {
                case 13: // Enter key
                    isMenuOpen = false;
                    break;
                case 27: // Escape character
                    selectedIndex = inAndOut.handleArrowKey(selectedIndex, 3);
                    printMenu(selectedIndex);
                    break;
                default:
                    break;
            }

            if (terminal.getWidth() != width) {
                width = terminal.getWidth();
                terminal.puts(InfoCmp.Capability.clear_screen);
                printMenu(selectedIndex);
            }
        }
        return selectedIndex + 1;
    }


    /**
     * Processes the selected menu option and performs the corresponding action.
     *
     * @param selectedOption the selected menu option index
     */
    public void processMenuSelection(int selectedOption) {
        switch (selectedOption) {
            case 3:
                terminal.puts(Capability.cursor_visible);
                exit();
                break;
            case 2:
                loadGame();
                break;
            case 1:
                player1.setName(askName());
                 startGameLoop();
                break;
            default:
                break;
        }
    }

    /**
     * Prints the menu to the terminal.
     *
     * @param i the index of the currently selected menu option
     */

    public void printMenu(int i) {
        terminal.puts(Capability.cursor_address, 0, 0);
        System.out.print(menu.menu(i, width));
    }

    /**
     * Loads a saved game from a file and starts the game loop.
     */
    public void loadGame() {
        Object[] objects = file.read("src/main/resources/output.xml");
        Board b = (Board) objects[0];
        board.deepCopy(b.getH(), b.getW(), b.getB());
        player1 = (Player) objects[1];
        startGameLoop();
    }

    /**
     * Asks the player for their name and updates the database with the new name.
     */
    public String askName() {
        terminal.puts(Capability.clear_screen);
        System.out.println("Enter your name: ");
        return inAndOut.readString();
    }

    /**
     * Handles the column selection process for a player.
     *
     * @param p the player making the move
     * @return the selected column index
     * @throws IOException if an I/O error occurs
     */
    public int handleColSelection(Player p) throws IOException {
        int i = 0;
        terminal.puts(Capability.clear_screen);
        System.out.print(drawer.draw_Board(0, - 1, p));

        boolean isMenuOpen = true;
        while (isMenuOpen) {
            int key = inAndOut.terminalRead(300);
            if (key >= '0' && key <= '9') {
                int key2 = inAndOut.terminalRead(300);
                if (key2 == - 2) {
                    i = key - '0';
                } else {
                    i = (key - '0') * 10 + key2 - '0';
                }

                isMenuOpen = false;
            } else {
                switch (key) {

                    case 13: // Enter key
                        isMenuOpen = false;
                        break;
                    case 'e':
                        terminal.puts(Capability.cursor_visible);
                        exit();
                        break;
                    case 's':
                        save();
                        break;
                    case 'r':
                        terminal.puts(Capability.clear_screen);
                        board.clear();
                        startGameLoop();
                        break;
                    case 27: // Escape character
                        i = inAndOut.handleArrowKey(i, board.getW() + 1);
                        break;
                    default:
                        break;
                }
            }
            terminal.puts(Capability.cursor_address, 0, 0);
            System.out.print(drawer.draw_Board(i, - 1, p));
        }

        return i - 1;
    }
    /**
     * Exits the application by terminating the JVM.
     */

    public void exit() {
        System.exit(0);
    }

    /**
     * Saves the current game state to a file and exits the application.
     */

    public void save() {
        System.out.println(board.getH());
        file.write("src/main/resources/output.xml", board, player1);
        terminal.puts(Capability.cursor_visible);
        exit();
    }



    /**
     * Starts the main game loop, alternating between player moves.
     */
    public void startGameLoop() {
        terminal.puts(Capability.clear_screen);
        System.out.print(drawer.draw_Board(- 1, - 1, player1));

        while (true) {
            handlePlayerMove(player1);
            botmove(player2);

        }
    }
    /**
     * Handles the bot's move by selecting a random valid column and animating the move.
     *
     * @param p the player representing the bot
     */

    public void botmove(Player p) {
        Random random = new Random();
        int row = - 1;
        int col = 0;
        while (row == - 1) {
            col = random.nextInt(11);
            row = stepper.isStepValid(col, board);
        }

        animateMove(p, col, row);
        checkWinner(p, col, row);
    }


    /**
     * Handles a player's move, including column selection, move validation, and animation.
     *
     * @param p the player making the move
     */
    public void handlePlayerMove(Player p) {
        int row = - 1;
        int col = - 1;
        while (row == - 1) {
            col = getPlayerMove(p);
            row = stepper.isStepValid(col, board);
        }

        animateMove(p, col, row);
        checkWinner(p, col, row);
    }

    private void checkWinner(Player p, int col, int row) {
        if (validator.validate(board, col, row)) {
            Database dbd = new Database();
            dbd.createNewTable("hs.db");
            String winner = inAndOut.announceWinner(p, player1.getName(), dbd);
            System.out.println(winner);
            exit();
        }
    }

    /**
     * Gets the player's move by reading input from the terminal.
     *
     * @param p the player making the move
     * @return the selected column index
     */
    public int getPlayerMove(Player p) {
        int col = - 1;
        if (inAndOut.isTerminalDumb()) {
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
     * @param p   the player making the move
     * @param col the column index
     * @param row the row index
     */
    public void animateMove(Player p, int col, int row) {
        if (! "dumb".equals(terminal.getType())) {
            for (int j = 0; j <= row; j++) {
                terminal.puts(Capability.cursor_address, 0, 0);
                System.out.print(drawer.draw_Board(col, j, p));
                try {
                    Thread.sleep(100 - j * 5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        board.setI(col, row, p.getId());
        String move = drawer.draw_Board(- 1, - 1, p);
        terminal.puts(Capability.cursor_address, 0, 0);
        System.out.print(move);

    }

    public Player getPlayer1() {
        return player1;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setInAndOut(InAndOutput inAndOut) {
        this.inAndOut = inAndOut;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setDrawer(Draw drawer) {
        this.drawer = drawer;
    }

    public void setValidator(Validate validator) {
        this.validator = validator;
    }

    public void setStepper(Step stepper) {
        this.stepper = stepper;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}