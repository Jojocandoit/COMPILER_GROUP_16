
package com.compilergroup.minilang;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {

    private final SymbolTable symbols;
    private final List<SemanticError> errors;

    private final ExpressionTypeChecker expressionChecker;
    private final AssignmentChecker assignmentChecker;
    private final ControlFlowChecker controlFlowChecker;

    public SemanticAnalyzer() {

        symbols = new SymbolTable();
        errors = new ArrayList<>();

        expressionChecker = new ExpressionTypeChecker(
                symbols,
                errors
        );

        assignmentChecker = new AssignmentChecker(
                symbols,
                expressionChecker,
                errors
        );

        controlFlowChecker = new ControlFlowChecker(
                expressionChecker,
                errors,
                this
        );
    }

    // Begin semantic analysis
    public List<SemanticError> analyze(
            List<Ast.Stmt> statements
    ) {

        checkStatements(statements);

        return new ArrayList<>(errors);
    }

    // Analyze a list of statements
    void checkStatements(List<Ast.Stmt> statements) {

        for (Ast.Stmt statement : statements) {
            checkStatement(statement);
        }
    }

    // Analyze an individual statement
    void checkStatement(Ast.Stmt statement) {

        if (statement instanceof Ast.Assignment assignment) {

            assignmentChecker.check(assignment);

        } else if (statement instanceof Ast.Display display) {

            expressionChecker.infer(display.expression());

        } else if (statement instanceof Ast.If ifStatement) {

            controlFlowChecker.checkIf(ifStatement);

        } else if (statement instanceof Ast.While whileStatement) {

            controlFlowChecker.checkWhile(whileStatement);

        } else if (statement instanceof Ast.For forStatement) {

            controlFlowChecker.checkFor(forStatement);
        }
    }

    // Return all semantic errors
    public List<SemanticError> getErrors() {
        return new ArrayList<>(errors);
    }

    // Return the symbol table
    public SymbolTable getSymbolTable() {
        return symbols;
    }
}
