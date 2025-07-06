package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {

    @Test
    void parse_shouldReturnWordCompositeWithCorrectType() {
        WordParser parser = new WordParser();
        TextComponent result = parser.parse("test");

        assertEquals(ComponentType.WORD, result.getType());
        assertTrue(result instanceof TextComposite);
    }

    @Test
    void parse_shouldContainCorrectNumberOfSymbols() {
        WordParser parser = new WordParser();
        String word = "abc";
        TextComponent result = parser.parse(word);

        assertEquals(word.length(), result.getChildren().size());
    }

    @Test
    void parse_emptyString_shouldReturnEmptyComposite() {
        WordParser parser = new WordParser();
        TextComponent result = parser.parse("");

        assertEquals(0, result.getChildren().size());
    }
}
