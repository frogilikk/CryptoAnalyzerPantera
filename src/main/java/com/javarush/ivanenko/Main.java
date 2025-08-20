package com.javarush.ivanenko;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path path;

        System.out.print(Messages.GREETING + Messages.ARROW);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            path = Path.of("text/dict.txt"); // путь по умолчанию
            System.out.println(Messages.DEFAULT_PATH_NOTICE);
        } else {
            path = Path.of(input);
        }

        System.out.println("Путь к файлу: " + path.toAbsolutePath() + "\n");

        MenuWork.Menu(path, scanner);
    }
}
