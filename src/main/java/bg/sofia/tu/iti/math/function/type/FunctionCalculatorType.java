package bg.sofia.tu.iti.math.function.type;

import bg.sofia.tu.iti.math.core.Notation;

public enum FunctionCalculatorType implements Notation{
    SINE{
        @Override
        public String getNotation(){
            return "sin";
        }
    }, COSINE{
        @Override
        public String getNotation(){
            return "cos";
        }
    }, TO_RADIANS{
        @Override
        public String getNotation(){
            return "rad";
        }
    }
}
