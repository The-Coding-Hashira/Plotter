package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class Sine extends Function{
    public Sine(){
        super(FunctionCalculatorType.SINE.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        Number operand = arguments.pop();
        return new Calculation("sin(" + operand + ")", Math.sin(operand.doubleValue()));
    }
}
