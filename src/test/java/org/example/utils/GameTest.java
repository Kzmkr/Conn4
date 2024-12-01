package org.example.utils;

import org.example.input.Database;
import org.example.input.File;
import org.example.input.InAndOutput;
import org.example.models.Board;
import org.example.models.Player;

import org.example.ui.Draw;
import org.example.ui.Menu;
import org.jline.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
public class GameTest {

    private Game game;
    private InAndOutput inAndOut;
    private Draw drawer;
    private Player player;
    private Terminal terminal;
    private File file;
    private Menu menu;
    private Step stepper;
    private Validate validator;

    @BeforeEach
    public void setUp() {
        game = spy(new Game());
        game.initializeGameComponents();
        file = mock(File.class);
        game.setFile(file);

        stepper = mock(Step.class);
        game.setStepper(stepper);

        validator = mock(Validate.class);
        game.setValidator(validator);

        menu = mock(Menu.class);
        game.setMenu(menu);

        drawer = mock(Draw.class);
        player = new Player(1, "Player 1", 9, "X", true);

        // Set the terminal and drawer in the game instance
        terminal = Mockito.mock(Terminal.class);
        game.setTerminal(terminal);
        inAndOut = Mockito.mock(InAndOutput.class);
        game.setInAndOut(inAndOut);
        drawer = Mockito.mock(Draw.class);
        game.setDrawer(drawer);

    }



    @Test
    public void testHandleColSelectionNumberKeys() throws IOException {

        Player player = new Player(1, "Player 1", 9, "X", true);

        // Mock the terminal reader to simulate user input
        when(inAndOut.terminalRead(300))
                .thenReturn((int) '1') // Simulate pressing '9'
                .thenReturn((int)'0'); // Simulate end of input

        // Mock the drawer to avoid actual drawing
        when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");

        int col = game.handleColSelection(player);
        assertEquals(9, col); // Expecting 8 because the method returns i - 1
    }

    @Test
    public void testHandleColSelectionLetterKeysS() throws IOException {

        Player player = new Player(1, "Player 1", 9, "X", true);


        when(inAndOut.terminalRead(300))
                .thenReturn((int) 's').thenReturn(13);
        // Mock the drawer to avoid actual drawing
        when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");
        doNothing().when(game).save();
        int col = game.handleColSelection(player);
        verify(game).save();
        }
    @Test
    public void testHandleColSelectionLetterKeysE() throws IOException {

        Player player = new Player(1, "Player 1", 9, "X", true);

        when(inAndOut.terminalRead(300))
                .thenReturn((int) 'e').thenReturn(13);
        // Mock the terminal reader to simulate user input

        when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");
        doNothing().when(game).exit();
        int col = game.handleColSelection(player);
        verify(game).exit();
    }
    @Test
    public void testHandleColSelectionLetterKeysR() throws IOException {

        Player player = new Player(1, "Player 1", 9, "X", true);

        when(inAndOut.terminalRead(300))
                .thenReturn((int) 'r').thenReturn(13);
        // Mock the terminal reader to simulate user input

        when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");
        doNothing().when(game).startGameLoop();
        int col = game.handleColSelection(player);
        verify(game).startGameLoop();
    }
    @Test

    public void testHandleMenuSelectionNotDumb()  {

        when(inAndOut.isTerminalDumb()).thenReturn(false);

        // Mock the inAndOut.read() method to return a specific value
        when(inAndOut.terminalRead(100)).thenReturn(27).thenReturn(13); // Simulate pressing '9';
        doNothing().when(game).printMenu(anyInt());
        game.setWidth(1);
        // Call the method and assert the expected result
        int result = game.handleMenuSelection();
        assertEquals(1, result);


    }

    @Test
    public void testBotMove(){
        doNothing().when(game).animateMove(any(), anyInt(), anyInt());
        game.botmove(player);
        verify(game).animateMove(any(), anyInt(), anyInt());
    }

    @Test
    public void testGetPlayerMove() throws IOException {
        when(inAndOut.isTerminalDumb()).thenReturn(true);
        when(inAndOut.read())
                .thenReturn(1);

        assertEquals(0, game.getPlayerMove(player));

    }





    @Test
    public void testHandleMenuSelection()  {
        // Mock the terminal type to be "dumb"

        when(inAndOut.isTerminalDumb()).thenReturn(true);

        // Mock the inAndOut.read() method to return a specific value
        when(inAndOut.read()).thenReturn(1);


        // Call the method and assert the expected result
        int result = game.handleMenuSelection();
        assertEquals(1, result);


    }

    @Test
    public void testProcessMenuSelection()  {

        doNothing().when(game).startGameLoop();
        doNothing().when(game).loadGame();
        doNothing().when(game).exit();


        game.processMenuSelection(1);
        verify(game).startGameLoop();

        game.processMenuSelection(2);
        verify(game).loadGame();

        game.processMenuSelection(3);
        verify(game).exit();

        game.processMenuSelection(4);

    }

   @Test
    public void saveWritesToFile() {
   when(file.write(anyString(), any(Board.class), any(Player.class))).thenReturn(true);
    doNothing().when(game).exit();

    game.save();

    verify(file).write(anyString(), any(Board.class), any(Player.class));
       verify(game).exit();
}
@Test
public void animateMoveUpdatesBoard() {
    Board board = new Board(6, 7);
    game.setBoard(board);
    when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");
    game.animateMove(player, 3, 2);
    assertEquals(player.getId(), board.getI(3, 2));
}

@Test
public void loadGameLoadsBoardAndPlayer() {
    Board board = new Board(6, 7);
    game.setBoard(board);

    Board mockBoard = mock(Board.class);
    Player mockPlayer = mock(Player.class);
    Object[] objects = {mockBoard, mockPlayer};
    when(file.read("src/main/resources/output.xml")).thenReturn(objects);
    doNothing().when(game).startGameLoop();

    game.loadGame();
    assertEquals(mockPlayer, game.getPlayer1());
    verify(game).startGameLoop();
}

@Test
    public void Start(){
    doNothing().when(game).initializeGameComponents();
    doReturn(1).when(game).handleMenuSelection();
    doNothing().when(game).processMenuSelection(anyInt());

    game.start();
    verify(game, times(2)).initializeGameComponents();
    verify(game).processMenuSelection(anyInt());
    verify(game).handleMenuSelection();

}

    @Test
    public void testHandlePlayerMove() throws IOException {
        // Arrange
        Player player = new Player(1, "Player 1", 9, "X", true);
        Board board = new Board(6, 7);
        game.setBoard(board);

        // Mock the dependencies
        doReturn(1).when(game).getPlayerMove(player);
        when(stepper.isStepValid(anyInt(), eq(board))).thenReturn(1);
        doNothing().when(game).animateMove(any(Player.class), anyInt(), anyInt());
        when(validator.validate(eq(board), anyInt(), anyInt())).thenReturn(true);
        when(inAndOut.announceWinner(any(Player.class), anyString(), any(Database.class))).thenReturn("Player 1 wins!");
        doNothing().when(game).exit();
        // Act
        game.handlePlayerMove(player);

        // Assert
        verify(game).animateMove(player, 1, 1);
        verify(validator).validate(board, 1, 1);
        verify(inAndOut).announceWinner(any(Player.class), anyString(), any(Database.class));
        verify(game).exit();
    }








}