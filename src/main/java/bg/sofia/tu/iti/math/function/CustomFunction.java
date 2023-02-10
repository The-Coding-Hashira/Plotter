package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.expression.VariableValueSupplier;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;

import java.util.List;
import java.util.Stack;

public class CustomFunction extends Function implements Calculator{
    private final List<VariableValueSupplier> variableValueSuppliers;
    private final List<Calculator>            expression;

    public CustomFunction(String identifier, List<VariableValueSupplier> variableValueSuppliers,
                          List<Calculator> expression){
        super(identifier, variableValueSuppliers.size());
        this.variableValueSuppliers = variableValueSuppliers;
        this.expression             = expression;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        for(int i = variableValueSuppliers.size() - 1; i >= 0; i--){
            variableValueSuppliers.get(i)
                                  .supply(arguments.pop());
        }
        //TODO this guy has not precompiled its calculators so it explodes
        ExpressionResult result = new ExpressionInterpreter().interpretCompiledCalculators(expression);
        return new Calculation(result.getFullDescription(), result.get());
    }
}
