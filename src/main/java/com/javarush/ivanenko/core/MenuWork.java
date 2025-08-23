package com.javarush.ivanenko.core;

import com.javarush.ivanenko.io.FileManager;
import com.javarush.ivanenko.io.Messages;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class MenuWork {
    public static void menu(Path path, Scanner scanner, Path resultPath) {
        System.out.println(Messages.MENU + "\n");
        while (true) {
            System.out.print("\n" + Messages.MENU_PROMPT + Messages.ARROW);
            if (!scanner.hasNextInt()) {
                System.out.println("Введите номер пункта меню!");
                scanner.next();
                continue;
            }
            int num = scanner.nextInt();


            Switch(num, scanner, path, resultPath);
        }
    }

    public static void Switch(int num, Scanner scanner, Path path, Path resultPath) {
        switch (num) {
            case (1):
                process(scanner, path, resultPath, 0, Messages.ENCRYPTION_KEY_PROMPT);
                break;
            case (2):
                process(scanner, path, resultPath, 1, Messages.DECRYPTION_KEY_PROMPT);
                break;
            case (3):
                bruteForce(path, resultPath);
                break;
            case (0):
                System.exit(0);
            default:
                System.out.println("Неверный пункт меню. Попробуйте снова.");
        }
    }

    private static void process(Scanner scanner, Path path, Path resultPath, int mode, String prompt) {
        while (true) {
            System.out.print(prompt + Messages.ARROW);
            if (!scanner.hasNextInt()) {
                System.out.println("Введите число!");
                scanner.next();
                continue;
            }
            int key = scanner.nextInt();
            if (key >= 0 && key < Messages.ALPHABET.length) {
                try {
                    FileManager.write(resultPath, CaesarCipher.transform(key, path, mode));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            } else {
                System.out.println("Недопустимый ключ. Попробуйте снова.");
            }
        }

    }

    private static void bruteForce(Path path, Path resultPath) {
        try {
            FileManager.write(resultPath, BruteForce.bruteForce(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}