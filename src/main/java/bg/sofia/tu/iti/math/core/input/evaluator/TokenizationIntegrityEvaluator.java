package bg.sofia.tu.iti.math.core.input.evaluator;

import bg.sofia.tu.iti.math.core.input.token.Token;

import java.util.List;

public class TokenizationIntegrityEvaluator{
    public void evaluate(List<Token> tokens, String expression){
        int tokenizedExpressionLength = 0;
        for(Token token : tokens){
            tokenizedExpressionLength += token.getValue()
                                              .length();
        }
        if(tokenizedExpressionLength != expression.length()){
            String unknownToken = findUnknownToken(tokens, expression);
            throw new RuntimeException("Unknown token [" + unknownToken + "] in expression [" + expression + "]");
        }
    }

    private String findUnknownToken(List<Token> tokens, String expression){
        int       cursor = 0;
        Character unknownCharacter;
        for(Token token : tokens){
            unknownCharacter = lookForUnknownToken(token.getValue(), expression, cursor);
            if(unknownCharacter != null){
                return unknownCharacter.toString();
            }
            cursor += token.getValue()
                           .length();
        }
        throw new RuntimeException("Unknown token was detected but could not be found" + expression);
    }

    private Character lookForUnknownToken(String tokenValue, String expression, int cursor){
        for(Character character : tokenValue.toCharArray()){
            if(character != expression.charAt(cursor)){
                return expression.charAt(cursor);
            }
            cursor++;
        }
        return null;
    }
}
