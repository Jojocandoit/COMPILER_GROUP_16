package com.compilergroup.minilang;

public class SemanticError {

    private final Token token;
    private final String message;

    public SemanticError(Token token, String message) {
        this.token = token;
        this.message = message;
    }

    public Token getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public int getLine() {
        return token == null ? -1 : token.getLine();
    }

    @Override
    public String toString() {

        if (token == null) {
            return "Semantic Error: " + message;
        }

        return "Line " + token.getLine()
                + ": " + message;
    }
}