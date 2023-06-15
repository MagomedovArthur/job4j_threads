package ru.job4j.io;

import java.io.*;

public class SaveContent implements SetContent {

    @Override
    public void saveContent(String content, File file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.println(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}