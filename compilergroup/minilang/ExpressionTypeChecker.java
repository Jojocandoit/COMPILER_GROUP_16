package com.compilergroup.minilang;

import java.util.List;

public class ExpressionTypeChecker {

    private final SymbolTable symbols;
    private final List<SemanticError> errors;

    public ExpressionTypeChecker(
            SymbolTable symbols,
            List<SemanticError> errors
    ) {
        this.symbols = symbols;
        this.errors = errors;
    }

    public ValueType infer(Ast.Expr expression) {

        // Handle literal values
        if (expression instanceof Ast.Literal literal) {

            Object value = literal.value();

            if (value instanceof Number) {
                return ValueType.DECIMAL;
            }

            if (value instanceof String) {

                String text = value.toString();

                // Scanner preserves quotation marks in string lexemes
                if (text.startsWith("\"")
                        && text.endsWith("\"")) {
                    return ValueType.STRING;
                }

                if (text.matches("-?\\d+")) {
                    return ValueType.INTEGER;
                }

                if (text.matches("-?\\d+\\.\\d+")) {
                    return ValueType.DECIMAL;
                }

                return ValueType.STRING;
            }

            return ValueType.UNKNOWN;
        }

        // Handle variables
        if (expression instanceof Ast.Variable variable) {

            String name = variable.name().getLexeme();

            if (!symbols.contains(name)) {

                errors.add(new SemanticError(
                        variable.name(),
                        "Variable '" + name + "' has not been declared."
                ));

                return ValueType.UNKNOWN;
            }

            return symbols.getType(name);
        }

        // Handle parentheses
        if (expression instanceof Ast.Grouping grouping) {
            return infer(grouping.expression());
        }

        // Handle negative numbers
        if (expression instanceof Ast.Unary unary) {

            ValueType right = infer(unary.right());

            if (!TypeRules.isNumeric(right)
                    && right != ValueType.UNKNOWN) {

                errors.add(new SemanticError(
                        unary.operator(),
                        "Unary minus requires a numeric value."
                ));

                return ValueType.UNKNOWN;
            }

            return right;
        }

        // Handle binary expressions
        if (expression instanceof Ast.Binary binary) {

            ValueType left = infer(binary.left());
            ValueType right = infer(binary.right());

            TokenType operator = binary.operator().getType();

            switch (operator) {

                // Arithmetic operators
                case PLUS:
                case MINUS:
                case MULTIPLY:
                case DIVIDE:

                    if (left == ValueType.UNKNOWN
                            || right == ValueType.UNKNOWN) {
                        return ValueType.UNKNOWN;
                    }

                    ValueType result = TypeRules.arithmeticResult(
                            left,
                            right,
                            operator
                    );

                    if (result == ValueType.UNKNOWN) {

                        errors.add(new SemanticError(
                                binary.operator(),
                                "Arithmetic operations require numeric values."
                        ));
                    }

                    return result;

                // Relational operators
                case GREATER_THAN:
                case GREATER_EQUAL:
                case LESS_THAN:
                case LESS_EQUAL:

                    if (left == ValueType.UNKNOWN
                            || right == ValueType.UNKNOWN) {
                        return ValueType.UNKNOWN;
                    }

                    if (!TypeRules.isNumeric(left)
                            || !TypeRules.isNumeric(right)) {

                        errors.add(new SemanticError(
                                binary.operator(),
                                "Comparison operators require numeric values."
                        ));
                    }

                    return ValueType.BOOLEAN;

                // Equality operators
                case EQUAL_EQUAL:
                case NOT_EQUAL:

                    if (left == ValueType.UNKNOWN
                            || right == ValueType.UNKNOWN) {
                        return ValueType.UNKNOWN;
                    }

                    if (!TypeRules.canCompareEquality(left, right)) {

                        errors.add(new SemanticError(
                                binary.operator(),
                                "Cannot compare values of different types."
                        ));
                    }

                    return ValueType.BOOLEAN;

                default:
                    return ValueType.UNKNOWN;
            }
        }

        return ValueType.UNKNOWN;
    }
}