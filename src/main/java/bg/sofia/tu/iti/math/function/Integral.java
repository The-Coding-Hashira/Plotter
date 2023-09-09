package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.VariableValueSupplier;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Integral extends Function{
    private final VariableValueSupplier variableValueSupplier;
    private final Variable              variable;
    private final double                steps;
    private       Range                 range;
    private       List<Token>           tokenizedIntegrand;
    private       Function              integrand;

    public Integral(){
        super(FunctionCalculatorType.INTEGRAL.getNotation(), 0);
        variable              = new Variable("x");
        variableValueSupplier = new VariableValueSupplier(Collections.singletonList(variable));
        steps                 = 1001;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        double argument = variable.getValue();
        if(argument > range.getLowBoundary() && argument < range.getHighBoundary()){
            arguments.push(argument);
            return integrand.calculate(arguments);
        }
        return new Calculation("", Double.NaN);
    }

    public ExpressionResult integrate(){
        if(steps % 2 == 0){
            throw new RuntimeException("The number of integration steps must be odd!");
        }
        double          rangeLow        = range.getLowBoundary();
        double          rangeHigh       = range.getHighBoundary();
        double          rangeValue      = range.calculate();
        double          dx              = rangeValue / (steps - 1);
        double          dx2             = 2 * dx;
        IntegrandHelper integrandHelper = new IntegrandHelper();
        double          result          = integrandHelper.valueOf(rangeLow) + integrandHelper.valueOf(rangeHigh);
        for(double x = rangeLow + dx; x < rangeHigh; x += dx2){
            result += 4 * integrandHelper.valueOf(x);
        }
        for(double x = rangeLow + dx2; x < rangeHigh; x += dx2){
            result += 2 * integrandHelper.valueOf(x);
        }
        String description = getIdentifier() + "[" + rangeLow + "," + rangeHigh + "](" + generateIntegrandDefinition(
                tokenizedIntegrand) + ")";
        result *= dx / 3;
        return new ExpressionResult(description, Collections.singletonList(new Calculation(description, result)), result);
    }

    private String generateIntegrandDefinition(List<Token> integrand){
        StringBuilder definition = new StringBuilder();
        integrand.stream()
                 .map(Token::getValue)
                 .forEach(definition::append);
        return definition.toString();
    }

    public Range getRange(){
        return range;
    }

    public void setRange(Range range){
        this.range = range;
    }

    public VariableValueSupplier getVariableValueSupplier(){
        return variableValueSupplier;
    }

    public void setTokenizedIntegrand(List<Token> tokenizedIntegrand){
        this.tokenizedIntegrand = tokenizedIntegrand;
    }

    public List<Token> getTokenizedIntegrand(){
        return tokenizedIntegrand;
    }

    public void setIntegrand(Function integrand){
        this.integrand = integrand;
    }

    private class IntegrandHelper{
        private final Stack<Double> arguments;

        public IntegrandHelper(){
            this.arguments = new Stack<>();
        }

        private double valueOf(double value){
            arguments.push(value);
            return integrand.calculate(arguments)
                            .getResult();
        }
    }
}
