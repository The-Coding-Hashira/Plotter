package bg.sofia.tu.iti.math.expression.input;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.function.Function;

import java.util.Stack;

public class FunctionFactory{
    public Function create(String expression){
        System.out.println(expression);
        return new Function("", 2){
            public double calc(double y, double x){
                return Math.sin(x) + Math.cos(y);
            }

            @Override
            public Calculation calculate(Stack<Double> arguments){
                return new Calculation("mock up func", calc(arguments.pop(), arguments.pop()));
            }
        };
    }
}
