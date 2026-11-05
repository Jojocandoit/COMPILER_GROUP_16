package com.compilergroup.minilang;

public class ParseError extends RuntimeException {

    private final Token token;

    public ParseError(Token token, String message) {
        super(message);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}