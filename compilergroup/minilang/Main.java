package com.compilergroup.minilang;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = """
                SET age = 20
                SET price = 12.50
                SET name = "Ama"
               DISPLAY price
                DISPLAY 10 + 5 >= 15
                """;

        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}