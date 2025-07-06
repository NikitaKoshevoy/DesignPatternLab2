package com.esdc.lab2.parser;

import com.esdc.lab2.entity.TextComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextParserTest {

    @Test
    void parse_emptyText_shouldReturnEmptyText() {
        Parser mockNext = mock(Parser.class);
        TextParser parser = new TextParser(mockNext);

        TextComponent result = parser.parse("");
        assertEquals(0, result.getChildren().size());
        verifyNoInteractions(mockNext);
    }

    @Test
    void parse_noParagraphs_shouldReturnEmptyText() {
        Parser mockNext = mock(Parser.class);
        TextParser parser = new TextParser(mockNext);

        TextComponent result = parser.parse("No leading spaces\nAnother one");
        assertEquals(0, result.getChildren().size());
        verifyNoInteractions(mockNext);
    }
}
