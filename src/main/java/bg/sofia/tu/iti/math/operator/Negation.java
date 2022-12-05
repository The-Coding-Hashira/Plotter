package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.core.calculator.UnaryMathCalculator;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Negation extends UnaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.NEGATION.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.MINUS.getNotation();
    }

    @Override
    public double doCalculation(double operand){
        return operand * -1;
    }
}
