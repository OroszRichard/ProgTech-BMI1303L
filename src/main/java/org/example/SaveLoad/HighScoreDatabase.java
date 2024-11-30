package org.example.SaveLoad;

import java.sql.*;

public class HighScoreDatabase {

    private static final String DATABASE_PATH = "high_scores.db";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_PATH;

    public void initializeDatabase() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS high_scores (
                player_name TEXT PRIMARY KEY,
                wins INTEGER NOT NULL
            );
            """;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Az adatbázis betöltése sikeres.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Hiba az adatbázis betöltésekor: " + e.getMessage());
        }
    }

    public void saveWin(String playerName) {
        String insertOrUpdateSQL = """
            INSERT INTO high_scores (player_name, wins)
            VALUES (?, 1)
            ON CONFLICT(player_name) DO UPDATE SET
            wins = wins + 1;
            """;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertOrUpdateSQL)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
            System.out.println(playerName + " győzelme mentésre került.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Hiba történt a győzelem mentésekor: " + e.getMessage());
        }
    }

    public void displayHighScores() {
        String selectSQL = "SELECT player_name, wins FROM high_scores ORDER BY wins DESC";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            if (!rs.isBeforeFirst()) { // Ha nincs adat
                System.out.println("Nincs elérhető high score adat.");
                return;
            }

            System.out.println("High Scores:");
            while (rs.next()) {
                String playerName = rs.getString("player_name");
                int wins = rs.getInt("wins");
                System.out.println(playerName + ": " + wins + " győzelem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Hiba történt a high score beolvasásakor: " + e.getMessage());
        }
    }
}
