package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class GetContent implements GetContentStrategy {

    @Override
    public String getContent(Predicate<Character> filter, File file) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(output);
    }
}