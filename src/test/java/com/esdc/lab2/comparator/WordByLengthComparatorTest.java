package com.esdc.lab2.comparator;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordByLengthComparatorTest {

    @Test
    void compare_shouldOrderByWordLength() {
        TextComposite shortWord = new TextComposite(ComponentType.WORD);
        shortWord.add(mockSymbol('A'));
        shortWord.add(mockSymbol('B'));

        TextComposite longWord = new TextComposite(ComponentType.WORD);
        longWord.add(mockSymbol('X'));
        longWord.add(mockSymbol('Y'));
        longWord.add(mockSymbol('Z'));

        WordByLengthComparator comparator = new WordByLengthComparator();
        int result = comparator.compare(shortWord, longWord);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldReturnZeroWhenEqualLength() {
        TextComposite word1 = new TextComposite(ComponentType.WORD);
        word1.add(mockSymbol('A'));
        word1.add(mockSymbol('B'));

        TextComposite word2 = new TextComposite(ComponentType.WORD);
        word2.add(mockSymbol('X'));
        word2.add(mockSymbol('Y'));

        WordByLengthComparator comparator = new WordByLengthComparator();
        assertEquals(0, comparator.compare(word1, word2));
    }

    @Test
    void compare_shouldThrowExceptionWhenNotWord() {
        TextComposite notWord = new TextComposite(ComponentType.SENTENCE);
        notWord.add(mockSymbol('A'));
        notWord.add(mockSymbol('B'));

        TextComposite word = new TextComposite(ComponentType.WORD);
        word.add(mockSymbol('X'));
        word.add(mockSymbol('Y'));

        WordByLengthComparator comparator = new WordByLengthComparator();

        assertThrows(IllegalArgumentException.class, () -> comparator.compare(notWord, word));
    }

    private TextComponent mockSymbol(char c) {
        return new com.esdc.lab2.entity.TextLeaf(c, ComponentType.SYMBOL);
    }
}
