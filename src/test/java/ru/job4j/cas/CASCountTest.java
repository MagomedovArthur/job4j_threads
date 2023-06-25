package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    void whenAddedThreeNumbers() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                count.increment();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                count.increment();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(7);
    }

    @Test
    void whenAddedOneNumbers() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> {
            for (int i = 1; i <= 90_000; i++) {
                count.increment();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                count.increment();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(90_002);
    }
}