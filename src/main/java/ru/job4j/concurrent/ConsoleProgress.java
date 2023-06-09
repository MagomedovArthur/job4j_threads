package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        try {
            var process = new char[]{'-', '\\', '|', '/'};
            while (!Thread.currentThread().isInterrupted()) {
                for (var element : process) {
                    System.out.print("\rload: " + element);
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}