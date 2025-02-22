package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);

        Helper.printHelp();
        Table table = new Table(4);
        do {
            table.printTable();
            table.printCurrentMax();
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("up") || input.equalsIgnoreCase("u")) {
                table.moveDirection(Direction.UP);
                Helper.clearScreen();
            } else if (input.equalsIgnoreCase("down") || input.equalsIgnoreCase("d")) {
                table.moveDirection(Direction.DOWN);
                Helper.clearScreen();
            } else if (input.equalsIgnoreCase("left") || input.equalsIgnoreCase("l")) {
                table.moveDirection(Direction.LEFT);
                Helper.clearScreen();
            } else if (input.equalsIgnoreCase("right") || input.equalsIgnoreCase("r")) {
                table.moveDirection(Direction.RIGHT);
                Helper.clearScreen();
            } else if (input.equalsIgnoreCase("h") || input.equalsIgnoreCase("help")) {
                Helper.printHelp();
            } else if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else {
                Helper.printError();
                scanner.nextLine();
            }
        } while (!table.isGameOver());
        System.out.println("GG loshara");
        scanner.close();
        System.out.close();
    }
}