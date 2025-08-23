package com.javarush.ivanenko.core;

import com.javarush.ivanenko.io.Messages;
import com.javarush.ivanenko.io.FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    private static List<String> commonWords = List.of(
                      "не", "на","быть", "он", "что",
                      "это", "этот", "по", "но", "как"
    );

    public static List<String> bruteForce(Path path) throws IOException {
        List<String> source = FileManager.read(path);
        int[] scores = new int[Messages.ALPHABET.length];
        String alphabetStr = new String(Messages.ALPHABET);

        if (source.isEmpty()) {
            throw new IllegalArgumentException("Файл пуст или не содержит текста.");
        }

        for (int shift = 0; shift < Messages.ALPHABET.length; shift++) {
            char[] keyAlphabet = CaesarCipher.keyAlphabet(shift);

            for (String line : source) {
                StringBuilder builder = new StringBuilder();

                for (char ch : line.toCharArray()) {
                    int index = new String(Messages.ALPHABET).indexOf(ch);
                    if (index != -1) {
                        builder.append(keyAlphabet[index]);
                    } else {
                        builder.append(ch);
                    }
                }

                String decryptedLine = builder.toString().toLowerCase();

                for (String word : commonWords) {
                    if (decryptedLine.contains(word)) {
                        scores[shift]++;
                    }
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

    private static List<String> decryptSource(List<String> source, int bestKey, String alphabetStr) {
        List<String> result = new ArrayList<>();
        char[] finalAlphabet = CaesarCipher.keyAlphabet(bestKey);
        for (String line : source) {
            StringBuilder builder = new StringBuilder();
            for (char ch : line.toCharArray()) {
                int index = alphabetStr.indexOf(ch);
                builder.append(index != -1 ? finalAlphabet[index] : ch);
            }
            result.add(builder.toString());
        }
        return result;
    }
}
