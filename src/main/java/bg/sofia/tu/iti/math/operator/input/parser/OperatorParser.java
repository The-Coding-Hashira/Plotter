package bg.sofia.tu.iti.math.operator.input.parser;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.operator.Addition;
import bg.sofia.tu.iti.math.operator.Division;
import bg.sofia.tu.iti.math.operator.EqualsOperator;
import bg.sofia.tu.iti.math.operator.Factorial;
import bg.sofia.tu.iti.math.operator.Multiplication;
import bg.sofia.tu.iti.math.operator.Power;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;

import java.util.List;

public class OperatorParser implements TokenParser{
    private final MinusParser minusParser;

    public OperatorParser(){
        minusParser = new MinusParser();
    }

    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.MINUS.getNotation())){
            return minusParser.parse(tokenIndex, tokens);
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.PLUS.getNotation())){
            return new Addition();
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.MULTIPLY.getNotation())){
            return new Multiplication();
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.DIVIDE.getNotation())){
            return new Division();
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.POWER.getNotation())){
            return new Power();
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.FACTORIAL.getNotation())){
            return new Factorial();
        }
        if(tokens.get(tokenIndex)
                 .getValue()
                 .equals(OperatorNotation.EQUALS.getNotation())){
            return new EqualsOperator();
        }
        throw new RuntimeException("Unknown operator " + tokens.get(tokenIndex)
                                                               .getValue());
    }
}
