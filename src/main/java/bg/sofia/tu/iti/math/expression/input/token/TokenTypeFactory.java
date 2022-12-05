package bg.sofia.tu.iti.math.expression.input.token;

import bg.sofia.tu.iti.math.core.input.token.extractor.RegexTokenValueExtractor;
import bg.sofia.tu.iti.math.expression.input.extractor.BracketTokenValueExtractor;
import bg.sofia.tu.iti.math.expression.input.token.evaluator.DummyTokenEvaluator;
import bg.sofia.tu.iti.math.expression.input.token.evaluator.NumberTokenEvaluator;
import bg.sofia.tu.iti.math.expression.input.token.parser.BracketParser;
import bg.sofia.tu.iti.math.expression.input.token.parser.IdentifierParser;
import bg.sofia.tu.iti.math.expression.input.token.parser.NumberParser;
import bg.sofia.tu.iti.math.expression.input.token.parser.SeparatorParser;
import bg.sofia.tu.iti.math.expression.input.token.type.SeparatorType;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.operator.input.extractor.OperatorTokenValueExtractor;
import bg.sofia.tu.iti.math.operator.input.parser.OperatorParser;

import java.util.ArrayList;
import java.util.List;

public class TokenTypeFactory{
    public List<TokenType> createTokenTypes(List<Function> functions){
        List<TokenType> tokenTypes = new ArrayList<>();
        tokenTypes.add(createBracketTokenType());
        tokenTypes.add(createSeparatorTokenType());
        tokenTypes.add(createNumberTokenType());
        tokenTypes.add(createOperatorTokenType());
        tokenTypes.add(createIdentifierTokenType(functions));
        return tokenTypes;
    }

    private TokenType createBracketTokenType(){
        return new TokenType.Builder().withType(MathElementType.BRACKET)
                                      .withExtractor(new BracketTokenValueExtractor())
                                      .withEvaluator(new DummyTokenEvaluator())
                                      .withParser(new BracketParser())
                                      .build();
    }

    private TokenType createSeparatorTokenType(){
        return new TokenType.Builder().withType(MathElementType.SEPARATOR)
                                      .withExtractor(new RegexTokenValueExtractor(SeparatorType.COMMA.getNotation()))
                                      .withEvaluator(new DummyTokenEvaluator())
                                      .withParser(new SeparatorParser())
                                      .build();
    }

    private TokenType createNumberTokenType(){
        return new TokenType.Builder().withType(MathElementType.NUMBER)
                                      .withExtractor(new RegexTokenValueExtractor("[\\d.]+"))
                                      .withEvaluator(new NumberTokenEvaluator())
                                      .withParser(new NumberParser())
                                      .build();
    }

    private TokenType createOperatorTokenType(){
        return new TokenType.Builder().withType(MathElementType.OPERATOR)
                                      .withExtractor(new OperatorTokenValueExtractor())
                                      .withEvaluator(new DummyTokenEvaluator())
                                      .withParser(new OperatorParser())
                                      .build();
    }

    private TokenType createIdentifierTokenType(List<Function> functions){
        return new TokenType.Builder().withType(MathElementType.IDENTIFIER)
                                      .withExtractor(new RegexTokenValueExtractor("[A-Za-z]+"))
                                      .withEvaluator(new DummyTokenEvaluator())
                                      .withParser(new IdentifierParser(functions))
                                      .build();
    }
}
