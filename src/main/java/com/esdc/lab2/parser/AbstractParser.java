package com.esdc.lab2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser implements Parser {
    protected final Parser next;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public AbstractParser(Parser next) {
        this.next = next;
    }
}
