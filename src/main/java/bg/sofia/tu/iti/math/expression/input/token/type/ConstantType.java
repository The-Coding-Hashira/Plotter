package bg.sofia.tu.iti.math.expression.input.token.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum ConstantType implements Notation{
    E("e"), PI("pi");
    private final String notation;

    ConstantType(String notation){
        this.notation = notation;
    }

    @Override
    public String getNotation(){
        return notation;
    }
}
