package bg.sofia.tu.iti.math.expression.input.parser;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.expression.input.token.TokenOperations;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser{
    private final List<TokenType> tokenTypes;

    public ExpressionParser(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public List<Calculator> parse(List<Token> tokens){
        List<Calculator> calculations = new ArrayList<>();
        for(int i = 0; i < tokens.size(); i++){
            calculations.add(parseToken(i, tokens));
        }
        return calculations;
    }

    private Calculator parseToken(int tokenIndex, List<Token> tokens){
        return TokenOperations.matchTokenType(tokenTypes, tokens.get(tokenIndex))
                              .parse(tokenIndex, tokens);
    }
}
