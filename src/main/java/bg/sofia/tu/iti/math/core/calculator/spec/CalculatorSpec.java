package bg.sofia.tu.iti.math.core.calculator.spec;

public class CalculatorSpec{
    private final String                  type;
    private final Integer                 precedence;
    private final CalculatorAssociativity calculatorAssociativity;

    public CalculatorSpec(String type, Integer precedence, CalculatorAssociativity calculatorAssociativity){
        this.type                    = type;
        this.precedence              = precedence;
        this.calculatorAssociativity = calculatorAssociativity;
    }

    public int comparePostfixNotationOrderTo(CalculatorSpec calculatorSpec){
        if(calculatorSpec.getCalculationAssociativity()
                         .equals(CalculatorAssociativity.NONE)){
            return comparePrecedenceTo(calculatorSpec);
        }
        if(calculatorSpec.getCalculationAssociativity()
                         .equals(CalculatorAssociativity.LEFT) && comparePrecedenceTo(calculatorSpec) <= 0){
            return -1;
        }
        if(calculatorSpec.getCalculationAssociativity()
                         .equals(CalculatorAssociativity.RIGHT) && comparePrecedenceTo(calculatorSpec) < 0){
            return -1;
        }
        return 1;
    }

    public CalculatorAssociativity getCalculationAssociativity(){
        return calculatorAssociativity;
    }

    public int comparePrecedenceTo(CalculatorSpec calculatorSpec){
        return getPrecedence().compareTo(calculatorSpec.getPrecedence());
    }

    public Integer getPrecedence(){
        return precedence;
    }

    public String getType(){
        return type;
    }
}
