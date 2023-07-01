package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

class FindIndexTest {

    @Test
    void whenElementNotFound() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip"};
        int result = forkJoinPool.invoke(new FindIndex<>(array, "artur", 0, array.length - 1));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenDifferentDataTypes() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip"};
        int result = forkJoinPool.invoke(new FindIndex<>(array, 324, 0, array.length - 1));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenElementIsFound() {
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip",
                "asdas", "iwquy", "iopip", "asdas", "iwquy", "iopip",
                "asdas", "iwquy", "iopip", "asdas", "iwquy", "iopip"};
        assertThat(FindIndex.search(array, "asd")).isEqualTo(3);
    }
}