package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

class FindIndexTest {

    @Test
    void whenElementNotFound() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip"};
        int result = forkJoinPool.invoke(new FindIndex<>(array, "artur"));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenDifferentDataTypes() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip"};
        int result = forkJoinPool.invoke(new FindIndex<>(array, 324));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenElementIsFound() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] array = new String[]{"asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip",
                "asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip",
                "asdas", "iwquy", "iopip", "asd", "asdas", "iwquy", "iopip"};
        int result = forkJoinPool.invoke(new FindIndex<>(array, "asd"));
        assertThat(result).isEqualTo(3);
    }
}