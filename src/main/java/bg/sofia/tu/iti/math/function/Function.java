package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

public abstract class Function implements Calculator{
    private final String identifier;
    private       int    numberOfParameters;

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

    public void setNumberOfParameters(int numberOfParameters){
        this.numberOfParameters = numberOfParameters;
    }

    public String getIdentifier(){
        return identifier;
    }
}
