package org.example.models;

/**
 * Model osztály a játokos eltárolására.
 */

public class Player {



    private  int id;
    private  String name;
    private  int color;
    private  String symbol;
    private  boolean isHuman;

    /**
     *  Változok bekérése.
     */

    public Player(int id, String name, int color, String symbol, boolean isHuman) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.symbol = symbol;
        this.isHuman = isHuman;
    }

    public boolean isHuman() {
        return isHuman;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }
}
