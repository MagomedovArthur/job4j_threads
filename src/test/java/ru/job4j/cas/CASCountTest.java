package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    void whenAddedThreeNumbers() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        count.increment();
        assertThat(count.get()).isEqualTo(3);
    }

    @Test
    void whenAddedOneNumbers() {
        CASCount count = new CASCount();
        count.increment();
        assertThat(count.get()).isEqualTo(1);
    }
}