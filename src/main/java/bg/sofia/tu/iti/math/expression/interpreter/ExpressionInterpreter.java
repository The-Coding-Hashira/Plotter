package bg.sofia.tu.iti.math.expression.interpreter;

import bg.sofia.tu.iti.math.context.MathContext;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorSpec;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.compiler.InfixToPostfixNotationExpressionCompiler;
import bg.sofia.tu.iti.math.expression.input.Tokenizer;
import bg.sofia.tu.iti.math.expression.input.parser.ExpressionParser;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;

import java.util.List;

public class ExpressionInterpreter{
    private final Tokenizer                                tokenizer;
    private final ExpressionParser                         expressionParser;
    private final InfixToPostfixNotationExpressionCompiler infixToPostfixCompiler;
    private final PostfixNotationExpressionInterpreter     postfixExpressionInterpreter;

    public ExpressionInterpreter(){
        this(new MathContextFactory().createMathContext());
    }

    public ExpressionInterpreter(MathContext mathContext){
        this(mathContext.getTokenTypes(), mathContext.getCalculatorSpecs());
    }

    public ExpressionInterpreter(List<TokenType> tokenTypes, List<CalculatorSpec> calculatorSpecs){
        tokenizer                    = new Tokenizer(tokenTypes);
        expressionParser             = new ExpressionParser(tokenTypes);
        infixToPostfixCompiler       = new InfixToPostfixNotationExpressionCompiler(calculatorSpecs);
        postfixExpressionInterpreter = new PostfixNotationExpressionInterpreter();
    }

    public ExpressionResult interpret(String expression){
        return interpret(expression, tokenizer.tokenize(expression));
    }

    private ExpressionResult interpret(String expression, List<Token> tokenizedExpression){
        return buildExpressionResult(expression,
                                     postfixExpressionInterpreter.interpret(infixToPostfixCompiler.compile(
                                             expressionParser.parse(tokenizedExpression))));
    }

    private ExpressionResult buildExpressionResult(String expression, List<Calculation> calculations){
        return new ExpressionResult(expression,
                                    calculations,
                                    calculations.get(calculations.size() - 1)
                                                .getResult());
    }

    public ExpressionResult interpret(List<Token> expression){
        return interpret(buildExpressionDescription(expression), expression);
    }

    private String buildExpressionDescription(List<Token> expression){
        StringBuilder description = new StringBuilder();
        for(Token token : expression){
            description.append(token.getValue());
        }
        return description.toString();
    }

    public ExpressionResult interpretCompiledCalculators(String expression, List<Calculator> calculators){
        return buildExpressionResult(expression, postfixExpressionInterpreter.interpret(calculators));
    }
}
