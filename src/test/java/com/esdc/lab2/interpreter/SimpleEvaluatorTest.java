package com.esdc.lab2.interpreter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleEvaluatorTest {

    @Test
    void testSimpleAddition() {
        assertEquals(3, SimpleEvaluator.evaluate("2 + 3"));
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals(-6, SimpleEvaluator.evaluate("10 - 6"));
    }

    @Test
    void testMultiplication() {
        assertEquals(15, SimpleEvaluator.evaluate("3 * 5"));
    }

    @Test
    void testDivision() {
        assertEquals(4, SimpleEvaluator.evaluate("20 / 5"));
    }

    @Test
    void testDivisionByZero() {
        assertEquals(0, SimpleEvaluator.evaluate("5 / 0"));
    }

    @Test
    void testExpressionWithMultipleOperators() {
        assertEquals(12, SimpleEvaluator.evaluate("2 + 3 * 4"));
    }

    @Test
    void testExpressionWithParentheses() {
        assertEquals(12, SimpleEvaluator.evaluate("(2 + 3) * 4"));
    }

    @Test
    void testExpressionWithNestedParentheses() {
        assertEquals(6, SimpleEvaluator.evaluate("((1 + 2) * 3)"));
    }

    @Test
    void testExpressionWithWhitespace() {
        assertEquals(4, SimpleEvaluator.evaluate(" 3 + 4 "));
    }

    @Test
    void testExpressionWithNegativeNumbers() {
        assertEquals(-3, SimpleEvaluator.evaluate("2 - 3"));
        assertEquals(-5, SimpleEvaluator.evaluate("-5"));
        assertEquals(6, SimpleEvaluator.evaluate("-2 * -3"));
    }

    @Test
    void testComplexExpression() {
        assertEquals(0, SimpleEvaluator.evaluate("3 + 4 * 2 / (1 - 5)"));
    }
}
