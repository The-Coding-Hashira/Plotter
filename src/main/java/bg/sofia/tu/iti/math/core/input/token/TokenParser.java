package bg.sofia.tu.iti.math.core.input.token;

import bg.sofia.tu.iti.math.core.calculator.Calculator;

import java.util.List;

public interface TokenParser{
    Calculator parse(int tokenIndex, List<Token> tokens);
}
