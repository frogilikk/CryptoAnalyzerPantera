package com.javarush.ivanenko;

import com.javarush.khmelov.constant.Alphabet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class MenuWork {
    public static void Menu(Path path, Scanner scanner, Path resultPath) {
        System.out.println(Messages.MENU + "\n");
        while (true) {
            System.out.print(Messages.MENU_PROMPT + Messages.ARROW);
            int num = scanner.nextInt();

            Switch(num, scanner, path, resultPath);
        }
    }

    public static void Switch(int num, Scanner scanner, Path path, Path resultPath) {
        switch (num) {
            case (1):
                System.out.print(Messages.ENCRYPTION_KEY_PROMPT + Messages.ARROW);
                int key = scanner.nextInt();
                Encrypt.encrypt(key, path, resultPath);
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
