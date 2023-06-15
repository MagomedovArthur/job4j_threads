package ru.job4j.io;

import java.io.*;

public final class ParseFile {
    private final File file;

    public ParseFile(final File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        return new GetContent().getContent(t -> t != 0, file);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return new GetContent().getContent(t -> t < 0x80, file);
    }

    public synchronized void saveContent(String content) throws Exception {
        new SaveContent().saveContent(content, file);
    }
}