package com.compilergroup.minilang;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String source =
                "SET student = 20\n" +
                "DISPLAY \"Hello\"\n" +
                "IF student >= 18\n" +
                "DISPLAY \"Adult\"\n" +
                "END\n";

        Scanner scanner = new Scanner(source);

        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}