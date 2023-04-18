package bg.sofia.tu.iti.math.function.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum FunctionCalculatorType implements Notation{
    SINE("sin"),
    COSINE("cos"),
    TO_RADIANS("rad"),
    LOGARITHM("log"),
    INTEGRAL("Int");

    private final String notation;

    FunctionCalculatorType(String notation){
        this.notation = notation;
    }

    @Override
    public String getNotation(){
        return notation;
    }
}
