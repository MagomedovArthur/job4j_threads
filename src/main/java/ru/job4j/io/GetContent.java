package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class GetContent implements GetContentStrategy {

    @Override
    public String getContent(Predicate<Character> filter, File file) {
        String output = "";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}