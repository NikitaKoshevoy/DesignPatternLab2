package com.esdc.lab2.parser;

public class ParserChainBuilder {

    public static TextParser build() {
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser(wordParser);
        LexemeParser lexemeParser = new LexemeParser(expressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
        return textParser;
    }
}
