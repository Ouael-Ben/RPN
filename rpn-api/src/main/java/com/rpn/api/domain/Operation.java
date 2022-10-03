package com.rpn.api.domain;

import com.rpn.api.exception.RpnApiException;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum Operation {

    PLUS("+", Double::sum),
    MULTI("*", (Double a, Double b) -> a * b),
    DIV("/", (Double a, Double b) -> { if(b != null && b != 0) return a / b; throw new RpnApiException("Divisor should be different to zero and null"); }),
    MINUS("-", (Double a, Double b) -> a - b);

    private final String symbole;
    private BiFunction<Double, Double, Double> biFunction;

    Operation(String symbole, BiFunction<Double, Double, Double> biFunction) {
        this.symbole = symbole;
        this.biFunction = biFunction;
    }

    public static Operation getOperationBySymbole(String symbole) {
        for (Operation operation : Operation.values()) {
            if (operation.symbole.equals(symbole)) {
                return operation;
            }
        }
        throw new RpnApiException("This operator doesn't exist.");
    }

    public static List<String> getAllOperand() {
        return Arrays.stream(Operation.values()).map(operation -> operation.symbole).toList();
    }
    public Double calculate(Double a, Double b) {
        return biFunction.apply(a, b);
    }
}
