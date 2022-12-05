package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

import java.util.Stack;

public class EqualsOperator implements Calculator{
    @Override
    public String getType(){
        return OperatorType.EQUALS.toString();
    }

    @Override
    public int getNumberOfArguments(){
        return 0;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        return new Calculation(OperatorNotation.EQUALS.getNotation(), 1);
    }
}
