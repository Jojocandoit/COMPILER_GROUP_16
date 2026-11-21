package com.compilergroup.minilang;

import java.util.List;

public class AssignmentChecker {

    private final SymbolTable symbols;
    private final ExpressionTypeChecker expressions;
    private final List<SemanticError> errors;

    public AssignmentChecker(
            SymbolTable symbols,
            ExpressionTypeChecker expressions,
            List<SemanticError> errors
    ) {
        this.symbols = symbols;
        this.expressions = expressions;
        this.errors = errors;
    }

    public void check(Ast.Assignment assignment) {

        String name = assignment.name().getLexeme();

        ValueType newType = expressions.infer(assignment.value());

        if (newType == ValueType.UNKNOWN) {
            return;
        }

        // If this is a new variable, add it to the symbol table
        if (!symbols.contains(name)) {

            symbols.define(name, newType);
            return;
        }

        // Check the existing variable type
        ValueType existingType = symbols.getType(name);

        if (!TypeRules.canAssign(existingType, newType)) {

            errors.add(new SemanticError(
                    assignment.name(),
                    "Cannot assign " + newType
                            + " to variable '" + name
                            + "' of type " + existingType + "."
            ));
        }
    }
}