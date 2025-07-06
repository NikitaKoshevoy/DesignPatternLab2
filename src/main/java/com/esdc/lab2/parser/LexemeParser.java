package com.esdc.lab2.parser;

import com.esdc.lab2.entity.*;
import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;

public class LexemeParser implements Parser {
    private final Parser next;

    public LexemeParser(Parser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String lexeme) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        lexemeComposite.add(next.parse(lexeme));
        return lexemeComposite;
    }
}
