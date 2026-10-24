package com.compilergroup.minilang;
public enum TokenType {

    // Keywords
    SET,
    DISPLAY,
    IF,
    ELSE,
    WHILE,
    FOR,
    END,

    // Identifiers and values
    IDENTIFIER,
    INTEGER,
    DECIMAL,
    STRING,

    // Operators
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    ASSIGN,
    EQUAL_EQUAL,
    NOT_EQUAL,
    GREATER_THAN,
    GREATER_EQUAL,
    LESS_THAN,
    LESS_EQUAL,

    // Symbols
    LEFT_PAREN,
    RIGHT_PAREN,
    SEMICOLON,
    COMMA,
    NEWLINE,

    // End of file
    EOF
}