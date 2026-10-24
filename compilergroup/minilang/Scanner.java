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

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z')
                || c == '_';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    // Read identifiers and recognize keywords
    private void scanIdentifier() {
        while (isLetter(peek()) || isDigit(peek())) {
            advance();
        }

        String text = source.substring(start, current);

        switch (text) {
            case "SET":
                addToken(TokenType.SET);
                break;
            case "DISPLAY":
                addToken(TokenType.DISPLAY);
                break;
            case "IF":
                addToken(TokenType.IF);
                break;
            case "ELSE":
                addToken(TokenType.ELSE);
                break;
            case "WHILE":
                addToken(TokenType.WHILE);
                break;
            case "FOR":
                addToken(TokenType.FOR);
                break;
            case "END":
                addToken(TokenType.END);
                break;
            default:
                addToken(TokenType.IDENTIFIER);
                break;
        }
    }

    // Read integer and decimal numbers
    private void scanNumber() {
        while (isDigit(peek())) {
            advance();
        }

        if (peek() == '.' && isDigit(peekNext())) {
            advance();

            while (isDigit(peek())) {
                advance();
            }

            addToken(TokenType.DECIMAL);
        } else {
            addToken(TokenType.INTEGER);
        }
    }

    // Read a quoted string
    private void scanString() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }
            advance();
        }

        if (isAtEnd()) {
            System.out.println(
                    "Unterminated string starting on line " + line
            );
            return;
        }

        // Consume the closing quotation mark
        advance();

        addToken(TokenType.STRING);
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

        // Ignore comments beginning with //
        if (c == '/' && peek() == '/') {
            while (peek() != '\n' && !isAtEnd()) {
                advance();
            }
            return;
        }

        // Recognize identifiers and keywords
        if (isLetter(c)) {
            scanIdentifier();
            return;
        }

        // Recognize numbers
        if (isDigit(c)) {
            scanNumber();
            return;
        }

        // Recognize strings
        if (c == '"') {
            scanString();
            return;
        }

        switch (c) {
            case ' ':
            case '\r':
            case '\t':
                break;

            case '\n':
                tokens.add(new Token(TokenType.NEWLINE, "\\n", line));
                line++;
                break;

            case '+':
                addToken(TokenType.PLUS);
                break;

            case '-':
                addToken(TokenType.MINUS);
                break;

            case '*':
                addToken(TokenType.MULTIPLY);
                break;

            case '/':
                addToken(TokenType.DIVIDE);
                break;

            case '=':
                if (peek() == '=') {
                    advance();
                    addToken(TokenType.EQUAL_EQUAL);
                } else {
                    addToken(TokenType.ASSIGN);
                }
                break;

            case '>':
                if (peek() == '=') {
                    advance();
                    addToken(TokenType.GREATER_EQUAL);
                } else {
                    addToken(TokenType.GREATER_THAN);
                }
                break;

            case '<':
                if (peek() == '=') {
                    advance();
                    addToken(TokenType.LESS_EQUAL);
                } else {
                    addToken(TokenType.LESS_THAN);
                }
                break;

            case '!':
                if (peek() == '=') {
                    advance();
                    addToken(TokenType.NOT_EQUAL);
                } else {
                    System.out.println(
                            "Invalid character at line " + line + ": " + c
                    );
                }
                break;

            case '(':
                addToken(TokenType.LEFT_PAREN);
                break;

            case ')':
                addToken(TokenType.RIGHT_PAREN);
                break;

            case ';':
                addToken(TokenType.COMMA);
                break;

            default:
                System.out.println(
                        "Character not handled at line " + line + ": " + c
                );
                break;
        }
    }
}
