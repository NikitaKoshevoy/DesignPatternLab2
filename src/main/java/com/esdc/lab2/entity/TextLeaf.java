package com.esdc.lab2.entity;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final char value;
    private final ComponentType type;

    public TextLeaf(char value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Leaf does not support add()");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Leaf does not support remove()");
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
