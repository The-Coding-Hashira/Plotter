package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.core.calculator.BinaryMathCalculator;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Addition extends BinaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.ADDITION.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.PLUS.getNotation();
    }

    @Override
    public double doCalculation(double leftOperand, double rightOperand){
        return leftOperand + rightOperand;
    }
}
