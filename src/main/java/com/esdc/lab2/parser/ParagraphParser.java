package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;

import java.util.regex.Pattern;

public class ParagraphParser implements Parser {
    private static final String SENTENCE_REGEX = "[A-Z][^.!?…]*[.!?…]";
    private final Parser next;

    public ParagraphParser(Parser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String paragraph) {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        var matcher = Pattern.compile(SENTENCE_REGEX).matcher(paragraph);
        while (matcher.find()) {
            String sentence = matcher.group().trim();
            paragraphComposite.add(next.parse(sentence));
        }
        return paragraphComposite;
    }
}
