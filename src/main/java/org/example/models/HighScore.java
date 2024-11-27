package org.example.models;

import java.util.ArrayList;

/**
 * The HighScore class represents a collection of high scores for a game.
 * It maintains lists of player names, win scores, and lose scores.
 */
public class HighScore {
    private final ArrayList<String> names;
    private final ArrayList<Integer> winScores;
    private final ArrayList<Integer> loseScores;
    private int size;

    /**
     * Constructs a new HighScore object with empty lists for names, win scores, and lose scores.
     */
    public HighScore() {
        this.names = new ArrayList<>();
        this.winScores = new ArrayList<>();
        this.loseScores = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Returns the list of player names.
     *
     * @return the list of player names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * Returns the list of win scores.
     *
     * @return the list of win scores
     */
    public ArrayList<Integer> getWinScores() {
        return winScores;
    }

    /**
     * Returns the list of lose scores.
     *
     * @return the list of lose scores
     */
    public ArrayList<Integer> getLoseScores() {
        return loseScores;
    }

    /**
     * Returns the name of the player at the specified index.
     *
     * @param i the index of the player
     * @return the name of the player at the specified index
     */
    public String getNameAt(int i) {
        return names.get(i);
    }

    /**
     * Adds a player name to the list and increments the size.
     *
     * @param name the name of the player to add
     */
    public void addName(String name) {
        this.names.add(name);
        this.size = this.size + 1;
    }

    /**
     * Adds a player name, win score, and lose score to the respective lists and increments the size.
     *
     * @param name the name of the player
     * @param winScores the win score of the player
     * @param loseScores the lose score of the player
     */
    public void addScore(String name, int winScores, int loseScores) {
        this.names.add(name);
        this.winScores.add(winScores);
        this.loseScores.add(loseScores);
        this.size = this.size + 1;
    }

    /**
     * Returns the number of players in the high score list.
     *
     * @return the number of players in the high score list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the win score of the player at the specified index.
     *
     * @param i the index of the player
     * @return the win score of the player at the specified index
     */
    public int getWinScoreAt(int i) {
        return winScores.get(i);
    }

    /**
     * Adds a win score to the list.
     *
     * @param winScores the win score to add
     */
    public void addWinScore(int winScores) {
        this.winScores.add(winScores);
    }

    /**
     * Returns the lose score of the player at the specified index.
     *
     * @param i the index of the player
     * @return the lose score of the player at the specified index
     */
    public int getLoseScoreAt(int i) {
        return loseScores.get(i);
    }

    /**
     * Adds a lose score to the list.
     *
     * @param loseScores the lose score to add
     */
    public void addLoseScore(int loseScores) {
        this.loseScores.add(loseScores);
    }
}