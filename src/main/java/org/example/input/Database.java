package org.example.input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.models.HighScore;

/**
 * The Database class provides methods to interact with a SQLite database.
 * It allows creating a new table, updating records, and reading high scores.
 */
public class Database {
    private String url;

    /**
     * Creates a new table in the database if it does not already exist.
     *
     * @param fileName the name of the database file
     */
    public void createNewTable(String fileName) {
        url = "jdbc:sqlite:" + fileName;
        String sql = "CREATE TABLE IF NOT EXISTS Highscore (" +
                "name VARCHAR(10) PRIMARY KEY, " +
                "winScore INT, " +
                "loseScore INT);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the high score of a player. If the player does not exist, a new record is inserted.
     *
     * @param name the name of the player
     * @param winScore the number of wins to add
     * @param loseScore the number of losses to add
     */
    public void update(String name, int winScore, int loseScore) {
        String sql = "INSERT INTO Highscore (name, winScore, loseScore) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT(name) DO UPDATE SET " +
                "winScore = winScore + ?, " +
                "loseScore = loseScore + ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, winScore);
            pstmt.setInt(3, loseScore);
            pstmt.setInt(4, winScore);
            pstmt.setInt(5, loseScore);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the high scores from the database and returns them as a HighScore object.
     *
     * @return a HighScore object containing the high scores, or null if an error occurs
     */
    public HighScore read() {
        String sql = "SELECT name, winScore, loseScore FROM Highscore ORDER BY winScore DESC";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            HighScore hs = new HighScore();
            while (rs.next()) {
                hs.addName(rs.getString("name"));
                hs.addWinScore(rs.getInt("winScore"));
                hs.addLoseScore(rs.getInt("loseScore"));
            }

            return hs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}