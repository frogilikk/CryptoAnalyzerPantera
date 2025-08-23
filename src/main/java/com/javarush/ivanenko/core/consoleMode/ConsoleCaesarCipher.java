package com.javarush.ivanenko.core.consoleMode;

import com.javarush.ivanenko.core.pathsMode.CaesarCipher;
import com.javarush.ivanenko.io.Messages;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCaesarCipher {
    public static List<String> caesarCipher(int key, String source, int move) {
        List<String> encryptedSource = new ArrayList<>();
        char[] keyAlphabet = CaesarCipher.keyAlphabet(key);
        String alphabetStr = new String(Messages.ALPHABET);

        StringBuilder builder = new StringBuilder();
        for (char ch : source.toCharArray()) {
            action(builder, keyAlphabet, alphabetStr, ch, move);
        }

        encryptedSource.add(builder.toString());

        return encryptedSource;
    }

    private static void action(StringBuilder builder, char[] keyAlphabet, String alphabetStr, char ch, int mode) {
        int index;
        switch (mode) {
            case 0:
                index = alphabetStr.indexOf(ch);
                builder.append(index != -1 ? keyAlphabet[index] : ch);
                break;
            case 1:
                index = alphabetStr.indexOf(ch);
                builder.append(index != -1 ? Messages.ALPHABET[index] : ch);
                break;
            default:
                throw new IllegalArgumentException("Неверный режим: " + mode);
        }
    }
}
