package com.esdc.lab2.parser;

import com.esdc.lab2.entity.*;
import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;

import java.util.regex.Pattern;

public class TextParser implements Parser {
    private static final String PARAGRAPH_REGEX = "(?<=\\n|^)(\\s{4}.*?)(?=(\\n\\s{4}|\\Z))";
    private final Parser next;

    public TextParser(Parser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        var matcher = Pattern.compile(PARAGRAPH_REGEX, Pattern.DOTALL).matcher(text);
        while (matcher.find()) {
            String paragraphText = matcher.group().trim();
            textComposite.add(next.parse(paragraphText));
        }
        return textComposite;
    }
}
