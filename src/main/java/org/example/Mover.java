package org.example;

public class Mover {
    private static int max = 0;

    public static void shiftUp(int column, int[][] table) {
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

    public static void shiftDown(int column, int[][] table) {
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

    public static void shiftLeft(int row, int[][] table) {
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

    public static void shiftRight(int row, int[][] table) {
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

    private static int[] getNewGridAfterMergeNeighborNumbers(int n, int[] temp, int idx) {
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

    public static int getMax() {
        return max;
    }
}
