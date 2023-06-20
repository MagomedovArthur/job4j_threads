package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenBothThreadsHaveCompletedSuccessfully() {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(10);
        List<Integer> resultList = new ArrayList<>();
        Thread firstThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.offer(i);
            }
        });
        Thread secondThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resultList.add((Integer) queue.poll());
            }
        });
        firstThread.start();
        secondThread.start();
        try {
            secondThread.join();
            secondThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(resultList).contains(7, 10, 5);
    }
}