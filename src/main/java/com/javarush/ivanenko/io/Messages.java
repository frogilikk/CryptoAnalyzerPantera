package com.javarush.ivanenko.io;

public class Messages {
    public static final char[] ALPHABET_RU = {
                      'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
                      'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
                      'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч',
                      'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',

                      'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
                      'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
                      'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
                      'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',

                      '.', ',', '-', '«', '»', '"', '\'', ':', '!', '?', ' '
    };

    public static final char[] ALPHABET_EN = {
                      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                      'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                      'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                      'Y', 'Z',

                      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                      'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                      'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                      'y', 'z',

                      '.', ',', '-', '"', '\'', ':', '!', '?', ' '
    };

    public static char[] ALPHABET = ALPHABET_RU;

    public static final String ARROW = "\n> ";
    public static final String GREETING = "\nДобро пожаловать в CryptoAnalyzer по шифру Цезаря";
    public static final String PATH_WORK = "\nВведи путь к файлу с которого брать текст: " +
                      "(если пропустить будет дефолтный)";
    public static final String PATH_RESULT = "Введи путь к файлу в который сохранить текст: " +
                      "(если не вводить будет дефолтный)";
    public static final String LOW_FUNCTIONALITY = " (доступный функционал ограничен, можно только шифровать на русском," +
                      " для полного функционала введите свой путь)\n";
    public static final String DEFAULT_PATH_NOTICE = "Вы не указали путь, использую стандартный";
    public static final String INVALID_NUMBER = "Введен не верный номер, попробуй ещё раз";
    public static final String ENCRYPTION_KEY_PROMPT = "\nДавай зашифруем твой файл, путь у нас есть — остался лишь ключ." +
                      " Введи его ниже: (он должен быть от 1 до " + (ALPHABET.length - 1) + ")";
    public static final String DECRYPTION_KEY_PROMPT = "\nДавай расшифруем твой файл, путь у нас есть - остался ключ." +
                      " Введи его ниже: (он должен быть от 1 до " + (ALPHABET.length - 1) + ")";
    public static final String ENCRYPTION_KEY_PROMPT_CONSOLE = "\nДавай зашифруем твой текст нам нужен ключ." +
                      " Введи его ниже: (он должен быть от 1 до " + (ALPHABET.length - 1) + ")";
    public static final String DECRYPTION_KEY_PROMPT_CONSOLE = "\nДавай расшифруем твой текст нам нужен ключ." +
                      " Введи его ниже: (он должен быть от 1 до " + (ALPHABET.length - 1) + ")";
    public static final String INVALID_KEY_ERROR = "Введен неверный ключ";
    public static final String MENU_RU =
                      "Меню:\n" +
                                        "1. Шифрование\n" +
                                        "2. Расшифровка с ключом\n" +
                                        "3. Brute force\n" +
                                        "4. Изменить пути\n" +
                                        "5. Посмотреть пути\n" +
                                        "6. Переключиться в консольный режим\n" +
                                        "7. Переключится на англ шифровку\n" +
                                        "0. Выход";
    public static final String MENU_CONSOLE_MODE_RU =
                      "Меню:\n" +
                                        "1. Шифрование\n" +
                                        "2. Расшифровка с ключом\n" +
                                        "3. Brute force\n" +
                                        "4. Изменить пути\n" +
                                        "5. Посмотреть пути\n" +
                                        "6. Переключиться в файловый режим\n" +
                                        "7. Переключится на англ шифровку\n" +
                                        "0. Выход";
    public static final String MENU_EN =
                      "Меню:\n" +
                                        "1. Шифрование\n" +
                                        "2. Расшифровка с ключом\n" +
                                        "3. Brute force\n" +
                                        "4. Изменить пути\n" +
                                        "5. Посмотреть пути\n" +
                                        "6. Переключиться в консольный режим\n" +
                                        "7. Переключится на рус шифровку\n" +
                                        "0. Выход";
    public static final String MENU_CONSOLE_MODE_EN =
                      "Меню:\n" +
                                        "1. Шифрование\n" +
                                        "2. Расшифровка с ключом\n" +
                                        "3. Brute force\n" +
                                        "4. Изменить пути\n" +
                                        "5. Посмотреть пути\n" +
                                        "6. Переключиться в файловый режим\n" +
                                        "7. Переключится на рус шифровку\n" +
                                        "0. Выход";
    public static final String MENU_PROMPT = "Выбери пункт:";
}
