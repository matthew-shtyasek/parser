package com.mgiovannini.language.parser.ast;

import com.mgiovannini.language.lib.Constants;
import com.mgiovannini.language.lib.Functions;

public class ConstantExpression implements Expression {

    private final String name;
    private final Expression expr1;

    public ConstantExpression(String name) {
        this(name, null);
    }
    public ConstantExpression(String name, Expression expr1) {
        this.name = name;
        this.expr1 = expr1;
    }

    @Override
    public double eval() {
        if (!Constants.isExists(this.name) && !Functions.isExists(this.name)) throw new RuntimeException("Константы несуществует!");
        if (this.expr1 == null)
            return Constants.get(this.name);
        return Functions.get(this.name, this.expr1);
    }

    @Override
    public String toString() {
        if (this.expr1 == null)
            return String.format("%s", this.name);
        return String.format("%s(%s)", this.name, this.expr1);
    }
}
