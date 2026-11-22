
package com.compilergroup.minilang;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Sample MiniLang program
        String source =
                "SET total = 10 + 5\n" +
                "SET age = 20\n" +
                "IF age >= 18\n" +
                "DISPLAY \"Adult\"\n" +
                "ELSE\n" +
                "DISPLAY \"Minor\"\n" +
                "END\n" +
                "DISPLAY total\n";

        // PHASE 1: LEXICAL ANALYSIS
        System.out.println("========== LEXICAL ANALYSIS ==========");

        Scanner scanner = new Scanner(source);

        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }

        // PHASE 2: SYNTAX ANALYSIS
        System.out.println("\n========== SYNTAX ANALYSIS ==========");

        Parser parser = new Parser(tokens);

        List<Ast.Stmt> statements = parser.parse();

        if (!parser.getErrors().isEmpty()) {

            for (String error : parser.getErrors()) {
                System.out.println(error);
            }

            return;
        }

        System.out.println("Syntax analysis completed successfully.");

        // PHASE 3: SEMANTIC ANALYSIS
        System.out.println("\n========== SEMANTIC ANALYSIS ==========");

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

        List<SemanticError> semanticErrors =
                semanticAnalyzer.analyze(statements);

        if (!semanticErrors.isEmpty()) {

            for (SemanticError error : semanticErrors) {
                System.out.println(error);
            }

            return;
        }

        System.out.println("Semantic analysis completed successfully.");

        // Display the symbol table
        System.out.println("\n========== SYMBOL TABLE ==========");

        semanticAnalyzer.getSymbolTable()
                .getAllSymbols()
                .forEach((name, type) ->
                        System.out.println(name + " : " + type)
                );

        // INTERMEDIATE REPRESENTATION
        System.out.println("\n========== INTERMEDIATE REPRESENTATION ==========");

        IRGenerator generator = new IRGenerator();

        List<IRInstruction> ir = generator.generate(statements);

        for (IRInstruction instruction : ir) {
            System.out.println(instruction);
        }

        System.out.println("\nCompiler phases completed.");
    }
}
