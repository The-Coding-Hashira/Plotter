package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.core.calculator.BinaryMathCalculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Multiplication extends BinaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.MULTIPLICATION.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.MULTIPLY.getNotation();
    }

    @Override
    public double doCalculation(double leftOperand, double rightOperand){
        return leftOperand * rightOperand;
    }
}
