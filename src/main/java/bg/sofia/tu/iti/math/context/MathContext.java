package bg.sofia.tu.iti.math.context;

import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorSpec;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.function.Function;

import java.util.List;

public class MathContext{
    private final List<TokenType>      tokenTypes;
    private final List<CalculatorSpec> calculatorSpecs;
    private final List<Function>       functions;

    public MathContext(List<TokenType> tokenTypes, List<CalculatorSpec> calculatorSpecs, List<Function> functions){
        this.tokenTypes      = tokenTypes;
        this.calculatorSpecs = calculatorSpecs;
        this.functions       = functions;
    }

    public List<TokenType> getTokenTypes(){
        return tokenTypes;
    }

    public List<CalculatorSpec> getCalculatorSpecs(){
        return calculatorSpecs;
    }

    public List<Function> getFunctions(){
        return functions;
    }
}
