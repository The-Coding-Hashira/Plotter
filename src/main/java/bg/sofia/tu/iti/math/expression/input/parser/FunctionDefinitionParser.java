package bg.sofia.tu.iti.math.expression.input.parser;

import bg.sofia.tu.iti.math.operator.notation.OperatorNotation;
import bg.sofia.tu.iti.math.function.CustomFunction;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;
import bg.sofia.tu.iti.math.core.input.token.Token;
import bg.sofia.tu.iti.math.core.input.evaluator.TokenizationIntegrityEvaluator;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;
import bg.sofia.tu.iti.math.expression.input.token.TokenType;
import bg.sofia.tu.iti.math.expression.input.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinitionParser{
    private final List<TokenType> tokenTypes;

    public FunctionDefinitionParser(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public CustomFunction parse(String definition){
        List<Token> tokens = new Tokenizer(tokenTypes).tokenize(definition);
        new TokenizationIntegrityEvaluator().evaluate(tokens, definition);
        //TODO optimize: pass cut string to only express id args and expr, maybe after extract id cut the id, after
        // extract args cut the args and leave expr only
        String       identifier = extractIdentifier(tokens);
        List<String> arguments  = extractArguments(tokens);
        List<Token>  expression = extractExpression(tokens);
        return new CustomFunction(identifier, arguments, expression);
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

    private List<String> extractArguments(List<Token> tokens){
        List<String> arguments = new ArrayList<>();
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
                arguments.add(tokens.get(i)
                                    .getValue());
                continue;
            }
            if(tokens.get(i)
                     .getType()
                     .contentEquals(MathElementType.NUMBER.toString())){
                throw new RuntimeException("Cannot have a number as a function argument in the function definition");
            }
            if(!tokens.get(i)
                      .getType()
                      .contentEquals(MathElementType.SEPARATOR.toString())){
                throw new RuntimeException("Invalid function definition, expected argument not separator");
            }
        }
        return arguments;
    }

    private List<Token> extractExpression(List<Token> tokens){
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i)
                     .getType()
                     .contentEquals(MathElementType.OPERATOR.toString())){
                if(tokens.get(i)
                         .getValue()
                         .contentEquals(OperatorNotation.EQUALS.getNotation())){
                    return tokens.subList(i + 1, tokens.size());
                }
            }
        }
        throw new RuntimeException("invalid function definition");
    }
}
