package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.expression.ParameterValueSupplier;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;

import java.util.List;
import java.util.Stack;

public class CustomFunction extends Function implements Calculator{
    private final List<ParameterValueSupplier> parameterValueSuppliers;
    private final List<Calculator>             expression;

    public CustomFunction(String identifier, List<ParameterValueSupplier> parameterValueSuppliers,
                          List<Calculator> expression){
        super(identifier, parameterValueSuppliers.size());
        this.parameterValueSuppliers = parameterValueSuppliers;
        this.expression              = expression;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        for(int i = parameterValueSuppliers.size() - 1; i >= 0; i--){
            parameterValueSuppliers.get(i)
                                   .emitValue(arguments.pop());
        }
        ExpressionResult result = new ExpressionInterpreter().interpretCalculators(expression);
        return new Calculation(result.getFullDescription(), result.get());
    }
}
