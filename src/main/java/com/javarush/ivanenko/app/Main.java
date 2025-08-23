package com.javarush.ivanenko.app;

import com.javarush.ivanenko.io.GetPaths;
import com.javarush.ivanenko.core.MenuWork;
import com.javarush.ivanenko.io.Messages;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static Path path;
    public static Path resultPath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Messages.GREETING);
        path = GetPaths.getPaths(scanner, 1);
        if (!Files.exists(path)) {
            System.out.println("Файл для работы не найден: " + path.toAbsolutePath());
            return;
        }
        resultPath = GetPaths.getPaths(scanner, 2);
        if (!Files.exists(resultPath.getParent())) {
            System.out.println("Директория для сохранения не найдена: " + resultPath.getParent());
            return;
        }
        System.out.println("Путь к файлу для работы: " + path.toAbsolutePath());
        System.out.println("Путь к файлу для сохранения: " + resultPath.toAbsolutePath() + "\n");

        try {
            MenuWork.menu(path, scanner, resultPath);
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}