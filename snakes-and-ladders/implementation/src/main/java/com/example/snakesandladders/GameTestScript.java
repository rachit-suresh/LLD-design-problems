package com.example.snakesandladders;

import com.example.snakesandladders.core.Game;
import com.example.snakesandladders.core.GameLoop;
import java.util.Scanner;

public class GameTestScript {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Snake and Ladder Test Script ===");

            int n = readPositiveInt(scanner, "Enter board dimension n (board will be n x n): ");
            int playersCount = readMinInt(scanner, "Enter number of players (minimum 2): ", 2);
            String difficulty = readDifficulty(scanner, "Enter difficulty (easy/hard): ");

            Game game = new Game(n, playersCount, difficulty);
            GameLoop gameLoop = new GameLoop(game);

            System.out.println("Starting game...");
            gameLoop.playGame();
            System.out.println("Session ended.");
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid value. Please enter a positive integer.");
        }
    }

    private static int readMinInt(Scanner scanner, String prompt, int minValue) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= minValue) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid value. Please enter an integer >= " + minValue + ".");
        }
    }

    private static String readDifficulty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.next().trim();
            if ("easy".equalsIgnoreCase(value) || "hard".equalsIgnoreCase(value)) {
                return value;
            }
            System.out.println("Invalid difficulty. Please type easy or hard.");
        }
    }
}