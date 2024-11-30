package org.example.utils;

import org.example.input.File;
import org.example.input.InAndOutput;
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

    @BeforeEach
    public void setUp() {
        game = spy(new Game());
        game.initializeGameComponents();
        file = mock(File.class);
        game.setFile(file);

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
    public void testHandleColSelectionLetterKeys() throws IOException {

        Player player = new Player(1, "Player 1", 9, "X", true);

        when(inAndOut.terminalRead(300))
                .thenReturn((int) 'r').thenReturn(13);
        // Mock the terminal reader to simulate user input
        when(inAndOut.terminalRead(300))
                .thenReturn((int) 's').thenReturn(13);
        // Mock the drawer to avoid actual drawing
        when(drawer.draw_Board(anyInt(), anyInt(), any(Player.class))).thenReturn("");
        doNothing().when(game).save();
        int col = game.handleColSelection(player);
        verify(game).save();
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
    public void testGetPlayerMove2() throws IOException {
        when(inAndOut.isTerminalDumb()).thenReturn(false);

        doThrow(new IOException()).when(game).handleColSelection(player);


        assertEquals(-1, game.getPlayerMove(player));



    }



    @Test
    public void testHandleMenuSelection() throws IOException {
        // Mock the terminal type to be "dumb"

        when(inAndOut.isTerminalDumb()).thenReturn(true);

        // Mock the inAndOut.read() method to return a specific value
        when(inAndOut.read()).thenReturn(1);


        // Call the method and assert the expected result
        int result = game.handleMenuSelection();
        assertEquals(1, result);


    }

    @Test
    public void testProcessMenuSelection() throws IOException {

        doNothing().when(game).startGameLoop();
        doNothing().when(game).loadGame();
        doNothing().when(game).exit();


        game.processMenuSelection(1);
        verify(game).startGameLoop();;

        game.processMenuSelection(2);
        verify(game).loadGame();;

        game.processMenuSelection(3);
        verify(game).exit();;

        game.processMenuSelection(4);

    }

    @Test
    public void testStart(){





    }









}