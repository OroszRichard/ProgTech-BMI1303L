package org.example.Player;

import java.util.Scanner;

public class PlayerInteraction {
    private static final Scanner scanner = new Scanner(System.in);

    public static String askPlayerName() {
        System.out.print("Kérlek add meg a neved: ");
        return scanner.nextLine();
    }

    public static int getColumnInput() {
        System.out.print("Add meg az oszlop számát (1-től kezdve): ");
        return scanner.nextInt();
    }
}
