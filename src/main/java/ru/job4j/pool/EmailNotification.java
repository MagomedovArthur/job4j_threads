package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(
                        String.format("Notification %s to email %s.", user.userName(), user.email()),
                        String.format("Add a new event to %s.", user.userName()),
                        user.email()
                );
            }
        });
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        pool.shutdownNow();
    }
}