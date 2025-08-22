package com.javarush.ivanenko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Decipher {
    public static void decipher(int key, Path path, Path resultPath) {
        List<String> source;
        List<String> encryptedSource = new ArrayList<>();
        char[] keyAlphabet = MenuWork.keyAlphabet(key);
        if (key < 0 || key > Messages.ALPHABET.length - 1) {
            System.out.println(Messages.INVALID_KEY_ERROR);
        } else {
            try {
                source = Files.readAllLines(path);
                for (int i = 0; i < source.size(); i++) {
                    String line = source.get(i);
                    StringBuilder builder = new StringBuilder();
                    for (char ch : line.toCharArray()) {
                        int index = new String(keyAlphabet).indexOf(ch);
                        if (index != -1) {
                            builder.append(Messages.ALPHABET[index]);
                        } else {
                            builder.append(ch);
                        }
                    }
                    encryptedSource.add(builder.toString());
                }
                if (Files.exists(resultPath)) {
                    Files.delete(resultPath);
                }
                Files.writeString(resultPath, "", StandardOpenOption.CREATE);
                Files.write(resultPath, encryptedSource, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Неудалось прочитать файл" + e);
            }
        }
    }
}
