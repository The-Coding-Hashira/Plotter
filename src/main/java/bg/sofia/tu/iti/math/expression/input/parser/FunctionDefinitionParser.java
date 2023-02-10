package bg.sofia.tu.iti.math.expression.input.parser;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.evaluator.TokenizationIntegrityEvaluator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.VariableValueSupplier;
import bg.sofia.tu.iti.math.expression.input.Tokenizer;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;
import bg.sofia.tu.iti.math.function.CustomFunction;
import bg.sofia.tu.iti.math.function.Variable;
import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionDefinitionParser{
    private final List<TokenType> tokenTypes;

    public FunctionDefinitionParser(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public CustomFunction parse(String definition){
        List<Token> tokens = new Tokenizer(tokenTypes).tokenize(definition);
        new TokenizationIntegrityEvaluator().evaluate(tokens, definition);
        if(definition.contains(OperatorNotation.EQUALS.getNotation())){
            String                      identifier             = extractIdentifier(tokens);
            List<String>                parameterIdentifiers   = extractParameterIdentifiers(tokens);
            List<Calculator>            expression             = extractExpression(tokens);
            List<VariableValueSupplier> variableValueSuppliers = setUpParameters(parameterIdentifiers, expression);
            return new CustomFunction(identifier, variableValueSuppliers, expression);
        }
        else{
            List<String>                parameterIdentifiers   = Arrays.asList("x", "y");
            List<Calculator>            expression             = new ExpressionParser(tokenTypes).parse(tokens);
            List<VariableValueSupplier> variableValueSuppliers = setUpParameters(parameterIdentifiers, expression);
            return new CustomFunction("", variableValueSuppliers, expression);
        }
    }

    private String extractIdentifier(List<Token> tokens){
        if(tokens.get(0)
                 .getType()
                 .contentEquals(MathElementType.IDENTIFIER.toString())){
            return tokens.get(0)
                         .getValue();
        }
        throw new RuntimeException("Expected function identifier, not " + tokens.get(0)
                                                                                .getType());
    }

    private List<String> extractParameterIdentifiers(List<Token> tokens){
        List<String> parameters = new ArrayList<>();
        if(!tokens.get(1)
                  .getValue()
                  .contentEquals(BracketType.OPEN_BRACKET.getNotation())){
            throw new RuntimeException("Invalid function definition, expected open bracket");
        }
        for(int i = 2; i < tokens.size(); i++){
            if(tokens.get(i)
                     .getValue()
                     .contentEquals(BracketType.CLOSE_BRACKET.getNotation())){
                break;
            }
            if(tokens.get(i)
                     .getType()
                     .contentEquals(MathElementType.IDENTIFIER.toString())){
                parameters.add(tokens.get(i)
                                     .getValue());
                continue;
            }
            if(tokens.get(i)
                     .getType()
                     .contentEquals(MathElementType.NUMBER.toString())){
                throw new RuntimeException("Cannot have a number as a function parameter in the function definition");
            }
            if(!tokens.get(i)
                      .getType()
                      .contentEquals(MathElementType.SEPARATOR.toString())){
                throw new RuntimeException("Invalid function definition, expected parameter, got separator");
            }
        }
        return parameters;
    }

    private List<Calculator> extractExpression(List<Token> tokens){
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i)
                     .getType()
                     .contentEquals(MathElementType.OPERATOR.toString())){
                if(tokens.get(i)
                         .getValue()
                         .contentEquals(OperatorNotation.EQUALS.getNotation())){
                    return new ExpressionParser(tokenTypes).parse(tokens.subList(i + 1, tokens.size()));
                }
            }
        }
        throw new RuntimeException("Invalid function definition");
    }

    private List<VariableValueSupplier> setUpParameters(List<String> parameterIdentifiers, List<Calculator> expression){
        List<VariableValueSupplier> variableValueSuppliers = new ArrayList<>(parameterIdentifiers.size());
        List<Calculator> parameters = expression.stream()
                                                .filter(calculator -> calculator.getType()
                                                                                .contentEquals(OperatorType.VARIABLE.toString()))
                                                .collect(Collectors.toList());
        parameterIdentifiers.forEach(identifier -> variableValueSuppliers.add(new VariableValueSupplier(parameters.stream()
                                                                                                                  .filter(calculator -> ((Variable) calculator).getIdentifier()
                                                                                                                                                               .contentEquals(
                                                                                                                                                                       identifier))
                                                                                                                  .map(calculator -> (Variable) calculator)
                                                                                                                  .collect(
                                                                                                                          Collectors.toList()))));

        return variableValueSuppliers;
    }
}
