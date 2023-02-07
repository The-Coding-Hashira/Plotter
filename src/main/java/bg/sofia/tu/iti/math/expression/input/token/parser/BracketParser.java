package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;

import java.util.List;
import java.util.Stack;

public class BracketParser implements TokenParser{
    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(BracketType.OPEN_BRACKET.getNotation())){
            return createBracket(BracketType.OPEN_BRACKET.toString());
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(BracketType.CLOSE_BRACKET.getNotation())){
            return createBracket(BracketType.CLOSE_BRACKET.toString());
        }
        throw new RuntimeException("Unknown bracket");
    }

    private Calculator createBracket(String type){
        return new Calculator(){
            @Override
            public String getType(){
                return type;
            }

            @Override
            public int getNumberOfParameters(){
                return 0;
            }

            @Override
            public Calculation calculate(Stack<Double> arguments){
                return new Calculation(getType(), 1.0);
            }
        };
    }
}
