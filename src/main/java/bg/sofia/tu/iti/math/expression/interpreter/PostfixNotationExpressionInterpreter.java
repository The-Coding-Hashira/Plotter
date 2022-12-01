package bg.sofia.tu.iti.math.expression.interpreter;

import bg.sofia.tu.iti.math.core.Calculation;
import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.expression.input.token.type.SeparatorType;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixNotationExpressionInterpreter{
    public List<Calculation> interpret(List<Calculator> calculators){
        List<Calculation> calculations = new ArrayList<>();
        Stack<Double>     result       = new Stack<>();
        for(Calculator calculator : calculators){
            if(calculator.getType()
                         .contentEquals(SeparatorType.COMMA.toString())){
                continue;
            }
            Calculation calculation = interpretCalculator(calculator, result);
            result.push(calculation.getResult());
            if(!calculator.getType()
                          .contentEquals(MathElementType.NUMBER.toString())){
                calculations.add(calculation);
            }
        }
        if(result.size() > 1){
            throw new RuntimeException("Expected 1 result in the stack got: " + result.size());
        }
        if(result.size() < 1){
            throw new RuntimeException("There is no result in the stack");
        }
        calculations.add(new Calculation("result", result.pop()));
        return calculations;
    }

    private Calculation interpretCalculator(Calculator calculator, Stack<Double> result){
        Stack<Double> arguments = new Stack<>();
        for(int i = 0; i < calculator.getNumberOfArguments(); i++){
            arguments.push(result.pop());
        }
        return calculator.calculate(arguments);
    }
}
