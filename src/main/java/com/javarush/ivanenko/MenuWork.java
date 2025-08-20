package com.javarush.ivanenko;

import com.javarush.khmelov.constant.Alphabet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class MenuWork {
    public static void Menu(Path path, Scanner scanner) {
        System.out.println(Messages.MENU + "\n");
        while (true) {
            System.out.print(Messages.MENU_PROMPT + Messages.ARROW);
            int num = scanner.nextInt();

            Switch(num, scanner, path);
        }
    }

    public static void Switch(int num, Scanner scanner, Path path) {
        Path encryptedPath = Path.of("src/main/java/com/javarush/ivanenko/encryptedText");
        List<String> source;
        List<String> encryptedSource = new ArrayList<>();
        char[] keyAlphabet = new char[Messages.ALPHABET.length];
        switch (num) {
            case (1):
                System.out.print(Messages.ENCRYPTION_KEY_PROMPT + Messages.ARROW);
                int key = scanner.nextInt();
                if (key < 0 || key > Messages.ALPHABET.length - 1) {
                    System.out.println(Messages.INVALID_KEY_ERROR);
                } else {
                    for (int i = 0; i < Messages.ALPHABET.length; i++) {
                        keyAlphabet[i] = Messages.ALPHABET[(i + key) % Messages.ALPHABET.length];
                    }
                    try {
                        source = Files.readAllLines(path);
                        for (int i = 0; i < source.size(); i++) {
                            String line = source.get(i);
                            StringBuilder builder = new StringBuilder();
                            for (char ch : line.toCharArray()) {
                                int index = new String(Messages.ALPHABET).indexOf(ch);
                                if (index != -1) {
                                    builder.append(keyAlphabet[index]);
                                } else {
                                    builder.append(ch);
                                }
                            }
                            encryptedSource.add(builder.toString());
                        }
                        if (Files.exists(encryptedPath)) {
                            Files.delete(encryptedPath);
                        }
                        Files.writeString(encryptedPath, "", StandardOpenOption.CREATE);
                        Files.write(encryptedPath, encryptedSource, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Неудалось прочитать файл" + e);
                    }
                }
                break;
            case (2):
                break;
            case (3):
                break;
            case (0):
                System.exit(0);
                break;
            default:
                System.out.println("Введен не верный номер, попробуй ещё раз");
        }
    }
}
