package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;

import java.util.List;

public class IdentifierParser implements TokenParser{
    private final List<Function> functions;

    public IdentifierParser(List<Function> functions){
        this.functions = functions;
    }

    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        for(Function function : functions){
            if(function.getIdentifier()
                       .contentEquals(tokens.get(tokenIndex)
                                            .getValue())){
                return function;
            }
        }
        throw new RuntimeException("Unknown Identifier:" + tokens.get(tokenIndex)
                                                                 .getValue());
    }
}
