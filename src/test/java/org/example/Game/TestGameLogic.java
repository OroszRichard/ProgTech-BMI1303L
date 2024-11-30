package org.example.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGameLogic {

    private char[][] board;

    @BeforeEach
    void setUp() {
        board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '-'; // Kezdő állapot: minden mező üres
            }
        }
    }

    @Test
    void testIsValidMoveValid() {
        // Érvényes lépés, ha az oszlop első sora üres
        assertTrue(GameLogic.isValidMove(board, 4)); // Például az 4-es oszlop (index 3)
    }

    @Test
    void testIsValidMoveInvalid() {
        // Tegyük a 3-as oszlopot tele
        for (int i = 0; i < 6; i++) {
            board[i][3] = 'R';
        }
        assertFalse(GameLogic.isValidMove(board, 4)); // Már nem érvényes lépés
    }

    @Test
    void testApplyMove() {
        // Tegyünk le egy korongot az 1-es oszlopba
        GameLogic.applyMove(board, 1, 'Y');
        assertEquals('Y', board[5][0]); // Az első üres hely az oszlopban az alján van
    }

    @Test
    void testGenerateRandomMove() {
        // Teszteljük, hogy a generált oszlop érvényes legyen
        int column = GameLogic.generateRandomMove(board);
        assertTrue(GameLogic.isValidMove(board, column));
    }

    @Test
    void testCheckWinHorizontal() {
        // Vízszintes győzelem tesztelése
        board[5][0] = 'Y';
        board[5][1] = 'Y';
        board[5][2] = 'Y';
        board[5][3] = 'Y';
        assertTrue(GameLogic.checkWin(board, 'Y'));
    }

    @Test
    void testCheckWinVertical() {
        // Függőleges győzelem tesztelése
        board[0][0] = 'R';
        board[1][0] = 'R';
        board[2][0] = 'R';
        board[3][0] = 'R';
        assertTrue(GameLogic.checkWin(board, 'R'));
    }

    @Test
    void testCheckWinDiagonal() {
        // Átlós győzelem tesztelése
        board[0][0] = 'Y';
        board[1][1] = 'Y';
        board[2][2] = 'Y';
        board[3][3] = 'Y';
        assertTrue(GameLogic.checkWin(board, 'Y'));
    }

    @Test
    void testCheckWinAntiDiagonal() {
        // Ellentétes átlós győzelem tesztelése
        board[0][3] = 'R';
        board[1][2] = 'R';
        board[2][1] = 'R';
        board[3][0] = 'R';
        assertTrue(GameLogic.checkWin(board, 'R'));
    }

    @Test
    void testCheckWinNoWin() {
        // Nincs győzelem
        assertFalse(GameLogic.checkWin(board, 'Y'));
    }
}
