package org.example;

import java.util.Random;

public class Table {
    private int[][] table;
    private static int max = 2;
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
                    shiftUp(i);
                }
                System.out.println("Moved up");
            }
            case Direction.DOWN -> {
                for (int i = 0; i < n; i++) {
                    shiftDown(i);
                }
                System.out.println("Moved down");
            }
            case Direction.LEFT -> {
                for (int i = 0; i < n; i++) {
                    shiftLeft(i);
                }
                System.out.println("Moved left");
            }
            case Direction.RIGHT -> {
                for (int i = 0; i < n; i++) {
                    shiftRight(i);
                }
                System.out.println("Moved right");
            }
            default -> throw new IllegalArgumentException("invalid direction");
        }
        generateNumberInRandomPlace();
    }

    private void shiftUp(int column) {
        int n = table.length;
        int[] temp = new int[n];
        int idx = 0;

        for (int row = 0; row < n; row++) {
            if (table[row][column] != 0) {
                temp[idx++] = table[row][column];
            }
        }

        int[] temp2 = getNewGridAfterMergeNeighborNumbers(n, temp, idx);

        for (int i = 0; i < n; i++) {
            table[i][column] = temp2[i];
        }
    }

    private void shiftDown(int column) {
        int n = table.length;
        int[] temp = new int[n];
        int idx = 0;

        for (int row = n - 1; row >= 0; row--) {
            if (table[row][column] != 0) {
                temp[idx++] = table[row][column];
            }
        }

        int[] temp2 = getNewGridAfterMergeNeighborNumbers(n, temp, idx);

        for (int i = n - 1; i >= 0; i--) {
            table[i][column] = temp2[n - 1 - i];
        }
    }

    private void shiftLeft(int row) {
        int n = table.length;
        int[] temp = new int[n];
        int idx = 0;

        for (int col = 0; col < n; col++) {
            if (table[row][col] != 0) {
                temp[idx++] = table[row][col];
            }
        }

        int[] temp2 = getNewGridAfterMergeNeighborNumbers(n, temp, idx);

        for (int col = 0; col < n; col++) {
            table[row][col] = temp2[col];
        }
    }

    private void shiftRight(int row) {
        int n = table.length;
        int[] temp = new int[n];
        int idx = 0;

        for (int col = n - 1; col >= 0; col--) {
            if (table[row][col] != 0) {
                temp[idx++] = table[row][col];
            }
        }

        int[] temp2 = getNewGridAfterMergeNeighborNumbers(n, temp, idx);

        for (int col = n - 1; col >= 0; col--) {
            table[row][col] = temp2[n - 1 - col];
        }
    }

    private int[] getNewGridAfterMergeNeighborNumbers(int n, int[] temp, int idx) {
        for (int i = 0; i < idx - 1; i++) {
            if (temp[i] == temp[i + 1]) {
                temp[i] *= 2;
                temp[i + 1] = 0;

                if (temp[i] > max) {
                    max = temp[i];
                }
            }
        }

        int[] temp2 = new int[n];
        int idx2 = 0;
        for (int i : temp) {
            if (i != 0) {
                temp2[idx2++] = i;
            }
        }

        return temp2;
    }

    public void newGame(int row, int col) {
        this.table = new int[row][col];
       generateNumberInRandomPlace();
    }

    public void printCurrentMax() {
        System.out.println("Max: " + max);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void generateNumberInRandomPlace() {
        Random rand = new Random();
        int n = table.length;

        if (!emptyCellsExist()) {
            isGameOver = true;
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
    enum Direction {
        UP(1),
        LEFT(3),
        DOWN(2),
        RIGHT(4);

        public final int direction;

        private Direction(int direction) {
            this.direction = direction;
        }
    }
}
