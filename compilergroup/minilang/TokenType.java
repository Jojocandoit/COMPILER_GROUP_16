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

    // Variable names and values
    IDENTIFIER,
    INTEGER,
    DECIMAL,
    STRING,

    // Arithmetic operators
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,

    // Comparison operators
    GREATER_THAN,
    LESS_THAN,
    GREATER_EQUAL,
    LESS_EQUAL,
    EQUAL_EQUAL,
    NOT_EQUAL,

    // Assignment operator
    ASSIGN,

    // Symbols
    LEFT_PAREN,
    RIGHT_PAREN,
    SEMICOLON,

    // Other
    NEWLINE,
    EOF,
    INVALID
}