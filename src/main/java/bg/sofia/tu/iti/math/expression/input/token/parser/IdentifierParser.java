package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.math.constant.EConstant;
import bg.sofia.tu.iti.math.constant.PIConstant;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;
import bg.sofia.tu.iti.math.expression.input.token.type.ConstantType;
import bg.sofia.tu.iti.math.expression.input.token.type.SeparatorType;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import bg.sofia.tu.iti.math.function.Variable;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class IdentifierParser implements TokenParser{
    private final List<Function>          functions;
    private final Map<String, Calculator> calculatorsForConstantNotation;

    public IdentifierParser(List<Function> functions){
        this.functions                 = functions;
        calculatorsForConstantNotation = new HashMap<>();
        calculatorsForConstantNotation.put(ConstantType.E.getNotation(), new EConstant());
        calculatorsForConstantNotation.put(ConstantType.PI.getNotation(), new PIConstant());
    }

    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        Token token = tokens.get(tokenIndex);
        for(Function function : functions){
            if(function.getIdentifier()
                       .contentEquals(token.getValue())){
                return doParse(function, tokenIndex, tokens);
            }
        }
        if(calculatorsForConstantNotation.containsKey(token.getValue())){
            return calculatorsForConstantNotation.get(token.getValue());
        }
        return new Variable(token.getValue());
    }

    private Calculator doParse(Function function, int tokenIndex, List<Token> tokens){
        if(function.getIdentifier()
                   .contentEquals(FunctionCalculatorType.INTEGRAL.getNotation())){
            return parseIntegral((Integral) function, tokenIndex, tokens);
        }
        return function;
    }

    private Calculator parseIntegral(Integral integral, int tokenIndex, List<Token> tokens){
        double[] intLow       = new double[]{1, 1};
        double[] intHigh      = new double[]{1, 1};
        int      currentIndex = tokenIndex;

        currentIndex++;
        expect(BracketType.OPEN_SQUARE_BRACKET.getNotation(),
               tokens.get(currentIndex)
                     .getValue());

        currentIndex++;
        if(tokens.get(currentIndex)
                 .getValue()
                 .contentEquals(OperatorNotation.MINUS.getNotation())){
            intLow[1] *= -1;
            currentIndex++;
        }
        consumeExpectedTokenType(MathElementType.NUMBER.toString(),
                                 tokens.get(currentIndex),
                                 token -> intLow[0] = Double.parseDouble(token.getValue()));

        currentIndex++;
        expect(SeparatorType.COMMA.getNotation(),
               tokens.get(currentIndex)
                     .getValue());

        currentIndex++;
        if(tokens.get(currentIndex)
                 .getValue()
                 .contentEquals(OperatorNotation.MINUS.getNotation())){
            intHigh[1] *= -1;
            currentIndex++;
        }
        consumeExpectedTokenType(MathElementType.NUMBER.toString(),
                                 tokens.get(currentIndex),
                                 token -> intHigh[0] = Double.parseDouble(token.getValue()));

        currentIndex++;
        expect(BracketType.CLOSE_SQUARE_BRACKET.getNotation(),
               tokens.get(currentIndex)
                     .getValue());

        currentIndex++;
        expect(BracketType.OPEN_BRACKET.getNotation(),
               tokens.get(currentIndex)
                     .getValue());

        currentIndex++;
        List<Token> integrand        = extractIntegrand(currentIndex, tokens);
        int         lengthOfIntegral = currentIndex + integrand.size() + 1;
        for(int i = 0; i < lengthOfIntegral - 1; i++){
            tokens.remove(tokenIndex + 1);
        }
        if(intLow[0] * intLow[1] > intHigh[0] * intHigh[1]){
            throw new RuntimeException("Invalid boundaries for Integral.");
        }
        integral.setRange(new Range(intLow[0] * intLow[1], intHigh[0] * intHigh[1]));
        integral.setTokenizedIntegrand(integrand);
        return integral;
    }

    private List<Token> extractIntegrand(int from, List<Token> tokens){
        int openBrackets = 1;
        int to           = from;
        for(; to < tokens.size() && openBrackets > 0; to++){
            Token token = tokens.get(to);
            if(token.getValue()
                    .contentEquals(BracketType.OPEN_BRACKET.getNotation())){
                openBrackets++;
            }
            else if(token.getValue()
                         .contentEquals(BracketType.CLOSE_BRACKET.getNotation())){
                openBrackets--;
            }
        }
        if(openBrackets > 0){
            throw new RuntimeException("Invalid integral: missing closing bracket in integrand");
        }
        List<Token> integrand = new ArrayList<>();
        for(int i = from; i < to - 1; i++){
            integrand.add(tokens.get(i));
        }
        return integrand;
    }

    private void consumeExpectedTokenType(String expected, Token actual, Consumer<Token> consumer){
        expect(expected, actual.getType());
        consumer.accept(actual);
    }

    private void expect(String expected, String actual){
        if(!expected.contentEquals(actual)){
            throw new RuntimeException("Invalid integral: expected \"" + expected + "\", got \"" + actual + "\"");
        }
    }
}
