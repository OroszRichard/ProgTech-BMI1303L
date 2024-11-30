package org.example.Game;

public class GameLogic {

    public static boolean isValidMove(char[][] board, int column) {
        // oszlop (1-től indul)
        int Column = column - 1;
        return Column >= 0 && Column < board[0].length && board[0][Column] == '-';
    }

    public static void applyMove(char[][] board, int column, char symbol) {
        // oszlop (1-től indul)
        int Column = column - 1;
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][Column] == '-') {
                board[row][Column] = symbol;
                break;
            }
        }
        System.out.println("Gép által választott oszlop: " + column);
    }

    public static int generateRandomMove(char[][] board) {
        int column;
        do {
            column = (int) (Math.random() * board[0].length)+1 ; // Véletlen oszlop 1-től indulva
        } while (!isValidMove(board, column));
        return column;

    }

    public static boolean checkWin(char[][] board, char symbol) {
        int rows = board.length;
        int cols = board[0].length;

        // Vízszintes ellenőrzés
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                if (board[row][col] == symbol && board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol && board[row][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Függőleges ellenőrzés
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row <= rows - 4; row++) {  //
                if (board[row][col] == symbol && board[row + 1][col] == symbol &&
                        board[row + 2][col] == symbol && board[row + 3][col] == symbol) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (bal felső - jobb alsó)
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                if (board[row][col] == symbol && board[row + 1][col + 1] == symbol &&
                        board[row + 2][col + 2] == symbol && board[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (jobb felső - bal alsó)
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 3; col < cols; col++) {
                if (board[row][col] == symbol && board[row + 1][col - 1] == symbol &&
                        board[row + 2][col - 2] == symbol && board[row + 3][col - 3] == symbol) {
                    return true;
                }
            }
        }

        return false; // Nincs győzelem
    }
}
