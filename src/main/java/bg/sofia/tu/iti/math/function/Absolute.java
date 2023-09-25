package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class Absolute extends Function{
    public Absolute(){
        super(FunctionCalculatorType.ABSOLUTE.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        double operand = arguments.pop();
        return new Calculation("abs(" + operand + ")", Math.abs(operand));
    }
}
