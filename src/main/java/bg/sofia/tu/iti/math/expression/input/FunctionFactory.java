package bg.sofia.tu.iti.math.expression.input;

import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;
import bg.sofia.tu.iti.math.function.Cosine;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Sine;

import java.util.Stack;

public class FunctionFactory{

    public Function create(String expression){
        return new FunctionDefinitionParser(new MathContextFactory().createMathContext()
                                                                    .getTokenTypes()).parse(expression);
        //        return new Function("", 2){
        //            private final Sine sin = new Sine();
        //
        //            private final Cosine cos = new Cosine();
        //
        //            double calc(double y, double x){
        //                return Math.sin(x) + Math.cos(y);
        //            }
        //
        //            @Override
        //            public Calculation calculate(Stack<Double> arguments){
        //                return new Calculation("mock up func",
        //                                       cos.calculate(arguments)
        //                                          .getResult() + sin.calculate(arguments)
        //                                                            .getResult());
        //            }
        //        };
    }
}
