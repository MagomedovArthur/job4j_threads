package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (result[matrix.length - 1] == null) {
                    result[j] = new Sums(0, 0);
                    result[j].setColSum(result[j].getColSum() + matrix[i][j]);
                } else if (result[matrix.length - 1] != null) {
                    result[j].setColSum(result[j].getColSum() + matrix[i][j]);
                }
                rowSum += matrix[i][j];
            }
            result[i].setRowSum(rowSum);
        }
        return result;
    }

    public static CompletableFuture<Sums[]> asyncSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> {
                    return sum(matrix);
                }
        );
    }
}