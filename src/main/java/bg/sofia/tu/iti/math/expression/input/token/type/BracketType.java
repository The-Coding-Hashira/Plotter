package bg.sofia.tu.iti.math.expression.input.token.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum BracketType implements Notation{
    OPEN_BRACKET("("),
    CLOSE_BRACKET(")"),
    OPEN_SQUARE_BRACKET("["),
    CLOSE_SQUARE_BRACKET("]");

    private final String notation;

    BracketType(String notation){
        this.notation = notation;
    }

    @Override
    public String getNotation(){
        return notation;
    }
}
