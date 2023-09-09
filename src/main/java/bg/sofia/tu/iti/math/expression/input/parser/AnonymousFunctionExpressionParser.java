package bg.sofia.tu.iti.math.expression.input.parser;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorSpec;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.VariableValueSupplier;
import bg.sofia.tu.iti.math.expression.compiler.InfixToPostfixNotationExpressionCompiler;
import bg.sofia.tu.iti.math.expression.input.Tokenizer;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import bg.sofia.tu.iti.math.function.Variable;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnonymousFunctionExpressionParser{
    private final List<TokenType>      tokenTypes;
    private final List<CalculatorSpec> calculatorSpecs;

    public AnonymousFunctionExpressionParser(List<TokenType> tokenTypes, List<CalculatorSpec> calculatorSpecs){
        this.tokenTypes      = tokenTypes;
        this.calculatorSpecs = calculatorSpecs;
    }

    public AnonymousFunction parse(String expression){
        List<Calculator>            calculators            = process(expression);
        List<VariableValueSupplier> variableValueSuppliers = extractVariableValueSuppliers(calculators);
        if(calculators.size() == 1 && calculators.get(0) instanceof Integral){
            Integral integral = ((Integral) calculators.get(0));
            integral.setIntegrand(parseIntegrand(integral.getTokenizedIntegrand()));
        }
        return new AnonymousFunction(variableValueSuppliers, calculators, expression);
    }

    private Function parseIntegrand(List<Token> tokenizedIntegrand){
        List<Calculator> calculators = compileExpression(new ExpressionParser(tokenTypes).parse(tokenizedIntegrand));
        List<VariableValueSupplier> variableValueSuppliers = extractVariableValueSuppliers(calculators);
        return new AnonymousFunction(variableValueSuppliers,
                                     calculators,
                                     generateIntegrandDefinition(tokenizedIntegrand));
    }

    private String generateIntegrandDefinition(List<Token> integrand){
        StringBuilder definition = new StringBuilder();
        integrand.stream().map(Token::getValue).forEach(definition::append);
        return definition.toString();
    }

    private List<Calculator> process(String expression){
        return compileExpression(parseExpression(expression));
    }

    private List<VariableValueSupplier> extractVariableValueSuppliers(List<Calculator> calculators){
        List<Variable>              variables              = extractVariables(calculators);
        List<VariableValueSupplier> variableValueSuppliers = new ArrayList<>();
        tryToExtractVariableValueSupplier("x", variables, variableValueSuppliers);
        tryToExtractVariableValueSupplier("y", variables, variableValueSuppliers);
        if(calculators.size() == 1 && calculators.get(0) instanceof Integral){
            variableValueSuppliers.add(((Integral) calculators.get(0)).getVariableValueSupplier());
            return variableValueSuppliers;
        }
        if(variableValueSuppliers.isEmpty() || variableValueSuppliers.size() > 2){
            throw new RuntimeException("Invalid number of variables in expression. Must be 1 or 2.");
        }
        return variableValueSuppliers;
    }

    private List<Calculator> compileExpression(List<Calculator> calculators){
        return new InfixToPostfixNotationExpressionCompiler(calculatorSpecs).compile(calculators);
    }

    private List<Calculator> parseExpression(String expression){
        return new ExpressionParser(tokenTypes).parse(new Tokenizer(tokenTypes).tokenize(expression));
    }

    private List<Variable> extractVariables(List<Calculator> calculators){
        return calculators.stream()
                          .filter(calculator -> calculator.getType()
                                                          .contentEquals(OperatorType.VARIABLE.toString()))
                          .map(Variable.class::cast)
                          .collect(Collectors.toList());
    }

    private void tryToExtractVariableValueSupplier(String identifier, List<Variable> variables,
                                                   List<VariableValueSupplier> variableValueSuppliers){
        List<Variable> identifiedVariables = extractIdentifiedVariables(identifier, variables);
        if(!identifiedVariables.isEmpty()){
            variableValueSuppliers.add(new VariableValueSupplier(identifiedVariables));
        }
    }

    private List<Variable> extractIdentifiedVariables(String identifier, List<Variable> variables){
        return variables.stream()
                        .filter(variable -> variable.getIdentifier()
                                                    .contentEquals(identifier))
                        .collect(Collectors.toList());
    }
}
