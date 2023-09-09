package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.expression.VariableValueSupplier;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;

import java.util.List;
import java.util.Stack;

public class AnonymousFunction extends Function{
    private final List<VariableValueSupplier> variableValueSuppliers;
    private final List<Calculator> expression;
    private final String           expressionDefinition;

    public AnonymousFunction(List<VariableValueSupplier> variableValueSuppliers, List<Calculator> expression,
                             String expressionDefinition){
        super(AnonymousFunction.class.getSimpleName(), variableValueSuppliers.size());
        this.variableValueSuppliers = variableValueSuppliers;
        this.expression             = expression;
        this.expressionDefinition   = expressionDefinition;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        for(int i = variableValueSuppliers.size() - 1; i >= 0; i--){
            variableValueSuppliers.get(i)
                                  .supply(arguments.pop());
        }
        ExpressionResult result = new ExpressionInterpreter().interpretCompiledCalculators(expressionDefinition,
                                                                                           expression);
        return new Calculation(result.getFullDescription(), result.get());
    }

    public List<Calculator> getExpression(){
        return expression;
    }
}
