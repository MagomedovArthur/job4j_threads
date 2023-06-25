package ru.job4j.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SupplyAsync {
    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пам, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product);
                    return product;
                }
        );
    }

    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    public static void runAsyncExample() throws Exception {
        CompletableFuture<String> gtt = buyProduct("Молоко");
        iWork();
    }

    public static void main(String[] args) throws Exception {
        runAsyncExample();
    }
}