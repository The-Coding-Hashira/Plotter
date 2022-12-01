package bg.sofia.tu.iti.math.core.calculator;

import bg.sofia.tu.iti.math.core.Calculation;

import java.util.Stack;

public abstract class UnaryMathCalculator extends MathCalculator{
    @Override
    public int getNumberOfArguments(){
        return 1;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        double operand = arguments.pop();
        return new Calculation(getDescription(operand), doCalculation(operand));
    }

    public String getDescription(double operand){
        return getSymbol() + operand;
    }

    public abstract double doCalculation(double operand);
}
