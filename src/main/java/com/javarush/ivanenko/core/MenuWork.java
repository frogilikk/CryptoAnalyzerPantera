package com.javarush.ivanenko.core;

import com.javarush.ivanenko.io.FileManager;
import com.javarush.ivanenko.io.Messages;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class MenuWork {
    public static void Menu(Path path, Scanner scanner, Path resultPath) {
        System.out.println(Messages.MENU + "\n");
        while (true) {
            System.out.print("\n" + Messages.MENU_PROMPT + Messages.ARROW);
            int num = scanner.nextInt();

            Switch(num, scanner, path, resultPath);
        }
    }

    public static void Switch(int num, Scanner scanner, Path path, Path resultPath) {
        switch (num) {
            case (1):
                encrypt(scanner, path, resultPath);
                break;
            case (2):
                decrypt(scanner, path, resultPath);
                break;
            case (3):
                bruteForce(path, resultPath);
                break;
            case (0):
                System.exit(0);
            default:
                System.out.println();
        }
    }

    private static void encrypt(Scanner scanner, Path path, Path resultPath) {
        System.out.print(Messages.ENCRYPTION_KEY_PROMPT + Messages.ARROW);
        int key = scanner.nextInt();
        if (key < Messages.ALPHABET.length && key > 1) {
            try {
                FileManager.write(resultPath, CaesarCipher.transform(key, path, 0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void decrypt(Scanner scanner, Path path, Path resultPath) {
        System.out.print(Messages.DECRYPTION_KEY_PROMPT + Messages.ARROW);
        int key = scanner.nextInt();
        if (key < Messages.ALPHABET.length && key > 1) {
            try {
                FileManager.write(resultPath, CaesarCipher.transform(key, path, 1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void bruteForce(Path path, Path resultPath) {
        try {
            BruteForce.bruteForce(path, resultPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
