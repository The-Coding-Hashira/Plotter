package bg.sofia.tu.iti.math.constant;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;

import java.util.Stack;

public class PIConstant implements Calculator{
    private static final Calculation result = new Calculation("pi", Math.PI);
    @Override
    public String getType(){
        return MathElementType.NUMBER.toString();
    }

    @Override
    public int getNumberOfParameters(){
        return 0;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        return result;
    }
}
