package bg.sofia.tu.iti.math.expression.input.token.evaluator;

import bg.sofia.tu.iti.math.core.input.evaluator.TokenEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.TokenOperations;

import java.util.List;
import java.util.Optional;

public class NumberTokenEvaluator implements TokenEvaluator{
    @Override
    public void evaluate(int tokenIndex, List<Token> tokens){
        Optional<Token> token = evaluate(tokens.get(tokenIndex));
        token.ifPresent(value -> TokenOperations.updateToken(tokenIndex, tokens, value));
    }

    private Optional<Token> evaluate(Token token){
        Optional<Token> evaluatedMathToken = Optional.empty();
        if(token.getValue()
                .chars()
                .filter(character -> character == '.')
                .count() > 1){
            throw new RuntimeException("Invalid number: Number can have only 1 decimal point " + token.getValue());
        }
        if(token.getValue()
                .endsWith(".")){
            throw new RuntimeException("Invalid number: Number cannot end with a decimal point " + token.getValue());
        }
        if(token.getValue()
                .startsWith(".")){
            evaluatedMathToken = Optional.of(new Token(token.getType(), "0" + token.getValue()));
        }
        if(token.getValue()
                .endsWith("0") && token.getValue()
                                       .contains(".")){
            StringBuilder number = new StringBuilder(token.getValue());
            while(number.lastIndexOf("0") == number.length() - 1){
                number.deleteCharAt(number.length() - 1);
            }
            evaluatedMathToken = Optional.of(new Token(token.getType(), number.toString()));
        }
        if(token.getValue()
                .startsWith("0") && token.getValue()
                                         .charAt(1) == '0'){
            StringBuilder number = new StringBuilder(token.getValue());
            while(number.indexOf("0") == 0 && number.charAt(1) == '0'){
                number.deleteCharAt(0);
            }
            evaluatedMathToken = Optional.of(new Token(token.getType(), number.toString()));
        }
        return evaluatedMathToken;
    }
}
