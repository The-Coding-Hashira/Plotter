package bg.sofia.tu.iti.math.operator.input.parser;

import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;
import bg.sofia.tu.iti.math.operator.Negation;
import bg.sofia.tu.iti.math.operator.Subtraction;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;

import java.util.List;

public class MinusParser implements TokenParser{
    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        if(tokenIndex == 0){
            return createNegateCalculator();
        }
        if(tokens.get(tokenIndex - 1)
                 .getType()
                 .contentEquals(MathElementType.OPERATOR.toString())){
            return createNegateCalculator();
        }
        if(tokens.get(tokenIndex - 1)
                 .getValue()
                 .contentEquals(BracketType.OPEN_BRACKET.getNotation())){
            return createNegateCalculator();
        }
        if(tokens.get(tokenIndex - 1)
                 .getType()
                 .contentEquals(MathElementType.NUMBER.toString())){
            return createSubtractionCalculator();
        }
        throw new RuntimeException("Unknown minus");
    }

    private Calculator createNegateCalculator(){
        return new Negation();
    }

    private Calculator createSubtractionCalculator(){
        return new Subtraction();
    }
}
