package com.javarush.ivanenko;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path path;
        Path resultPath;

        path = GetPaths.getPaths(scanner, 1);
        resultPath = GetPaths.getPaths(scanner, 2);
        System.out.println("Путь к файлу для работы: " + path.toAbsolutePath());
        System.out.println("Путь к файлу для сохранения: " + resultPath.toAbsolutePath() + "\n");

        MenuWork.Menu(path, scanner, resultPath);
    }
}
