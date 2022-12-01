package bg.sofia.tu.iti.math.core.input.evaluator;

import bg.sofia.tu.iti.math.core.input.token.Token;

import java.util.List;

public interface TokenEvaluator{
    void evaluate(int tokenIndex, List<Token> tokens);
}
