package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Wget implements Runnable {
    private final String url;
    private final String fileName;
    private final int speed;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            int downloadData = 0;
            Calendar startDate = Calendar.getInstance();
            long startSeconds = TimeUnit.MILLISECONDS.toSeconds(startDate.getTimeInMillis());
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    Calendar currentDate = Calendar.getInstance();
                    long currentSeconds = TimeUnit.MILLISECONDS.toSeconds(currentDate.getTimeInMillis());
                    long interval = currentSeconds - startSeconds;
                    if (interval < 1) {
                        Thread.sleep(1000 - TimeUnit.MILLISECONDS.toMillis(interval));
                    }
                    downloadData = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        validateArgs(args);
        Thread wget = new Thread(new Wget(url, speed, "10Mb.dat"));
        wget.start();
        wget.join();
    }

    private static void validateArgs(String[] args) {
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("URL string is empty!");
        }
        if (Integer.parseInt(args[1]) != 0) {
            throw new IllegalArgumentException();
        }
    }
}