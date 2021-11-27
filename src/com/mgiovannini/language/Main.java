package com.mgiovannini.language;

import com.mgiovannini.language.parser.Lexer;
import com.mgiovannini.language.parser.Parser;
import com.mgiovannini.language.parser.Token;
import com.mgiovannini.language.parser.ast.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (reader != null) {
            String input = reader.readLine();
            if (input.equals("")) {
                reader = null;
                continue;
            }
            Lexer lexer = new Lexer(input);
            final List<Token> tokens = lexer.tokenize();
            final List<Expression> expressions;
            try {
                expressions = new Parser(tokens).parse();
            } catch (RuntimeException e) {
                System.out.println();
                continue;
            }
            for (Expression expr : expressions)
                System.out.println(expr.eval());
        }

        /*for (double i = -4; i < 4; i += 0.01) {
            final String input = "sin(" + i + ")";
            Lexer lexer = new Lexer(input);
            final List<Token> tokens = lexer.tokenize();
            //tokens.forEach(System.out::println);
            final List<Expression> expressions;
            try {
                expressions = new Parser(tokens).parse();
            } catch (RuntimeException e) {
                System.out.println(e.toString());
                continue;
            }

            for (Expression expr : expressions)
                System.out.println(expr + " = " + expr.eval());
        }*/
    }
}
