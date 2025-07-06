package com.esdc.lab2.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> children = new ArrayList<>();
    private final ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        children.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent child : children) {
            builder.append(child.toString());
        }

        if (type == ComponentType.PARAGRAPH) {
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }
}
