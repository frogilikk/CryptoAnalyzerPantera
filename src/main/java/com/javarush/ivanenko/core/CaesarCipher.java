package com.javarush.ivanenko.core;

import com.javarush.ivanenko.io.Messages;
import com.javarush.ivanenko.io.FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private static List<String> source;
    private static List<String> encryptedSource = new ArrayList<>();
    private static char[] keyAlphabet;

    public static List<String> transform(int key, Path path,int move) throws IOException {
        keyAlphabet = keyAlphabet(key);
        source = FileManager.read(path);
        for (int i = 0; i < source.size(); i++) {
            String line = source.get(i);
            StringBuilder builder = new StringBuilder();
            for (char ch : line.toCharArray()) {
                action(builder, keyAlphabet, ch, move);
            }
            encryptedSource.add(builder.toString());
        }
        return  encryptedSource;
    }

    private static void action(StringBuilder builder, char[] keyAlphabet, char ch, int move) {
        int index;
        switch (move) {
            case 0:
                index = new String(Messages.ALPHABET).indexOf(ch);
                builder.append(index != -1 ? keyAlphabet[index] : ch);
                break;
            case 1:
                index = new String(keyAlphabet).indexOf(ch);
                builder.append(index != -1 ? Messages.ALPHABET[index] : ch);
                break;
        }
    }

    public static char[] keyAlphabet(int key) {
        char[] keyAlphabet = new char[Messages.ALPHABET.length];
        for (int i = 0; i < Messages.ALPHABET.length; i++) {
            keyAlphabet[i] = Messages.ALPHABET[(i + key) % Messages.ALPHABET.length];
        }
        return keyAlphabet;
    }
}