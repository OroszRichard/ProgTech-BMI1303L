package org.example.SaveLoad;

public class HighScoreManager extends HighScoreDatabase {
    private final HighScoreDatabase database;

    public HighScoreManager() {
        this.database = new HighScoreDatabase();
        database.initializeDatabase();
    }
    @Override
    public void saveWin(String playerName) {
        // Felülírt implementáció
        System.out.println("Pontszám mentése a HighScore-n: " + playerName);
    }

    public void displayHighScores() {
        database.displayHighScores();
    }
}
