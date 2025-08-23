package com.javarush.ivanenko.io;

import com.javarush.ivanenko.core.MenuWork;

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

    public static void consoleWrite(Path path, List<String> lines) throws IOException {
        if (MenuWork.consoleDel == 0) {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            MenuWork.consoleDel = 1;
        } else {
            Files.write(path, lines, StandardOpenOption.APPEND);
        }
    }
}
