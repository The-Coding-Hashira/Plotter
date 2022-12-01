package bg.sofia.tu.iti.math.expression.input.token;

import bg.sofia.tu.iti.math.core.input.token.Token;

import java.util.List;

public final class TokenOperations{
    public static TokenType matchTokenType(List<TokenType> tokenTypes, Token token){
        for(TokenType tokenType : tokenTypes){
            if(tokenType.getName()
                        .equals(token.getType())){
                return tokenType;
            }
        }
        throw new RuntimeException("No such token type");
    }

    public static void updateToken(int tokenIndex, List<Token> tokens, Token newToken){
        tokens.remove(tokenIndex);
        tokens.add(tokenIndex, newToken);
    }

    private TokenOperations(){
    }
}
