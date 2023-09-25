package bg.sofia.tu.iti.math.function.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum FunctionCalculatorType implements Notation{
    SINE("sin"),
    COSINE("cos"),
    TANGENT("tan"),
    TO_RADIANS("rad"),
    NATURAL_LOGARITHM("ln"),
    COMMON_LOGARITHM("lg"),
    INTEGRAL("Int"),
    ABSOLUTE("abs"),
    SIGNUM("sign"),
    SQRT("sqrt");

    private final String notation;

    FunctionCalculatorType(String notation){
        this.notation = notation;
    }

    @Override
    public String getNotation(){
        return notation;
    }
}
