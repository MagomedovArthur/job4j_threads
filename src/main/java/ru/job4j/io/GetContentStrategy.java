package ru.job4j.io;

import java.io.File;
import java.util.function.Predicate;

public interface GetContentStrategy {

    String getContent(Predicate<Character> filter, File file);
}