package bg.sofia.tu.iti.math.function.formula;

import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;

import java.util.Map;

public class FunctionFormula{
    private final String formula;

    public FunctionFormula(String formula){
        this.formula = formula;
    }

    public Function buildFunctionForParameters(Map<String, Number> parameters){
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
        return new FunctionDefinitionParser(new MathContextFactory().createMathContext()
                                                                    .getTokenTypes()).parse(functionDefinition.toString());
    }
}
