package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserChainBuilderTest {

    @Test
    void build_shouldReturnNonNullParser() {
        TextParser parser = ParserChainBuilder.build();
        assertNotNull(parser);
    }

    @Test
    void build_parserShouldProduceTextComponent() {
        TextParser parser = ParserChainBuilder.build();
        String text = "    First sentence. Another one.\n    Second paragraph.";
        TextComponent result = parser.parse(text);
        assertEquals(ComponentType.TEXT, result.getType());
        assertFalse(result.getChildren().isEmpty());
        assertEquals(ComponentType.PARAGRAPH, result.getChildren().get(0).getType());
    }

    @Test
    void build_parserShouldHandleEmptyInput() {
        TextParser parser = ParserChainBuilder.build();
        TextComponent result = parser.parse("");
        assertEquals(ComponentType.TEXT, result.getType());
        assertTrue(result.getChildren().isEmpty());
    }
}
