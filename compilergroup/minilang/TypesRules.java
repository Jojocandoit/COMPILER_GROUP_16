package com.compilergroup.minilang;

public class TypeRules {

    // Check whether a type is numeric
    public static boolean isNumeric(ValueType type) {
        return type == ValueType.INTEGER
                || type == ValueType.DECIMAL;
    }

    // Check whether one type can be assigned to another
    public static boolean canAssign(
            ValueType target,
            ValueType source
    ) {

        if (target == source) {
            return true;
        }

        // An integer can safely be assigned to a decimal variable
        return target == ValueType.DECIMAL
                && source == ValueType.INTEGER;
    }

    // Determine the result of arithmetic operations
    public static ValueType arithmeticResult(
            ValueType left,
            ValueType right,
            TokenType operator
    ) {

        if (!isNumeric(left) || !isNumeric(right)) {
            return ValueType.UNKNOWN;
        }

        if (operator == TokenType.DIVIDE
                || left == ValueType.DECIMAL
                || right == ValueType.DECIMAL) {

            return ValueType.DECIMAL;
        }

        return ValueType.INTEGER;
    }

    // Check whether two values can be compared for equality
    public static boolean canCompareEquality(
            ValueType left,
            ValueType right
    ) {

        if (left == right) {
            return true;
        }

        return isNumeric(left) && isNumeric(right);
    }

}