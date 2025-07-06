package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SentenceParserTest {

    @Test
    void parse_shouldHandleExtraWhitespace() {
        Parser nextMock = mock(Parser.class);
        when(nextMock.parse(anyString())).thenAnswer(inv -> new TextComposite(ComponentType.WORD));
        SentenceParser parser = new SentenceParser(nextMock);

        String sentence = "  spaced    words  ";
        TextComponent result = parser.parse(sentence);

        assertEquals(2, result.getChildren().size());
        verify(nextMock, times(2)).parse(anyString());
    }

    @Test
    void parse_emptyString_shouldReturnEmptySentence() {
        Parser nextMock = mock(Parser.class);
        SentenceParser parser = new SentenceParser(nextMock);

        TextComponent result = parser.parse("");

        assertEquals(1, result.getChildren().size());
    }
}
