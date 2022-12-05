package bg.sofia.tu.iti.math.expression.compiler;

import bg.sofia.tu.iti.math.core.calculator.Calculator;
import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorSpec;
import bg.sofia.tu.iti.math.expression.input.token.MathElementType;
import bg.sofia.tu.iti.math.expression.input.token.type.BracketType;
import bg.sofia.tu.iti.math.operator.type.OperatorType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfixToPostfixNotationExpressionCompiler{
    private final List<CalculatorSpec>        calculatorSpecList;
    private final Map<String, CompileHandler> compileHandlers;

    public InfixToPostfixNotationExpressionCompiler(List<CalculatorSpec> calculatorSpecList){
        this.calculatorSpecList = calculatorSpecList;
        compileHandlers         = new HashMap<>();
        //TODO sort this mess out with the different enums here
        compileHandlers.put(MathElementType.NUMBER.toString(), this::handleConstantCalculation);
        compileHandlers.put(OperatorType.FUNCTION.toString(), this::handleFunctionCalculation);
        compileHandlers.put(BracketType.OPEN_BRACKET.toString(), this::handleOpenBracketCalculation);
        compileHandlers.put(BracketType.CLOSE_BRACKET.toString(), this::handleCloseBracketCalculation);
    }

    public List<Calculator> compile(List<Calculator> calculators){
        QueueCompiler queueCompiler = new QueueCompiler();
        for(Calculator calculator : calculators){
            if(compileHandlers.containsKey(calculator.getType())){
                compileHandlers.get(calculator.getType())
                               .handle(queueCompiler, calculator);
            }
            else{
                handleOperatorCalculation(queueCompiler, calculator);
            }
        }
        queueCompiler.consumeQueue();
        return queueCompiler.getConsumedCalculators();
    }

    private void handleFunctionCalculation(QueueCompiler queueCompiler, Calculator calculator){
        queueCompiler.pushToQueue(calculator);
    }

    private void handleConstantCalculation(QueueCompiler queueCompiler, Calculator calculator){
        queueCompiler.consume(calculator);
    }

    private void handleOpenBracketCalculation(QueueCompiler queueCompiler, Calculator calculator){
        queueCompiler.pushToQueue(calculator);
    }

    private void handleCloseBracketCalculation(QueueCompiler queueCompiler, Calculator calculator){
        while(!queueCompiler.isQueueEmpty()){
            if(!queueCompiler.peekQueue()
                             .getType()
                             .equals(BracketType.OPEN_BRACKET.toString())){
                queueCompiler.consumeQueueTop();
                continue;
            }
            break;
        }
        queueCompiler.discardQueueTop();
    }

    private void handleOperatorCalculation(QueueCompiler queueCompiler, Calculator calculator){
        while(!queueCompiler.isQueueEmpty()){
            if(queueCompiler.peekQueue()
                            .getType()
                            .contentEquals(BracketType.OPEN_BRACKET.toString())){
                break;
            }
            if(compareCalculatorPostfixNotationOrder(calculator, queueCompiler.peekQueue()) < 0){
                queueCompiler.consumeQueueTop();
                continue;
            }
            break;
        }
        queueCompiler.pushToQueue(calculator);
    }

    private int compareCalculatorPostfixNotationOrder(Calculator calculator, Calculator compareToCalculator){
        return matchCalculatorData(calculator).comparePostfixNotationOrderTo(matchCalculatorData(compareToCalculator));
    }

    private CalculatorSpec matchCalculatorData(Calculator calculator){
        for(CalculatorSpec calculatorSpec : calculatorSpecList){
            if(calculatorSpec.getType()
                             .contentEquals(calculator.getType())){
                return calculatorSpec;
            }
        }
        throw new RuntimeException("Unknown Calculator type: " + calculator.getType());
    }
}
