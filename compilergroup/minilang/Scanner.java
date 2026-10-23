package com.compilergroup.minilang;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }

        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }

        return source.charAt(current + 1);
    }

    private void addToken(TokenType type) {
        String lexeme = source.substring(start, current);
        tokens.add(new Token(type, lexeme, line));
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", line));

        return tokens;
    }

    private void scanToken() {
        char c = advance();

        switch (c) {
            case ' ':
            case '\r':
            case '\t':
                break;

            case '\n':
                tokens.add(new Token(TokenType.NEWLINE, "\\n", line));
                line++;
                break;

            default:
                System.out.println(
                        "Character not handled at line " + line + ": " + c
                );
                break;
        }
    }
}