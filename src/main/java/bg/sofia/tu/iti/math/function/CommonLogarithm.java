package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class CommonLogarithm extends Function{
    public CommonLogarithm() {
        super(FunctionCalculatorType.COMMON_LOGARITHM.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments) {
        double operand = arguments.pop();
        return new Calculation("lg(" + operand + ")", Math.log10(operand));
    }
}
