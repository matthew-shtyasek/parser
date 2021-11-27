package com.mgiovannini.language.lib;

import com.mgiovannini.language.parser.ast.Expression;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
interface MathFunction {
    double get(double d);
}

public class Functions {
    private static final List<String> functions;

    static {
        functions = new ArrayList<>();
        functions.add("sin");
        functions.add("cos");
        functions.add("tg");
        functions.add("ctg");
    }

    public static double get (final String name, final Expression expr) {
        switch (name) {
            case "sin":
                return Math.sin(expr.eval());
            case "cos":
                return Math.cos(expr.eval());
            case "tg":
                return Math.tan(expr.eval());
            case "ctg":
                return 1 / Math.tan(expr.eval());
            default:
                throw new RuntimeException(String.format("Неизвестная тригонометрическая функция %s(%s)", name, expr));
        }
    }

    public static boolean isExists(String name) {
        return functions.contains(name);
    }
}
