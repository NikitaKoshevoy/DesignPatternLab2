package com.esdc.lab2.comparator;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;

import java.util.Comparator;

public class ParagraphBySentenceCountComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent p1, TextComponent p2) {
        if (p1.getType() != ComponentType.PARAGRAPH || p2.getType() != ComponentType.PARAGRAPH) {
            throw new IllegalArgumentException("Components must be paragraphs");
        }
        return Integer.compare(p1.getChildren().size(), p2.getChildren().size());
    }
}
