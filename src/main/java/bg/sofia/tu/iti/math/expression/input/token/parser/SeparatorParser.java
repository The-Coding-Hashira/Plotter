package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.expression.input.token.type.SeparatorType;

import java.util.List;
import java.util.Stack;

public class SeparatorParser implements TokenParser{
    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        return new Calculator(){
            @Override
            public String getType(){
                return SeparatorType.COMMA.toString();
            }

            @Override
            public int getNumberOfArguments(){
                return 0;
            }

            //TODO return Double.NaN in such situations
            @Override
            public Calculation calculate(Stack<Double> arguments){
                return new Calculation(getType(), 1.0);
            }
        };
    }
}
