package com.javarush.ivanenko.io;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class GetPaths {
    public static Path getPaths(Scanner scanner, int num) {
        String input;
        switch (num) {
            case 1:
                System.out.print(Messages.PATH_WORK + Messages.ARROW);
                input = scanner.nextLine().trim();

                Path path = Path.of("text/text.txt");
                if (input.isEmpty()) {
                    System.out.println(Messages.DEFAULT_PATH_NOTICE + Messages.LOW_FUNCTIONALITY);
                    return path;
                } else {
                    try {
                        return Path.of(input);
                    } catch (InvalidPathException e) {
                        System.out.println("Неверный путь" + input);
                        System.out.println(Messages.DEFAULT_PATH_NOTICE);
                        return path;
                    }
                }

            case 2:
                System.out.print(Messages.PATH_RESULT + Messages.ARROW);
                input = scanner.nextLine().trim();

                Path path1 = Path.of("text/result.txt");
                if (input.isEmpty()) {
                    System.out.println(Messages.DEFAULT_PATH_NOTICE + "\n");
                    return path1;
                } else {
                    try {
                        return Path.of(input);
                    } catch (InvalidPathException e) {
                        System.out.println("Неверный путь" + input);
                        System.out.println(Messages.DEFAULT_PATH_NOTICE);
                        return path1;
                    }
                }

            default:
                throw new IllegalArgumentException("Неверный номер пути: " + num);
        }
    }
}