
package com.compilergroup.minilang;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private final List<String> errors = new ArrayList<>();

    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // This method begins parsing the complete program
    public List<Ast.Stmt> parse() {

        List<Ast.Stmt> statements = new ArrayList<>();

        skipSeparators();

        while (!isAtEnd()) {

            try {
                statements.add(parseStatement());
                finishStatement();

            } catch (ParseError error) {

                errors.add(
                        "Line " + error.getToken().getLine()
                        + ": " + error.getMessage()
                );

                synchronize();
            }

            skipSeparators();
        }

        return statements;
    }

    // This method recognises the type of statement
    private Ast.Stmt parseStatement() {

        if (match(TokenType.SET)) {
            return parseAssignment();
        }

        if (match(TokenType.DISPLAY)) {
            return parseDisplay();
        }

        if (match(TokenType.IF)) {
            return parseIf();
        }

        if (match(TokenType.WHILE)) {
            return parseWhile();
        }

        if (match(TokenType.FOR)) {
            return parseFor();
        }

        throw error(peek(), "Expected a valid statement.");
    }

    // This method ensures statements are separated
    private void finishStatement() {

        if (isAtEnd()
                || check(TokenType.ELSE)
                || check(TokenType.END)) {
            return;
        }

        if (match(TokenType.NEWLINE, TokenType.SEMICOLON)) {
            return;
        }

        throw error(peek(), "Expected a new line or ';' after statement.");
    }

    // This method skips newlines and statement separators
    private void skipSeparators() {

        while (match(
                TokenType.NEWLINE,
                TokenType.SEMICOLON
        )) {
            // Continue skipping separators
        }
    }

    // This method checks whether the current token matches any supplied type
    private boolean match(TokenType... types) {

        for (TokenType type : types) {

            if (check(type)) {

                advance();

                return true;
            }
        }

        return false;
    }

    // This method checks the current token type
    private boolean check(TokenType type) {

        if (isAtEnd()) {
            return type == TokenType.EOF;
        }

        return peek().getType() == type;
    }

    // This method checks multiple possible ending tokens
    private boolean checkAny(TokenType... types) {

        for (TokenType type : types) {

            if (check(type)) {
                return true;
            }
        }

        return false;
    }

    // This method consumes a token or reports an error
    private Token consume(TokenType type, String message) {

        if (check(type)) {
            return advance();
        }

        throw error(peek(), message);
    }

    // This method moves to the next token

    private Token advance() {

        if (!isAtEnd()) {
            current++;
        }

        return previous();
    }

    // This method returns the current token

    private Token peek() {
        return tokens.get(current);
    }

    // This method returns the previously consumed token

    private Token previous() {
        return tokens.get(current - 1);
    }

    // This method checks whether all tokens have been processed

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    // Start expression parsing

    private Ast.Expr expression() {
        return equality();
    }

    // Handle addition and subtraction

    private Ast.Expr term() {

        Ast.Expr expr = factor();

        while (match(
                TokenType.PLUS,
                TokenType.MINUS
        )) {

            Token operator = previous();

            Ast.Expr right = factor();

            expr = new Ast.Binary(expr, operator, right);
        }

        return expr;
    }

    // Handle multiplication and division

    private Ast.Expr factor() {

        Ast.Expr expr = unary();

        while (match(
                TokenType.MULTIPLY,
                TokenType.DIVIDE
        )) {

            Token operator = previous();

            Ast.Expr right = unary();

            expr = new Ast.Binary(expr, operator, right);
        }

        return expr;
    }

    // Handle negative numbers

    private Ast.Expr unary() {

        if (match(TokenType.MINUS)) {

            Token operator = previous();

            Ast.Expr right = unary();

            return new Ast.Unary(operator, right);
        }

        return primary();
    }

    // Handle basic values and parentheses

    private Ast.Expr primary() {

        if (match(TokenType.INTEGER, TokenType.DECIMAL)) {
            return new Ast.Literal(previous().getLexeme());
        }

        if (match(TokenType.STRING)) {
            return new Ast.Literal(previous().getLexeme());
        }

        if (match(TokenType.IDENTIFIER)) {
            return new Ast.Variable(previous());
        }

        if (match(TokenType.LEFT_PAREN)) {

            Ast.Expr expr = expression();

            consume(
                    TokenType.RIGHT_PAREN,
                    "Expected ')' after expression."
            );

            return new Ast.Grouping(expr);
        }

        throw error(peek(), "Expected an expression.");
    }

    // Parse SET statements

    private Ast.Assignment parseAssignment() {

        Token name = consume(
                TokenType.IDENTIFIER,
                "Expected a variable name."
        );

        consume(
                TokenType.ASSIGN,
                "Expected '=' after variable name."
        );

        Ast.Expr value = expression();

        return new Ast.Assignment(name, value);
    }

    // Parse DISPLAY statements

    private Ast.Display parseDisplay() {



        Ast.Expr value = expression();

        return new Ast.Display(value);
    }

// Handle equality operators == and != private Ast.Expr equality() {
Ast.Expr expr = comparison();

while (match(
        TokenType.EQUAL_EQUAL,
        TokenType.NOT_EQUAL
)) {

    Token operator = previous();

    Ast.Expr right = comparison();

    expr = new Ast.Binary(expr, operator, right);
}

return expr;
}
// Handle comparison operators >, >=, < and <= private Ast.Expr comparison() {


Ast.Expr expr = term();

while (match(
        TokenType.GREATER_THAN,
        TokenType.GREATER_EQUAL,
        TokenType.LESS_THAN,
        TokenType.LESS_EQUAL
)) {

    Token operator = previous();

    Ast.Expr right = term();

    expr = new Ast.Binary(expr, operator, right);
}

return expr;
}

// Parse IF and ELSE statements
private Ast.If parseIf() {

    Ast.Expr condition = expression();

    consumeHeaderEnd();

    List<Ast.Stmt> thenBranch = parseBlock(
            TokenType.ELSE,
            TokenType.END
    );

    List<Ast.Stmt> elseBranch = new ArrayList<>();

    if (match(TokenType.ELSE)) {

        consumeHeaderEnd();

        elseBranch = parseBlock(TokenType.END);
    }

    consume(
            TokenType.END,
            "Expected END to close IF statement."
    );

    return new Ast.If(condition, thenBranch, elseBranch);
}

// Parse statements inside IF and ELSE blocks
private List<Ast.Stmt> parseBlock(TokenType... endings) {

    List<Ast.Stmt> statements = new ArrayList<>();

    skipSeparators();

    while (!isAtEnd() && !checkAny(endings)) {

        statements.add(parseStatement());

        finishStatement();

        skipSeparators();
    }

    return statements;
}

// Require a new line after an IF, WHILE or FOR header
private void consumeHeaderEnd() {

    consume(
            TokenType.NEWLINE,
            "Expected a new line after the statement header."
    );

    skipSeparators();
}
// Parse WHILE loops
private Ast.While parseWhile() {

    Ast.Expr condition = expression();

    consumeHeaderEnd();

    List<Ast.Stmt> body = parseBlock(TokenType.END);

    consume(
            TokenType.END,
            "Expected END to close WHILE loop."
    );

    return new Ast.While(condition, body);
}

// Parse FOR loops
private Ast.For parseFor() {

    consume(
            TokenType.LEFT_PAREN,
            "Expected '(' after FOR."
    );

    consume(
            TokenType.SET,
            "Expected SET in FOR initializer."
    );

    Ast.Assignment initializer = parseAssignment();

    consume(
            TokenType.SEMICOLON,
            "Expected ';' after FOR initializer."
    );

    Ast.Expr condition = expression();

    consume(
            TokenType.SEMICOLON,
            "Expected ';' after FOR condition."
    );

    consume(
            TokenType.SET,
            "Expected SET in FOR increment."
    );

    Ast.Assignment increment = parseAssignment();

    consume(
            TokenType.RIGHT_PAREN,
            "Expected ')' after FOR header."
    );

    consumeHeaderEnd();

    List<Ast.Stmt> body = parseBlock(TokenType.END);

    consume(
            TokenType.END,
            "Expected END to close FOR loop."
    );

    return new Ast.For(
            initializer,
            condition,
            increment,
            body
    );
}




    
}