package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public FindIndex(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    private int findIndex(T[] array, T object, int from, int to) {
        int result = -1;
        for (int i = from; i < to; i++) {
            if (object.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return findIndex(array, object, from, to);
        }
        int middle = (from + to) / 2;
        FindIndex<T> findIndexRight = new FindIndex<>(array, object, from, middle);
        FindIndex<T> findIndexLeft = new FindIndex<>(array, object, middle + 1, to);
        findIndexRight.fork();
        findIndexLeft.fork();
        Integer right = findIndexRight.join();
        Integer left = findIndexLeft.join();
        return Math.max(right, left);
    }

    public static <T> int search(T[] array, T object) {
        return new ForkJoinPool().invoke(new FindIndex<>(array, object, 0, array.length - 1));
    }
}