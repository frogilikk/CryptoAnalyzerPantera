package com.javarush.ivanenko;

import java.nio.file.Path;
import java.util.Scanner;

public class GetPaths {
    public static Path getPaths(Scanner scanner, int num) {
        String input;
        switch (num) {
            case 1:
                System.out.print(Messages.GREETING + Messages.ARROW);
                input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println(Messages.DEFAULT_PATH_NOTICE +
                            " (доступный функционал ограницен, можно только шифровать, для полного функционала введите свой путь)" +
                            "\n");
                    Path path = Path.of("text/text.txt");
                    return path;
                } else {
                    return Path.of(input);
                }
            case 2:
            System.out.print(Messages.PATH_RESULT + Messages.ARROW);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(Messages.DEFAULT_PATH_NOTICE + "\n");
                return Path.of("text/result.txt");
            } else {
                return Path.of(input);
            }
            default:
                return null;
        }
    }
}
