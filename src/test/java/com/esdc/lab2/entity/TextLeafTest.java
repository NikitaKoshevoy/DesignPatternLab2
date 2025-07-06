package com.esdc.lab2.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextLeafTest {

    @Test
    void constructorAndGetters_shouldWorkCorrectly() {
        TextLeaf leaf = new TextLeaf('A', ComponentType.SYMBOL);
        assertEquals(ComponentType.SYMBOL, leaf.getType());
        assertEquals("A", leaf.toString());
    }

    @Test
    void getChildren_shouldReturnEmptyList() {
        TextLeaf leaf = new TextLeaf('B', ComponentType.SYMBOL);
        List<TextComponent> children = leaf.getChildren();
        assertNotNull(children);
        assertTrue(children.isEmpty());
    }

    @Test
    void add_shouldThrowUnsupportedOperationException() {
        TextLeaf leaf = new TextLeaf('C', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(new TextLeaf('D', ComponentType.SYMBOL)));
    }

    @Test
    void remove_shouldThrowUnsupportedOperationException() {
        TextLeaf leaf = new TextLeaf('E', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.remove(new TextLeaf('F', ComponentType.SYMBOL)));
    }
}
