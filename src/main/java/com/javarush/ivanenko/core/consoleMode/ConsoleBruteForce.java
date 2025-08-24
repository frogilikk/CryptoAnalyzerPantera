package com.javarush.ivanenko.core.consoleMode;

import com.javarush.ivanenko.core.MenuWork;
import com.javarush.ivanenko.core.pathsMode.CaesarCipher;
import com.javarush.ivanenko.io.Messages;

import java.util.ArrayList;
import java.util.List;

public class ConsoleBruteForce {
    private static List<String> commonWords;

    public static List<String> bruteForce(String source) {
        if (MenuWork.language == 1) {
            commonWords = List.of(
                              "и", "в", "во", "не", "на", "я", "быть", "с", "он", "что", "а",
                              "это", "этот", "по", "к", "но", "как", "же", "или", "если",
                              "мы", "вы", "они", "так", "у", "от", "до", "за", "из", "о",
                              "со", "для", "то", "там", "тут"
            );
        } else if (MenuWork.language == 2) {
            commonWords = List.of(
                              "the", "be", "to", "of", "and", "a", "in", "that", "have", "i",
                              "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
                              "this", "but", "his", "by", "from", "they", "we", "say", "her", "she"
            );
        }
        int[] scores = new int[Messages.ALPHABET.length];
        String alphabetStr = new String(Messages.ALPHABET);

        for (int shift = 0; shift < Messages.ALPHABET.length; shift++) {
            char[] keyAlphabet = CaesarCipher.keyAlphabet(shift);

            StringBuilder builder = new StringBuilder();

            for (char ch : source.toCharArray()) {
                int index = alphabetStr.indexOf(ch);
                if (index != -1) {
                    builder.append(keyAlphabet[index]);
                } else {
                    builder.append(ch);
                }
            }

            String decryptedLine = builder.toString();

            for (String word : commonWords) {
                if (decryptedLine.contains(word)) {
                    scores[shift]++;
                }
            }
        }

        int bestKey = findBestKey(scores);

        List<String> decryptedSource = decryptSource(source, bestKey, alphabetStr);

        System.out.printf("🔑 Найден ключ: %d с %d совпадениями%n", bestKey, scores[bestKey]);
        return decryptedSource;
    }

    private static int findBestKey(int[] scores) {
        int bestScore = 0;
        int bestKey = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > bestScore) {
                bestScore = scores[i];
                bestKey = i;
            }
        }
        return bestKey;
    }

    private static List<String> decryptSource(String source, int bestKey, String alphabetStr) {
        List<String> result = new ArrayList<>();
        char[] finalAlphabet = CaesarCipher.keyAlphabet(bestKey);
        StringBuilder builder = new StringBuilder();
        for (char ch : source.toCharArray()) {
            int index = alphabetStr.indexOf(ch);
            builder.append(index != -1 ? finalAlphabet[index] : ch);
        }
        result.add(builder.toString());
        return result;
    }
}
