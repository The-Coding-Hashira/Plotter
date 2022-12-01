package bg.sofia.tu.iti.math.expression.input.token.evaluator;

import bg.sofia.tu.iti.math.core.input.evaluator.TokenizationIntegrityEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.expression.input.token.TokenOperations;

import java.util.List;

public class ExpressionTokenEvaluator{
    private final List<TokenType> tokenTypes;

    public ExpressionTokenEvaluator(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public void evaluate(List<Token> tokens, String expression){
        new TokenizationIntegrityEvaluator().evaluate(tokens, expression);
        for(int i = 0; i < tokens.size(); i++){
            TokenOperations.matchTokenType(tokenTypes, tokens.get(i))
                           .evaluate(i, tokens);
        }
    }
}
