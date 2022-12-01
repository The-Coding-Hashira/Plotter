package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class DegreesToRadiansConverter extends Function{
    public DegreesToRadiansConverter(){
        super(FunctionCalculatorType.TO_RADIANS.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        double operand = arguments.pop();
        return new Calculation("rad(" + operand + ")", Math.toRadians(operand));
    }
}
