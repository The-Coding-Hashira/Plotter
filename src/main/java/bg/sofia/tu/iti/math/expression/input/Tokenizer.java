package bg.sofia.tu.iti.math.expression.input;

import bg.sofia.tu.iti.math.expression.input.token.evaluator.ExpressionTokenEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tokenizer{
    private final List<TokenType>          tokenTypes;
    private final ExpressionTokenEvaluator evaluator;

    public Tokenizer(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
        evaluator       = new ExpressionTokenEvaluator(tokenTypes);
    }

    public List<Token> tokenize(String expression){
        List<Token> tokens = tokenizeExpression(stripWhitespace(expression));
        evaluator.evaluate(tokens, expression);
        return tokens;
    }

    private String stripWhitespace(String expression){
        return expression.replaceAll("\\s", "");
    }

    private List<Token> tokenizeExpression(String expression){
        Map<Integer, Token> tokenIndexes = new HashMap<>();
        for(TokenType tokenType : tokenTypes){
            fillTokens(tokenType.getName(), tokenType.extract(expression), tokenIndexes);
        }
        return groupTokens(tokenIndexes);
    }

    private void fillTokens(String tokenType, Map<Integer, String> tokenValueIndexes, Map<Integer, Token> tokenIndexes){
        for(Integer tokenIndex : tokenValueIndexes.keySet()){
            tokenIndexes.put(tokenIndex, new Token(tokenType, tokenValueIndexes.get(tokenIndex)));
        }
    }

    private List<Token> groupTokens(Map<Integer, Token> tokenIndexes){
        List<Token> tokens = new ArrayList<>();
        while(!tokenIndexes.keySet()
                           .isEmpty()){
            Integer tokenIndex = getSmallestTokenIndex(tokenIndexes);
            tokens.add(tokenIndexes.get(tokenIndex));
            tokenIndexes.remove(tokenIndex);
        }
        return tokens;
    }

    private Integer getSmallestTokenIndex(Map<Integer, Token> tokenIndexes){
        Optional<Integer> index = tokenIndexes.keySet()
                                              .stream()
                                              .min(Integer::compare);
        if(index.isPresent()){
            return index.get();
        }
        throw new RuntimeException("Unexpected error occurred");
    }
}
