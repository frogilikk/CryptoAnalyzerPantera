package com.javarush.ivanenko.core.pathsMode;

import com.javarush.ivanenko.io.Messages;
import com.javarush.ivanenko.io.FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {

    public static List<String> transform(int key, Path path, int move) throws IOException {
        List<String> source = FileManager.read(path);
        List<String> encryptedSource = new ArrayList<>();
        char[] keyAlphabet = keyAlphabet(key);
        String alphabetStr = new String(Messages.ALPHABET);
        String keyAlphabetStr = new String(keyAlphabet);
        for (int i = 0; i < source.size(); i++) {
            String line = source.get(i);
            StringBuilder builder = new StringBuilder();
            for (char ch : line.toCharArray()) {
                action(builder, keyAlphabet, alphabetStr, keyAlphabetStr, ch, move);
            }
            encryptedSource.add(builder.toString());
        }
        return encryptedSource;
    }

    private static void action(StringBuilder builder, char[] keyAlphabet, String alphabetStr, String keyAlphabetStr, char ch, int mode) {
        int index;
        switch (mode) {
            case 0:
                index = alphabetStr.indexOf(ch);
                builder.append(index != -1 ? keyAlphabet[index] : ch);
                break;
            case 1:
                index = keyAlphabetStr.indexOf(ch);
                builder.append(index != -1 ? Messages.ALPHABET[index] : ch);
                break;
            default:
                throw new IllegalArgumentException("Неверный режим: " + mode);
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