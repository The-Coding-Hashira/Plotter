package bg.sofia.tu.iti.math.operator.notation;

import bg.sofia.tu.iti.math.core.Notation;

public enum OperatorNotation implements Notation{
    PLUS{
        @Override
        public String getNotation(){
            return "+";
        }
    }, MINUS{
        @Override
        public String getNotation(){
            return "-";
        }
    }, MULTIPLY{
        @Override
        public String getNotation(){
            return "*";
        }
    }, DIVIDE{
        @Override
        public String getNotation(){
            return "/";
        }
    }, FACTORIAL{
        @Override
        public String getNotation(){
            return "!";
        }
    }, POWER{
        @Override
        public String getNotation(){
            return "^";
        }
    }, EQUALS{
        @Override
        public String getNotation(){
            return "=";
        }
    }
}
