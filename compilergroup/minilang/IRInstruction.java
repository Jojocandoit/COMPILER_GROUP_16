package com.compilergroup.minilang;

public record IRInstruction(
        String opcode,
        String arg1,
        String arg2,
        String result
) {

    @Override
    public String toString() {

        switch (opcode) {

            case "LABEL":
                return result + ":";

            case "GOTO":
                return "GOTO " + result;

            case "IF_FALSE":
                return "IF_FALSE " + arg1 + " GOTO " + result;

            case "PRINT":
                return "PRINT " + arg1;

            case "ASSIGN":
                return result + " = " + arg1;

            case "NEG":
                return result + " = -" + arg1;

            default:

                if (arg2 == null) {
                    return result + " = " + arg1;
                }

                return result + " = "
                        + arg1 + " "
                        + opcode + " "
                        + arg2;
        }
    }
}
