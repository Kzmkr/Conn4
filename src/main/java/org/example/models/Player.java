package org.example.models;

/**
 * Model osztály a játokos eltárolására.
 */

public class Player {



    private final int id;
    private final String name;
    private final String color;
    private final String symbol;

    /**
     *  Változok bekérése.
     */

    public Player(int id, String name, String color, String symbol) {
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

    public String getColor() {
        return color;
    }
}
