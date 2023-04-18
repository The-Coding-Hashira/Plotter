package bg.sofia.tu.iti.math.expression.input.token.evaluator;

import bg.sofia.tu.iti.math.core.input.evaluator.TokenEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.function.type.FunctionCalculatorType;

import java.util.List;

public class IdentifierTokenEvaluator implements TokenEvaluator{
    @Override
    public void evaluate(int tokenIndex, List<Token> tokens){
        if(tokens.get(tokenIndex)
                 .getValue()
                 .contentEquals(FunctionCalculatorType.INTEGRAL.getNotation())){
            long integrals = tokens.stream()
                                   .filter(token -> token.getValue()
                                                         .contentEquals(FunctionCalculatorType.INTEGRAL.getNotation()))
                                   .count();
            if(integrals > 1){
                throw new RuntimeException("Can only process one, standalone integral.");
            }
        }
    }
}
