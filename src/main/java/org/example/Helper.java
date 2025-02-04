package org.example;

public class Helper {
    public static void printHelp() {
        System.out.println("Game ends when there is no place to generate new number.\n" +
                "Please enter up or u, down or d, left or l, right or r (not case sensitive) to make a move.\n" +
                "Enter help or h (not case sensitive) to show this message again.\n" +
                "Enter exit or e (not case sensitive) to exit the game.");
    }

    public static void clearScreen() {
        for(int i=0;i<100;i++) System.out.println();
    }

    public static void printError() {
        System.out.println("Input should be up, down, left, right (not case sensitive) to make a move.");
        System.out.println("Press any key to continue.");
    }
}
