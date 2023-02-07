package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

import java.util.Stack;

public class Parameter implements Calculator{
    private final String identifier;
    private       double value;

    public Parameter(String identifier){
        this.identifier = identifier;
        value           = Double.NaN;
    }

    @Override
    public String getType(){
        return OperatorType.PARAMETER.toString();
    }

    @Override
    public int getNumberOfParameters(){
        return 0;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        return new Calculation(Double.toString(value), value);
    }

    public String getIdentifier(){
        return identifier;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }
}
