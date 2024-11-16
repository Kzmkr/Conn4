package org.example.models;

/**
 * Model osztály a játokos eltárolására.
 */

public class Player {



    private final int id;
    private final String name;
    private final int color;
    private final String symbol;

    /**
     *  Változok bekérése.
     */

    public Player(int id, String name, int color, String symbol) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
}
