package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;

public class SentenceParser implements Parser {
    private final Parser next;

    public SentenceParser(Parser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String sentence) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = sentence.trim().split("\\s+");
        for (String lexeme : lexemes) {
            sentenceComposite.add(next.parse(lexeme));
        }
        return sentenceComposite;
    }
}
