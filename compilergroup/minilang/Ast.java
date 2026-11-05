package com.compilergroup.minilang;

import java.util.List;

public final class Ast {

    private Ast() {
    }

    // Expression types
    public interface Expr {
    }

    public record Binary(
            Expr left,
            Token operator,
            Expr right
    ) implements Expr {
    }

    public record Unary(
            Token operator,
            Expr right
    ) implements Expr {
    }

    public record Literal(Object value) implements Expr {
    }

    public record Variable(Token name) implements Expr {
    }

    public record Grouping(Expr expression) implements Expr {
    }

    // Statement types
    public interface Stmt {
    }

    public record Assignment(
            Token name,
            Expr value
    ) implements Stmt {
    }

    public record Display(Expr expression) implements Stmt {
    }

    public record If(
            Expr condition,
            List<Stmt> thenBranch,
            List<Stmt> elseBranch
    ) implements Stmt {
    }

    public record While(
            Expr condition,
            List<Stmt> body
    ) implements Stmt {
    }

    public record For(
            Assignment initializer,
            Expr condition,
            Assignment increment,
            List<Stmt> body
    ) implements Stmt {
    }
}