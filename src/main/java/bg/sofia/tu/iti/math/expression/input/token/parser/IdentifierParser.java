package bg.sofia.tu.iti.math.expression.input.token.parser;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.token.TokenParser;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Variable;

import java.util.List;

public class IdentifierParser implements TokenParser{
    private final List<Function> functions;

    public IdentifierParser(List<Function> functions){
        this.functions = functions;
    }

    @Override
    public Calculator parse(int tokenIndex, List<Token> tokens){
        Token token = tokens.get(tokenIndex);
        for(Function function : functions){
            if(function.getIdentifier()
                       .contentEquals(token.getValue())){
                return function;
            }
        }
        return new Variable(token.getValue());
        //        throw new RuntimeException("Unknown Identifier:" + tokens.get(tokenIndex)
        //                                                                   .getValue());
    }
}
