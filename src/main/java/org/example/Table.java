package org.example;

import java.util.Random;

import static org.example.Mover.getMax;
import static org.example.Mover.shiftDown;
import static org.example.Mover.shiftLeft;
import static org.example.Mover.shiftRight;
import static org.example.Mover.shiftUp;

public class Table {
    private int[][] table;
    private static boolean isGameOver = false;

    public Table(int row, int col) {
        if (row != col) {
            throw new IllegalArgumentException("row and col are not the same");
        }
        newGame(row, col);
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(".___.___.___.___.\n").append("|");
        for (int[] ints : table) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] == 0) {
                    sb.append("___|");
                } else {
                    sb.append("_").append(ints[j]).append("_|");
                }

                if (j == ints.length - 1) {
                    sb.append("\n|");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public void moveDirection(Direction direction) {
        int n = table.length;
        switch (direction) {
            case Direction.UP -> {
                for (int i = 0; i < n; i++) {
                    shiftUp(i, table);
                }
                System.out.println("Moved up");
            }
            case Direction.DOWN -> {
                for (int i = 0; i < n; i++) {
                    shiftDown(i, table);
                }
                System.out.println("Moved down");
            }
            case Direction.LEFT -> {
                for (int i = 0; i < n; i++) {
                    shiftLeft(i, table);
                }
                System.out.println("Moved left");
            }
            case Direction.RIGHT -> {
                for (int i = 0; i < n; i++) {
                    shiftRight(i, table);
                }
                System.out.println("Moved right");
            }
            default -> throw new IllegalArgumentException("invalid direction");
        }
        generateNumberInRandomPlace();
    }

    public void newGame(int row, int col) {
        this.table = new int[row][col];
        setIsGameOver(false);
        generateNumberInRandomPlace();
    }

    public void printCurrentMax() {
        System.out.println("Max: " + getMax());
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        Table.isGameOver = isGameOver;
    }

    private void generateNumberInRandomPlace() {
        Random rand = new Random();
        int n = table.length;

        if (!emptyCellsExist()) {
            setIsGameOver(checkSameNeighbourExist());
            return;
        }

        int x = rand.nextInt(n);
        int y = rand.nextInt(n);

        while (table[x][y] != 0) {
            x = rand.nextInt(n);
            y = rand.nextInt(n);
        }

        table[x][y] = 2;
    }

    private boolean emptyCellsExist() {
        int n = table.length;
        int emptyCells = 0;

        for (int[] ints : table) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 0) {
                    emptyCells++;
                }
            }
        }

        return emptyCells > 0;
    }

    private boolean checkSameNeighbourExist() {
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = 0; j < table.length - 1; j++) {
                if (table[i][j] != table[i][j + 1] || table[i][j] == table[i + 1][j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
