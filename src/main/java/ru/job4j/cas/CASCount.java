package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int expected;
        int newVal;
        do {
            expected = count.get();
            newVal = expected + 1;
        } while (!count.compareAndSet(expected, newVal));
    }

    public int get() {
        return count.get();
    }
}