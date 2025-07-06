package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import com.esdc.lab2.entity.TextLeaf;

public class WordParser implements Parser {
    @Override
    public TextComponent parse(String word) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        for (char ch : word.toCharArray()) {
            wordComposite.add(new TextLeaf(ch, ComponentType.SYMBOL));
        }
        return wordComposite;
    }
}
