package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class Tangent extends Function{
    public Tangent(){
        super(FunctionCalculatorType.TANGENT.getNotation(), 1);
    }
    @Override
    public Calculation calculate(Stack<Double> arguments){
        double operand = arguments.pop();
        return new Calculation("tan(" + operand + ")", Math.tan(operand));
    }
}
