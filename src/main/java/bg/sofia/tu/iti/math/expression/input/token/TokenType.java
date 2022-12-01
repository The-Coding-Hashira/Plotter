package bg.sofia.tu.iti.math.expression.input.token;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.evaluator.TokenEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.core.input.token.extractor.TokenValueExtractor;

import java.util.List;
import java.util.Map;

public class TokenType{
    //TODO consider making this an enum
    private final MathElementType     type;
    private final TokenValueExtractor extractor;
    private final TokenEvaluator      evaluator;
    private final TokenParser         parser;

    public TokenType(Builder builder){
        type      = builder.type;
        extractor = builder.extractor;
        evaluator = builder.evaluator;
        parser    = builder.parser;
    }

    public String getName(){
        return type.toString();
    }

    public Map<Integer, String> extract(String expression){
        return extractor.extract(expression);
    }

    public void evaluate(int tokenIndex, List<Token> tokens){
        evaluator.evaluate(tokenIndex, tokens);
    }

    public Calculator parse(int tokenIndex, List<Token> tokens){
        return parser.parse(tokenIndex, tokens);
    }

    public static class Builder{
        private MathElementType     type;
        private TokenValueExtractor extractor;
        private TokenEvaluator      evaluator;
        private TokenParser         parser;

        public Builder withType(MathElementType type){
            this.type = type;
            return this;
        }

        public Builder withExtractor(TokenValueExtractor extractor){
            this.extractor = extractor;
            return this;
        }

        public Builder withEvaluator(TokenEvaluator evaluator){
            this.evaluator = evaluator;
            return this;
        }

        public Builder withParser(TokenParser parser){
            this.parser = parser;
            return this;
        }

        public TokenType build(){
            return new TokenType(this);
        }
    }
}
