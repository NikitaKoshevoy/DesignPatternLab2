package com.esdc.lab2.comparator;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;

import java.util.Comparator;

public class WordByLengthComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent w1, TextComponent w2) {
        if (w1.getType() != ComponentType.WORD || w2.getType() != ComponentType.WORD) {
            throw new IllegalArgumentException("Components must be words");
        }
        return Integer.compare(w1.getChildren().size(), w2.getChildren().size());
    }
}
