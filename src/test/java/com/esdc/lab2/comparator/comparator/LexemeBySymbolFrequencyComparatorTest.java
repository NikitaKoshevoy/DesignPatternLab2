package com.esdc.lab2.comparator.comparator;

import com.esdc.lab2.comparator.LexemeBySymbolFrequencyComparator;
import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import com.esdc.lab2.entity.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexemeBySymbolFrequencyComparatorTest {

    private TextComponent createLexeme(String text) {
        TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
        for (char c : text.toCharArray()) {
            lexeme.add(new TextLeaf(c, ComponentType.SYMBOL));
        }
        return lexeme;
    }

    @Test
    void compare_shouldSortBySymbolFrequencyDesc() {
        LexemeBySymbolFrequencyComparator comparator = new LexemeBySymbolFrequencyComparator('a');

        TextComponent lexeme1 = createLexeme("banana");
        TextComponent lexeme2 = createLexeme("apple");


        assertTrue(comparator.compare(lexeme1, lexeme2) < 0);
        assertTrue(comparator.compare(lexeme2, lexeme1) > 0);
    }

    @Test
    void compare_shouldSortLexicographicallyIfFrequencyEqual() {
        LexemeBySymbolFrequencyComparator comparator = new LexemeBySymbolFrequencyComparator('a');

        TextComponent lexeme1 = createLexeme("data");
        TextComponent lexeme2 = createLexeme("java");


        int result = comparator.compare(lexeme1, lexeme2);
        assertTrue(result < 0);
    }

    @Test
    void compare_shouldHandleNoSymbol() {
        LexemeBySymbolFrequencyComparator comparator = new LexemeBySymbolFrequencyComparator('z');

        TextComponent lexeme1 = createLexeme("apple");
        TextComponent lexeme2 = createLexeme("banana");

        assertEquals(-1, comparator.compare(lexeme1, lexeme2));
    }

    @Test
    void compare_shouldHandleEmptyLexemes() {
        LexemeBySymbolFrequencyComparator comparator = new LexemeBySymbolFrequencyComparator('a');

        TextComponent empty1 = createLexeme("");
        TextComponent empty2 = createLexeme("");

        assertEquals(0, comparator.compare(empty1, empty2));
    }
}
