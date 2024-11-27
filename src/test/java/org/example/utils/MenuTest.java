package org.example.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.ui.Menu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }



    @Test
    void menuWithThirdOptionSelected() throws IOException {
        String expected = " \u001B[101;30m\u001B[101;30m _______  \u001B[103;30m  _______  \u001B[101;30m  _        \u001B[103;30m  _        \u001B[101;30m  _______  \u001B[103;30m _________ \u001B[101;30m  _______  \u001B[103;30m     ___   \u001B[0m\n" +
                " \u001B[101;30m(  ____ \\ \u001B[103;30m (  ___  ) \u001B[101;30m ( (    /| \u001B[103;30m ( (    /| \u001B[101;30m (  ____ \\ \u001B[103;30m \\__   __/ \u001B[101;30m (  ____ \\ \u001B[103;30m    /   )  \u001B[0m\n" +
                " \u001B[101;30m| (    \\/ \u001B[103;30m | (   ) | \u001B[101;30m |  \\  ( | \u001B[103;30m |  \\  ( | \u001B[101;30m | (    \\/ \u001B[103;30m    ) (    \u001B[101;30m | (    \\/ \u001B[103;30m   / /) |  \u001B[0m\n" +
                " \u001B[101;30m| |       \u001B[103;30m | |   | | \u001B[101;30m |   \\ | | \u001B[103;30m |   \\ | | \u001B[101;30m | (__     \u001B[103;30m    | |    \u001B[101;30m | |       \u001B[103;30m  / (_) (_ \u001B[0m\n" +
                " \u001B[101;30m| |       \u001B[103;30m | |   | | \u001B[101;30m | (\\ \\) | \u001B[103;30m | (\\ \\) | \u001B[101;30m |  __)    \u001B[103;30m    | |    \u001B[101;30m | |       \u001B[103;30m (____   _)\u001B[0m\n" +
                " \u001B[101;30m| |       \u001B[103;30m | |   | | \u001B[101;30m | | \\   | \u001B[103;30m | | \\   | \u001B[101;30m | (       \u001B[103;30m    | |    \u001B[101;30m | |       \u001B[103;30m      ) (  \u001B[0m\n" +
                " \u001B[101;30m| (____/\\ \u001B[103;30m | (___) | \u001B[101;30m | )  \\  | \u001B[103;30m | )  \\  | \u001B[101;30m | (____/\\ \u001B[103;30m    | |    \u001B[101;30m | (____/\\ \u001B[103;30m      | |  \u001B[0m\n" +
                " \u001B[101;30m(_______/ \u001B[103;30m (_______) \u001B[101;30m |/    )_) \u001B[103;30m |/    )_) \u001B[101;30m (_______/ \u001B[103;30m    )_(    \u001B[101;30m (_______/ \u001B[103;30m      (_)  \u001B[0m\n" +
                " \u001B[101;30m          \u001B[103;30m           \u001B[101;30m           \u001B[103;30m           \u001B[101;30m           \u001B[103;30m           \u001B[101;30m           \u001B[103;30m           \u001B[0m\n" +
                "                                        J Á T É K   1\u001B[0m\n" +
                "                             P Á L Y A   B E O L V A S Á S A   2\u001B[0m\n" +
                "                                      K I L É P É S   3\u001B[0m\n" +
                " ";
        assertEquals(expected, menu.menu(-1, 0));
    }



}
