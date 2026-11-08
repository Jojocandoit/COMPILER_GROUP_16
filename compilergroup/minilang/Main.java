package com.compilergroup.minilang;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String source =
                "SET student = 20\n" +
                "DISPLAY \"Hello\"\n" +
                "IF student >= 18\n" +
                "DISPLAY \"Adult\"\n" +
                "ELSE\n" +
                "DISPLAY \"Minor\"\n" +
                "END\n" +
                "SET count = 1\n" +
                "WHILE count <= 3\n" +
                "DISPLAY count\n" +
                "SET count = count + 1\n" +
                "END\n";

        // Phase 1: Lexical analysis
        Scanner scanner = new Scanner(source);

        List<Token> tokens = scanner.scanTokens();

        System.out.println("========== TOKENS ==========");

        for (Token token : tokens) {
            System.out.println(token);
        }

        // Phase 2: Syntax analysis
        Parser parser = new Parser(tokens);

        List<Ast.Stmt> statements = parser.parse();

        System.out.println("\n========== PARSER OUTPUT ==========");

        for (Ast.Stmt statement : statements) {
            System.out.println(statement);
        }

        // Display syntax errors
        System.out.println("\n========== SYNTAX ERRORS ==========");

        if (parser.getErrors().isEmpty()) {

            System.out.println("No syntax errors found.");

        } else {

            for (String error : parser.getErrors()) {
                System.out.println(error);
            }
        }
    }
}