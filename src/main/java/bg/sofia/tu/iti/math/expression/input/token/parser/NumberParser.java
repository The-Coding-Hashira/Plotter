package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;

import java.util.List;
import java.util.Stack;

public class NumberParser implements TokenParser{
    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        return new Calculator(){
            @Override
            public String getType(){
                return MathElementType.NUMBER.toString();
            }

            @Override
            public int getNumberOfArguments(){
                return 0;
            }

            @Override
            public Calculation calculate(Stack<Double> arguments){
                return new Calculation(tokens.get(tokenIndex)
                                             .getValue(), Double.parseDouble(tokens.get(tokenIndex)
                                                                                   .getValue()));
            }
        };
    }
}