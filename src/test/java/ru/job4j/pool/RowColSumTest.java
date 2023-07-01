package ru.job4j.pool;

import org.junit.jupiter.api.Test;

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
        Sums[] actual = sum(matrix);
        Sums[] expected = {
                new Sums(13, 8),
                new Sums(17, 19),
                new Sums(14, 17)
        };
        assertThat(actual).contains(expected);
    }

    @Test
    void whenAsyncSum() {
        int[][] matrix = new int[][]{
                {3, 2, 8},
                {1, 9, 7},
                {4, 8, 2}
        };
        Sums[] actual = asyncSum(matrix);
        Sums[] expected = {
                new Sums(13, 8),
                new Sums(17, 19),
                new Sums(14, 17)
        };
        assertThat(actual).contains(expected);
    }
}