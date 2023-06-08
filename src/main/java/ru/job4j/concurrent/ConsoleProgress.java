package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(5000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            var process = new char[]{'-', '\\', '|', '/'};
            while (!Thread.currentThread().isInterrupted()) {
                for (int i = 0; i < process.length; i++) {
                    System.out.print("\rload: " + process[i]);
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}