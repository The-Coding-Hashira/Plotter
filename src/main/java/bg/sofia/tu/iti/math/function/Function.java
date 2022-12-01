package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.operator.type.OperatorType;
import bg.sofia.tu.iti.math.core.calculator.Calculator;

public abstract class Function implements Calculator{
    private final String identifier;
    private final int    numberOfArguments;

    public Function(String identifier, int numberOfArguments){
        this.identifier        = identifier;
        this.numberOfArguments = numberOfArguments;
    }

    @Override
    public String getType(){
        return OperatorType.FUNCTION.toString();
    }

    @Override
    public int getNumberOfArguments(){
        return numberOfArguments;
    }

    public String getIdentifier(){
        return identifier;
    }
}
