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
        return "Expression: " + expression + '\n' + "Result: " + result + "\n\n" + "Calculation " + "Steps:" + '\n' + getCalculationStepsDescription();
    }

    public double get(){
        return result;
    }

    public String getCalculationStepsDescription(){
        StringBuilder calculationSteps = new StringBuilder();
        for(Calculation calculation : calculations){
            if(calculation.getDescription().contentEquals(String.valueOf(calculation.getResult()))){
                continue;
            }
            if(calculation.getDescription().contentEquals("")){
                continue;
            }
            calculationSteps.append('\t');
            calculationSteps.append(calculation.getDescription())
                            .append(" = ")
                            .append(calculation.getResult());
            calculationSteps.append('\n');
        }
        return calculationSteps.toString();
    }
}
