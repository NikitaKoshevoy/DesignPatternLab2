package com.esdc.lab2.comparator;

import com.esdc.lab2.entity.TextComponent;

import java.util.Comparator;

public class LexemeBySymbolFrequencyComparator implements Comparator<TextComponent> {
    private final char symbol;

    public LexemeBySymbolFrequencyComparator(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        long freq1 = o1.toString().chars().filter(c -> c == symbol).count();
        long freq2 = o2.toString().chars().filter(c -> c == symbol).count();
        if (freq1 != freq2) {
            return Long.compare(freq2, freq1);
        }
        return o1.toString().compareToIgnoreCase(o2.toString());
    }
}
