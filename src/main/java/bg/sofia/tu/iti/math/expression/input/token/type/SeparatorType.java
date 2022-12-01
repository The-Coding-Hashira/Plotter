package bg.sofia.tu.iti.math.expression.input.token.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum SeparatorType implements Notation{
    COMMA{
        @Override
        public String getNotation(){
            return ",";
        }
    }
}
