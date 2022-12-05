package bg.sofia.tu.iti.math.function;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import bg.sofia.tu.iti.math.expression.result.ExpressionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomFunction extends Function implements Calculator{
    private final List<String> definitionArguments;
    private final List<Token>  expression;

    public CustomFunction(String identifier, List<String> definitionArguments, List<Token> expression){
        super(identifier, definitionArguments.size());
        this.definitionArguments = definitionArguments;
        this.expression          = expression;
    }

    @Override
    public Calculation calculate(Stack<Double> arguments){
        //TODO this laggy af
        List<Token>      expression = definitionArguments.isEmpty() ? this.expression : buildExpression(arguments);
        ExpressionResult result     = new ExpressionInterpreter().interpret(expression);
        return new Calculation(result.getFullDescription(), result.get());
    }

    private List<Token> buildExpression(Stack<Double> arguments){
        List<Token> expression = new ArrayList<>();
        for(Token token : this.expression){
            if(token.getType()
                    .contentEquals(MathElementType.IDENTIFIER.toString())){
                expression.add(processIdentifierToken(arguments, token));
            }
            else{
                expression.add(token);
            }
        }
        return expression;
    }

    private Token processIdentifierToken(Stack<Double> arguments, Token token){
        for(String definitionArgument : definitionArguments){
            if(token.getValue()
                    .contentEquals(definitionArgument)){
                Number value = arguments.pop();
                //System.out.println("------> Swapping " + definitionArgument + " with " + value.toString() + " in "
                // + getDescription());
                return new Token(MathElementType.NUMBER.toString(), value.toString());
            }
        }
        return token;
    }

    public String getDescription(){
        return getIdentifier() + "(" + getNumberOfArguments() + ")" + " = " + expression.size();
    }
}
