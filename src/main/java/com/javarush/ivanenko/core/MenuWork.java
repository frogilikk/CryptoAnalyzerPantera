package com.javarush.ivanenko.core;

import com.javarush.ivanenko.app.Main;
import com.javarush.ivanenko.core.consoleMode.ConsoleBruteForce;
import com.javarush.ivanenko.core.consoleMode.ConsoleCaesarCipher;
import com.javarush.ivanenko.core.pathsMode.BruteForce;
import com.javarush.ivanenko.core.pathsMode.CaesarCipher;
import com.javarush.ivanenko.io.FileManager;
import com.javarush.ivanenko.io.GetPaths;
import com.javarush.ivanenko.io.Messages;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class MenuWork {
    public static int mode = 1;
    public static int consoleDel = 0;

    public static void menu(Path path, Scanner scanner, Path resultPath) {
        if (mode == 1) {
            System.out.println(Messages.MENU + "\n");
        } else if (mode == 2) {
            System.out.println(Messages.MENU_CONSOLE_MODE);
        }
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
                if (mode == 1) {
                    process(scanner, path, resultPath, 0, Messages.ENCRYPTION_KEY_PROMPT);
                } else if (mode == 2) {
                    consoleProcess(scanner, resultPath, 0, Messages.ENCRYPTION_KEY_PROMPT_CONSOLE);
                }
                break;
            case (2):
                if (mode == 1) {
                    process(scanner, path, resultPath, 1, Messages.DECRYPTION_KEY_PROMPT);
                } else if (mode == 2) {
                    consoleProcess(scanner, resultPath, 1, Messages.DECRYPTION_KEY_PROMPT_CONSOLE);
                }
                break;
            case (3):
                if (mode == 1) {
                    bruteForce(path, resultPath);
                } else if (mode == 2) {
                    consoleBruteForce(scanner, resultPath);
                }
                break;
            case (4):
                changePaths(scanner);
                break;
            case (5):
                System.out.println("Путь для работы: " + Main.path.toAbsolutePath());
                System.out.println("Путь для сохранения: " + Main.resultPath.toAbsolutePath());
                break;
            case (6):
                changeMode(path, scanner, resultPath);
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
            if (key > 0 && key < Messages.ALPHABET.length) {
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

    private static void changePaths(Scanner scanner) {
        scanner.nextLine();
        Main.path = GetPaths.getPaths(scanner, 1);
        System.out.print("Менять файл вывода? (y/n)" + Messages.ARROW);
        String answer = scanner.nextLine().trim().toLowerCase();

        switch (answer) {
            case "y":
                Main.resultPath = GetPaths.getPaths(scanner, 2);
                break;
            case "n":
                System.out.println("Предыдущие сохранения не будут записаны.");
                break;
            default:
                System.out.println("Неизвестный ответ. Используется путь по умолчанию.");
                break;
        }
    }

    private static void changeMode(Path path, Scanner scanner, Path resultPath) {
        if(mode == 1) {
            mode = 2;
        } else if (mode == 2) {
            mode = 1;
        }
        consoleDel = 0;
        MenuWork.menu(path, scanner, resultPath);
    }

    private static void consoleProcess(Scanner scanner, Path resultPath, int mode, String prompt) {
        while (true) {
            System.out.print(prompt + Messages.ARROW);
            if (!scanner.hasNextInt()) {
                System.out.println("Введите число!");
                scanner.next();
                continue;
            }
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Теперь введи текст для шифровки/расшифровки: " + Messages.ARROW);
            String source = scanner.nextLine();
            if (key > 0 && key < Messages.ALPHABET.length) {
                try {
                    FileManager.consoleWrite(resultPath, ConsoleCaesarCipher.caesarCipher(key, source, mode));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            } else {
                System.out.println("Недопустимый ключ. Попробуйте снова.");
            }
        }
    }

    private static void consoleBruteForce(Scanner scanner, Path resultPath) {
        scanner.nextLine();
        System.out.print("Введите текст для BruteForce расшифровки: " + Messages.ARROW);
        String source = scanner.nextLine();
        try {
            FileManager.consoleWrite(resultPath, ConsoleBruteForce.bruteForce(source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}