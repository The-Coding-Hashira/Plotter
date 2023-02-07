package bg.sofia.tu.iti.math.function.formula;

import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;

import java.util.List;
import java.util.Map;

public class FunctionFormula{
    private final String formula;

    public FunctionFormula(String formula){
        this.formula = formula;
    }

    public Function buildFunctionForParameters(Map<String, Number> parameters, List<TokenType> tokenTypes){
        StringBuilder functionDefinition = new StringBuilder(formula);
        int           equalsSignIndex    = functionDefinition.indexOf(OperatorNotation.EQUALS.getNotation());
        for(String parameter : parameters.keySet()){
            int indexOfParameter = functionDefinition.indexOf(parameter);
            if(indexOfParameter == -1){
                continue;
            }
            if(indexOfParameter <= equalsSignIndex){
                throw new RuntimeException("No such parameter in formula " + parameter);
            }
            functionDefinition.replace(indexOfParameter, indexOfParameter + parameter.length(),
                                       parameters.get(parameter)
                                                                                                          .toString());
        }
        return new FunctionDefinitionParser(tokenTypes).parse(functionDefinition.toString());
    }
}
