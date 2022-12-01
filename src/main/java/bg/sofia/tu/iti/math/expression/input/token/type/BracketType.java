package bg.sofia.tu.iti.math.expression.input.token.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum BracketType implements Notation{
    OPEN_BRACKET{
        @Override
        public String getNotation(){
            return "(";
        }
    }, CLOSE_BRACKET{
        @Override
        public String getNotation(){
            return ")";
        }
    }
}
