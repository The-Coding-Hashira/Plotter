package bg.sofia.tu.iti.math.core.calculator;

import bg.sofia.tu.iti.math.core.Calculation;

import java.util.Stack;

public interface Calculator{
    String getType();

    int getNumberOfParameters();

    Calculation calculate(Stack<Double> arguments);
}
