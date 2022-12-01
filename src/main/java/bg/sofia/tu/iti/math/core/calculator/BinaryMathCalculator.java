package bg.sofia.tu.iti.math.core.calculator;

import bg.sofia.tu.iti.math.core.Calculation;

import java.util.Stack;

public abstract class BinaryMathCalculator extends MathCalculator{
    @Override
    public int getNumberOfArguments(){
        return 2;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        //TODO test this operand thingy if it matches the input, maybe push x push y, and swap the pops for right
        // operand first pop
        double leftOperand  = arguments.pop();
        double rightOperand = arguments.pop();
        return new Calculation(getDescription(leftOperand, rightOperand), doCalculation(leftOperand, rightOperand));
    }

    public String getDescription(double leftOperand, double rightOperand){
        return leftOperand + getSymbol() + rightOperand;
    }

    protected abstract double doCalculation(double leftOperand, double rightOperand);
}
