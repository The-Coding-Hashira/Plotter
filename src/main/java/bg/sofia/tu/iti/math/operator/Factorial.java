package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.core.calculator.UnaryMathCalculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Factorial extends UnaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.FACTORIAL.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.FACTORIAL.getNotation();
    }

    @Override
    public double doCalculation(double operand){
        double result = 1;
        for(int i = 1; i <= operand; i++){
            result = result * i;
        }
        return result;
    }
}
