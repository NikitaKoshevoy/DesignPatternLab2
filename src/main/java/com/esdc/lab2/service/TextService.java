package com.esdc.lab2.service;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import com.esdc.lab2.entity.TextLeaf;

import java.util.*;
import java.util.stream.Collectors;

public class TextService {

    public static final String VOWELS = "aeouiy";
    public static final String VOWELS_KEY = "vowels";
    public static final String CONSONANTS_KEY = "consonants";

    public TextComponent sortParagraphsBySentenceCount(TextComponent text) {
        if (text.getType() != ComponentType.TEXT) {
            throw new IllegalArgumentException("Root component must be TEXT");
        }

        List<TextComponent> paragraphs = new ArrayList<>(text.getChildren());
        paragraphs.sort(Comparator.comparingInt(p -> p.getChildren().size()));
        TextComponent sortedText = new TextComposite(ComponentType.TEXT);
        paragraphs.forEach(sortedText::add);
        return sortedText;
    }

    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        int maxWordLength = 0;
        List<TextComponent> result = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                for (TextComponent word : sentence.getChildren()) {
                    int length = word.getChildren().size();
                    if (length > maxWordLength) {
                        maxWordLength = length;
                        result.clear();
                        result.add(sentence);
                    } else if (length == maxWordLength) {
                        result.add(sentence);
                    }
                }
            }
        }
        return result;
    }

    public TextComponent removeShortSentences(TextComponent text, int minWordCount) {
        TextComponent newText = new TextComposite(ComponentType.TEXT);
        for (TextComponent paragraph : text.getChildren()) {
            TextComponent newParagraph = new TextComposite(ComponentType.PARAGRAPH);
            for (TextComponent sentence : paragraph.getChildren()) {
                if (sentence.getChildren().size() >= minWordCount) {
                    newParagraph.add(sentence);
                }
            }
            if (!newParagraph.getChildren().isEmpty()) {
                newText.add(newParagraph);
            }
        }
        return newText;
    }

    public Map<String, Integer> countWordOccurrencesIgnoreCase(TextComponent text) {
        Map<String, Integer> freq = new HashMap<>();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                for (TextComponent word : sentence.getChildren()) {
                    freq.merge(word.getChildren().toString(), 1, Integer::sum);
                }
            }
        }
        return freq;
    }

    public Map<String, Integer> countVowelsAndConsonants(TextComponent sentence) {
        int vowels = 0;
        int consonants = 0;
        for (TextComponent word : sentence.getChildren()) {
            for (TextComponent c : word.getChildren()) {
                if (VOWELS.contains(c.toString())) vowels++;
                else consonants++;
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put(VOWELS_KEY, vowels);
        result.put(CONSONANTS_KEY, consonants);
        return result;
    }
}