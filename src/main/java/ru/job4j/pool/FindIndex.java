package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class FindIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T object;

    public FindIndex(T[] array, T object) {
        this.array = array;
        this.object = object;
    }

    private int findIndex(T[] array, T object) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            return findIndex(array, object);
        }
        FindIndex findIndex = new FindIndex(array, object);
        findIndex.fork();
        return findIndex(array, object);
    }
}