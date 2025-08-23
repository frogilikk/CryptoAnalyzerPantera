package com.javarush.ivanenko.core.consoleMode;

import com.javarush.ivanenko.core.pathsMode.CaesarCipher;
import com.javarush.ivanenko.io.Messages;

import java.util.ArrayList;
import java.util.List;

public class ConsoleBruteForce {
    private static final List<String> commonWords = List.of(
                      "и", "в", "во", "не", "на", "я", "быть", "с", "он", "что", "а",
                      "это", "этот", "по", "к", "но", "как", "же", "или", "если",
                      "мы", "вы", "они", "так", "у", "от", "до", "за", "из", "о",
                      "со", "для", "то", "там", "тут"
    );

    public static List<String> bruteForce(String source) {
        int[] scores = new int[Messages.ALPHABET.length];
        String alphabetStr = new String(Messages.ALPHABET);

        for (int shift = 0; shift < Messages.ALPHABET.length; shift++) {
            char[] keyAlphabet = CaesarCipher.keyAlphabet(shift);

            StringBuilder builder = new StringBuilder();

            for (char ch : source.toCharArray()) {
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

        int bestKey = findBestKey(scores);

        List<String> decryptedSource = decryptSource(source, bestKey, alphabetStr);

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
