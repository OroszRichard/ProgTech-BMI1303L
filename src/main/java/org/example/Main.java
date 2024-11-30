package org.example;

import org.example.Game.GameBoardManager;
import org.example.Game.GameLogic;
import org.example.Player.PlayerInteraction;
import org.example.SaveLoad.HighScoreManager;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
        public static void main(String[] args) {
       
        logger.info("Játék elindult");

        int rows = 6;
        int cols = 7;

        // A játék állapotának betöltése fájlból
        char[][] board = GameBoardManager.buildBoard(rows, cols);

        // High score kezelése
        HighScoreManager highScoreManager = new HighScoreManager();

        // Játékos neve
        String playerName = PlayerInteraction.askPlayerName();

        // Játék kezdete
        System.out.println("Játék kezdete:");
        GameBoardManager.printBoard(board);

        boolean gameRunning = true;

            while (gameRunning) {
                // Játékos lépése
                int column = PlayerInteraction.getColumnInput();
                // Érvényes lépés ellenőrzése
                if (GameLogic.isValidMove(board, column)) {
                    GameLogic.applyMove(board, column, 'Y');
                    GameBoardManager.printBoard(board);
                    GameBoardManager.saveGame(board);

                    // A győzelem ellenőrzése a játékos részéről
                    if (GameLogic.checkWin(board, 'Y')) {
                        System.out.println("Gratulálunk, " + playerName + ", nyertél!");
                        highScoreManager.saveWin(playerName);
                        gameRunning = false;
                        continue; // Ne hagyjuk, hogy a gép még lépjen
                    }
                } else {
                    System.out.println("Érvénytelen lépés, próbáld újra!");
                    continue; // Ugorj vissza a ciklus elejére, ha érvénytelen a lépés
                }

                // Gépi lépés
                int computerMove = GameLogic.generateRandomMove(board);
                GameLogic.applyMove(board, computerMove, 'R');
                GameBoardManager.printBoard(board);

                // A gép győzelmének ellenőrzése
                if (GameLogic.checkWin(board, 'R')) {
                    System.out.println("A gép nyert. Majd legközelebb!");
                    gameRunning = false;
                }
            }
        // High score lista megjelenítése
        System.out.println();
        highScoreManager.displayHighScores();

        logger.info("Játék vége");
    }
}
