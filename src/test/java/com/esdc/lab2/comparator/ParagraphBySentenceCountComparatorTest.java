package com.esdc.lab2.comparator;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.entity.TextComposite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphBySentenceCountComparatorTest {

    @Test
    void compare_shouldOrderBySentenceCount() {
        TextComponent paragraphWithOneSentence = new TextComposite(ComponentType.PARAGRAPH);
        paragraphWithOneSentence.add(new TextComposite(ComponentType.SENTENCE));

        TextComponent paragraphWithTwoSentences = new TextComposite(ComponentType.PARAGRAPH);
        paragraphWithTwoSentences.add(new TextComposite(ComponentType.SENTENCE));
        paragraphWithTwoSentences.add(new TextComposite(ComponentType.SENTENCE));

        ParagraphBySentenceCountComparator comparator = new ParagraphBySentenceCountComparator();

        int result = comparator.compare(paragraphWithOneSentence, paragraphWithTwoSentences);
        assertTrue(result < 0);
    }

    @Test
    void compare_shouldReturnZeroWhenEqual() {
        TextComponent paragraph1 = new TextComposite(ComponentType.PARAGRAPH);
        paragraph1.add(new TextComposite(ComponentType.SENTENCE));
        paragraph1.add(new TextComposite(ComponentType.SENTENCE));

        TextComponent paragraph2 = new TextComposite(ComponentType.PARAGRAPH);
        paragraph2.add(new TextComposite(ComponentType.SENTENCE));
        paragraph2.add(new TextComposite(ComponentType.SENTENCE));

        ParagraphBySentenceCountComparator comparator = new ParagraphBySentenceCountComparator();

        int result = comparator.compare(paragraph1, paragraph2);
        assertEquals(0, result);
    }

    @Test
    void compare_shouldThrowExceptionWhenNotParagraph() {
        TextComponent notParagraph = new TextComposite(ComponentType.SENTENCE);
        TextComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);

        ParagraphBySentenceCountComparator comparator = new ParagraphBySentenceCountComparator();

        assertThrows(IllegalArgumentException.class, () -> comparator.compare(notParagraph, paragraph));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(paragraph, notParagraph));
    }
}
