package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Stack;

public class Signum extends Function{
    public Signum() {
        super(FunctionCalculatorType.SIGNUM.getNotation(), 1);
    }

    @Override
    public Calculation calculate(Stack<Double> arguments) {
        double operand = arguments.pop();
        return new Calculation("sign(" + operand + ")", Math.signum(operand));
    }
}
