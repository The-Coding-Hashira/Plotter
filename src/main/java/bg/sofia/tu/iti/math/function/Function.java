package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public abstract class Function implements Calculator{
    private final String identifier;
    private final int    numberOfParameters;

    public Function(String identifier, int numberOfParameters){
        this.identifier         = identifier;
        this.numberOfParameters = numberOfParameters;
    }

    @Override
    public String getType(){
        return OperatorType.FUNCTION.toString();
    }

    @Override
    public int getNumberOfParameters(){
        return numberOfParameters;
    }

    public String getIdentifier(){
        return identifier;
    }
}
