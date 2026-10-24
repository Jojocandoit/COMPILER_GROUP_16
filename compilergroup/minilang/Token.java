package com.compilergroup.minilang;

/**
 * Represents a single token produced by the lexer.
 */
public class Token {

    private final TokenType type;
    private final String lexeme;
    private final int line;

    /**
     * Creates a token.
     *
     * @param type   the category of the token
     * @param lexeme the exact text matched in the source
     * @param line   the source line the token was found on
     */
    public Token(TokenType type, String lexeme, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
    }

    /** Returns the token's category. */
    public TokenType getType() {
        return type;
    }

    /** Returns the exact text matched in the source. */
    public String getLexeme() {
        return lexeme;
    }

    /** Returns the source line number. */
    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("Token{type=%s, lexeme='%s', line=%d}", type, lexeme, line);
    }
}