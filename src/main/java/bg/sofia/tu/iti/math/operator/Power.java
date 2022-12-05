package bg.sofia.tu.iti.math.operator;

import bg.sofia.tu.iti.math.core.calculator.BinaryMathCalculator;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public class Power extends BinaryMathCalculator{
    @Override
    public String getType(){
        return OperatorType.POWER.toString();
    }

    @Override
    public String getSymbol(){
        return OperatorNotation.POWER.getNotation();
    }

    @Override
    public double doCalculation(double leftOperand, double rightOperand){
        return Math.pow(leftOperand, rightOperand);
    }
}
