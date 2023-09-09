package bg.sofia.tu.iti.math.expression.input;

import bg.sofia.tu.iti.math.context.MathContext;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.input.parser.AnonymousFunctionExpressionParser;
import bg.sofia.tu.iti.math.function.Function;

public class FunctionFactory{

    public Function create(String expression){
        MathContext mathContext = new MathContextFactory().createMathContext();
        return new AnonymousFunctionExpressionParser(mathContext.getTokenTypes(),
                                                     mathContext.getCalculatorSpecs()).parse(expression);
    }
}
