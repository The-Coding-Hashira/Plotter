package bg.sofia.tu.iti.math.expression.result;

import bg.sofia.tu.iti.math.core.Calculation;

import java.util.List;

public class ExpressionResult{
    private final String            expression;
    private final List<Calculation> calculations;
    private final double            result;

    public ExpressionResult(String expression, List<Calculation> calculations, double result){
        this.expression   = expression;
        this.calculations = calculations;
        this.result       = result;
    }

    public String getFullDescription(){
        return "Expression: " + getExpression() + '\n' + "Result: " + get() + '\n' + "Calculation " + "Steps:" + '\n' + getCalculationStepsDescription();
    }

    public String getCalculationStepsDescription(){
        StringBuilder calculationSteps = new StringBuilder();
        for(Calculation calculation : getCalculations()){
            calculationSteps.append(calculation.getDescription())
                            .append(" = ")
                            .append(calculation.getResult());
            calculationSteps.append('\n');
        }
        return calculationSteps.toString();
    }

    public String getExpression(){
        return expression;
    }

    public List<Calculation> getCalculations(){
        return calculations;
    }

    public double get(){
        return result;
    }
}
