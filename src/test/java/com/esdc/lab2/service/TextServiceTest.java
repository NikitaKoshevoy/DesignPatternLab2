package com.esdc.lab2.service;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import com.esdc.lab2.entity.TextLeaf;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class TextServiceTest {

    @Test
    void sortParagraphsBySentenceCount_shouldSortCorrectly() {
        TextService service = new TextService();

        TextComposite text = new TextComposite(ComponentType.TEXT);

        TextComposite paragraph1 = new TextComposite(ComponentType.PARAGRAPH);
        paragraph1.add(createMockSentence(2));

        TextComposite paragraph2 = new TextComposite(ComponentType.PARAGRAPH);
        paragraph2.add(createMockSentence(1));

        text.add(paragraph1);
        text.add(paragraph2);

        TextComponent sorted = service.sortParagraphsBySentenceCount(text);
        List<TextComponent> children = sorted.getChildren();

        assertEquals(2, children.size());
    }

    @Test
    void sortParagraphsBySentenceCount_shouldThrowExceptionWhenRootIsNotText() {
        TextService service = new TextService();
        TextComposite nonTextRoot = new TextComposite(ComponentType.PARAGRAPH);

        assertThrows(IllegalArgumentException.class, () -> service.sortParagraphsBySentenceCount(nonTextRoot));
    }

    @Test
    void findSentencesWithLongestWord_shouldReturnCorrectSentence() {
        TextService service = new TextService();
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        sentence1.add(createMockWord("short"));

        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        sentence2.add(createMockWord("extraordinary"));

        paragraph.add(sentence1);
        paragraph.add(sentence2);
        text.add(paragraph);

        List<TextComponent> result = service.findSentencesWithLongestWord(text);

        assertEquals(1, result.size());
        assertEquals(sentence2, result.get(0));
    }

    @Test
    void removeShortSentences_shouldRemoveCorrectly() {
        TextService service = new TextService();
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);

        TextComposite shortSentence = new TextComposite(ComponentType.SENTENCE);
        shortSentence.add(createMockWord("hi"));

        TextComposite longSentence = new TextComposite(ComponentType.SENTENCE);
        longSentence.add(createMockWord("hello"));
        longSentence.add(createMockWord("world"));

        paragraph.add(shortSentence);
        paragraph.add(longSentence);
        text.add(paragraph);

        TextComponent result = service.removeShortSentences(text, 2);

        assertEquals(1, result.getChildren().get(0).getChildren().size());
        assertEquals(longSentence, result.getChildren().get(0).getChildren().get(0));
    }

    @Test
    void countWordOccurrencesIgnoreCase_shouldCountCorrectly() {
        TextService service = new TextService();
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);

        sentence.add(createMockWord("Hello"));
        sentence.add(createMockWord("world"));
        sentence.add(createMockWord("hello"));

        paragraph.add(sentence);
        text.add(paragraph);

        Map<String, Integer> result = service.countWordOccurrencesIgnoreCase(text);

        assertEquals(3, result.size());
    }

    @Test
    void countVowelsAndConsonants_shouldCountCorrectly() {
        TextService service = new TextService();
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        sentence.add(createMockWord("abc"));
        sentence.add(createMockWord("de"));

        Map<String, Integer> result = service.countVowelsAndConsonants(sentence);

        assertEquals(2, result.get("vowels"));
        assertEquals(3, result.get("consonants"));
    }

    private TextComponent createMockSentence(int wordCount) {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        for (int i = 0; i < wordCount; i++) {
            sentence.add(createMockWord("word" + i));
        }
        return sentence;
    }

    private TextComponent createMockWord(String word) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        for (char c : word.toCharArray()) {
            wordComposite.add(new TextLeaf(c, ComponentType.SYMBOL));
        }
        return wordComposite;
    }
}