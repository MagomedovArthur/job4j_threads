package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.pool.RowColSum.asyncSum;
import static ru.job4j.pool.RowColSum.sum;

class RowColSumTest {

    @Test
    void whenSum() {
        int[][] matrix = new int[][]{
                {3, 2, 8},
                {1, 9, 7},
                {4, 8, 2}
        };
        RowColSum.Sums[] actual = sum(matrix);
        assertThat(actual[1].getRowSum()).isEqualTo(17);
        assertThat(actual[1].getColSum()).isEqualTo(19);
        assertThat(actual[2].getColSum()).isNotZero();
    }

    @Test
    void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{
                {3, 2, 8},
                {1, 9, 7},
                {4, 8, 2}
        };
        RowColSum.Sums[] actual = asyncSum(matrix).get();
        assertThat(actual[1].getRowSum()).isEqualTo(17);
        assertThat(actual[1].getColSum()).isEqualTo(19);
        assertThat(actual[2].getColSum()).isNotZero();
    }
}