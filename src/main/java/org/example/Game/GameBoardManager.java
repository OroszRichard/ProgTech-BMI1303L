package org.example.Game;

import java.io.*;
import java.util.Arrays;

public class GameBoardManager {
    
    private static final String BOARD_FILE_PATH = "C:\\Users\\User\\Desktop\\OroszRichardprog tech 4.0\\game_board.txt";

    public static char[][] buildBoard(int rows, int cols) {
        char[][] board = new char[rows][cols];
        // A táblát alapértelmezett értékkel tölti fel
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], '-');
        }

        File file = new File(BOARD_FILE_PATH);
        // Ha létezik a fájl, akkor betölti az adatokat
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                for (int i = 0; i < rows; i++) {
                    String line = reader.readLine();
                    if (line != null) {
                        for (int j = 0; j < Math.min(cols, line.length()); j++) {
                            board[i][j] = line.charAt(j);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Hiba történt a játékállás betöltésekor: " + e.getMessage());
            }
        }
        return board;
    }

    public static void saveGame(char[][] board) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOARD_FILE_PATH))) {
            for (char[] row : board) {
                writer.write(new String(row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba történt a játékállás mentésekor: " + e.getMessage());
        }
    }

    public static void printBoard(char[][] board) {
        System.out.print("  ");
        // Oszlopok betűk szerinti kiírása
        for (int i = 0; i < board[0].length; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
        // Sorok kiírása
        for (int i = 0; i < board.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
