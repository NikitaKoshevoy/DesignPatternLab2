package com.esdc.lab2.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

    @Test
    void addAndGetChildren_shouldWorkCorrectly() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        TextLeaf leaf1 = new TextLeaf('A', ComponentType.SYMBOL);
        TextLeaf leaf2 = new TextLeaf('B', ComponentType.SYMBOL);

        composite.add(leaf1);
        composite.add(leaf2);

        List<TextComponent> children = composite.getChildren();
        assertEquals(2, children.size());
        assertEquals(leaf1, children.get(0));
        assertEquals(leaf2, children.get(1));
    }

    @Test
    void remove_shouldRemoveChild() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        TextLeaf leaf = new TextLeaf('X', ComponentType.SYMBOL);

        composite.add(leaf);
        composite.remove(leaf);

        assertTrue(composite.getChildren().isEmpty());
    }

    @Test
    void getChildren_shouldReturnUnmodifiableList() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        composite.add(new TextLeaf('Z', ComponentType.SYMBOL));
        assertThrows(UnsupportedOperationException.class, () -> composite.getChildren().add(new TextLeaf('A', ComponentType.SYMBOL)));
    }

    @Test
    void toString_shouldConcatenateChildren() {
        TextComposite composite = new TextComposite(ComponentType.SENTENCE);
        composite.add(new TextLeaf('H', ComponentType.SYMBOL));
        composite.add(new TextLeaf('i', ComponentType.SYMBOL));
        assertEquals("Hi", composite.toString());
    }

    @Test
    void toString_shouldAddNewlineForParagraph() {
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        paragraph.add(new TextLeaf('T', ComponentType.SYMBOL));
        paragraph.add(new TextLeaf('e', ComponentType.SYMBOL));
        paragraph.add(new TextLeaf('s', ComponentType.SYMBOL));
        paragraph.add(new TextLeaf('t', ComponentType.SYMBOL));
        assertEquals("Test" + System.lineSeparator(), paragraph.toString());
    }
}
