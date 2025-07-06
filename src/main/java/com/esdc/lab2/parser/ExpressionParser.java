package com.esdc.lab2.parser;

import com.esdc.lab2.entity.ComponentType;
import com.esdc.lab2.entity.TextLeaf;
import com.esdc.lab2.entity.TextComponent;
import com.esdc.lab2.interpreter.SimpleEvaluator;

public class ExpressionParser implements Parser {
    private static final String EXPRESSION_REGEX = "[-+*/().\\d]+";
    private final Parser next;

    public ExpressionParser(Parser next) {
        this.next = next;
    }

    @Override
    public TextComponent parse(String lexeme) {
        if (lexeme.matches(EXPRESSION_REGEX)) {
            try {
                int result = SimpleEvaluator.evaluate(lexeme);
                return new TextLeaf((char) result, ComponentType.WORD);
            } catch (Exception e) {

                return next.parse(lexeme);
            }
        }
        return next.parse(lexeme);
    }
}
