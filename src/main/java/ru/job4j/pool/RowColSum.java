package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;

public class RowColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            general(matrix, i, sums);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            CompletableFuture.runAsync(
                    () -> general(matrix, finalI, sums));
        }
        return sums;
    }

    private static void general(int[][] matrix, int i, Sums[] sums) {
        int row = 0;
        int col = 0;
        sums[i] = new Sums(0, 0);
        for (int j = 0; j < matrix.length; j++) {
            row += matrix[i][j];
            col += matrix[j][i];
        }
        sums[i].setRowSum(row);
        sums[i].setColSum(col);
    }
}