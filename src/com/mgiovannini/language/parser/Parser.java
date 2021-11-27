package com.mgiovannini.language.parser;

import com.mgiovannini.language.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");

    private final List<Token> tokens;
    private final int size;
    private int pos; //позиция токенов в списке tokens

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public List<Expression> parse() {
        final List<Expression> result = new ArrayList<>();
        while (!match(TokenType.EOF)) {
            result.add(expression());
        }
        return result;
    }

    //==============================

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = multiplicative();
        while (true) {
            if (match(TokenType.PLUS))
                result = new BinaryExpression('+', result, multiplicative());
            else if (match(TokenType.MINUS))
                result = new BinaryExpression('-', result, multiplicative());
            else break;
        }
        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();
        while (true) {
            if (match(TokenType.STAR))
                result = new BinaryExpression('*', result, unary());
            else if (match(TokenType.SLASH))
                result = new BinaryExpression('/', result, unary());
            else break;
        }
        return result;
    }

    private Expression unary() {
        if (match(TokenType.MINUS))
            return new UnaryExpression('-', primary());
        return primary();
    }

    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER))
            return new NumberExpression(Double.parseDouble(current.getText()));
        else if (match(TokenType.HEX_NUMBER))
            return new NumberExpression(Long.parseLong(current.getText(), 16));
        else if (match(TokenType.WORD))
            return new ConstantExpression(current.getText(), paren(current, true));
        return paren(current, false);
    }

    private Expression paren(final Token current, final boolean flag) {
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        } else if (flag)
            return null;
        throw new RuntimeException("Неизвестное выражение " + current.toString());
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        ++pos;
        return true;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
}
