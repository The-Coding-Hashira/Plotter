package bg.sofia.tu.iti.math.core.calculator;

import bg.sofia.tu.iti.math.core.Calculation;

import java.util.Stack;

public abstract class BinaryMathCalculator extends MathCalculator{
    @Override
    public int getNumberOfParameters(){
        return 2;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        double leftOperand  = arguments.pop();
        double rightOperand = arguments.pop();
        return new Calculation(getDescription(leftOperand, rightOperand), doCalculation(leftOperand, rightOperand));
    }

    public String getDescription(double leftOperand, double rightOperand){
        return leftOperand + " " + getSymbol() + " " + rightOperand;
    }

    protected abstract double doCalculation(double leftOperand, double rightOperand);
}
