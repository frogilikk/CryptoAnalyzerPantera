package com.javarush.ivanenko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class BruteForceSearch {
    public static void bruteForce(Path path, Path resultPath) {
        try {
            List<String> commonWords = List.of(
                    "и", "в", "не", "на", "я", "быть", "с", "он", "что", "а",
                    "это", "этот", "по", "к", "но"
            );

            List<String> source = Files.readAllLines(path);
            List<String> decryptedSource = new ArrayList<>();
            int[] scores = new int[Messages.ALPHABET.length];

            for (int shift = 0; shift < Messages.ALPHABET.length; shift++) {
                char[] keyAlphabet = MenuWork.keyAlphabet(shift);

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

                    String decryptedLine = builder.toString();
                    for (String word : commonWords) {
                        if (decryptedLine.contains(word)) {
                            scores[shift]++;
                        }
                    }
                }
            }

            int bestScore = 0;
            int bestKey = 0;
            for (int i = 0; i < scores.length; i++) {
                if (scores[i] > bestScore) {
                    bestScore = scores[i];
                    bestKey = i;
                }
            }

            char[] finalAlphabet = MenuWork.keyAlphabet(bestKey);
            for (String line : source) {
                StringBuilder builder = new StringBuilder();
                for (char ch : line.toCharArray()) {
                    int index = new String(Messages.ALPHABET).indexOf(ch);
                    if (index != -1) {
                        builder.append(finalAlphabet[index]);
                    } else {
                        builder.append(ch);
                    }
                }
                decryptedSource.add(builder.toString());
            }

            if (Files.exists(resultPath)) {
                Files.delete(resultPath);
            }
            Files.write(resultPath, decryptedSource, StandardOpenOption.CREATE);

            System.out.println("Лучший ключ: " + bestKey);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
