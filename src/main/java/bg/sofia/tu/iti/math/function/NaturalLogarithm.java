package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class NaturalLogarithm extends Function{
    public NaturalLogarithm() {
        super(FunctionCalculatorType.NATURAL_LOGARITHM.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments) {
        double operand = arguments.pop();
        return new Calculation("ln(" + operand + ")", Math.log(operand));
    }
}
