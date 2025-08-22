package com.javarush.ivanenko.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileManager {

    public static List<String> read(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public static void write(Path path, List<String> lines) throws IOException {
        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
