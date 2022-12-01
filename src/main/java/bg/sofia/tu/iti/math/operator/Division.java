package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.core.calculator.BinaryMathCalculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Division extends BinaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.DIVISION.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.DIVIDE.getNotation();
    }

    @Override
    public double doCalculation(double leftOperand, double rightOperand){
        return leftOperand / rightOperand;
    }
}
