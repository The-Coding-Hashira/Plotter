package bg.sofia.tu.iti.math.expression.compiler;

import bg.sofia.tu.iti.math.core.calculator.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QueueCompiler{
    private final List<Calculator>  consumedCalculators;
    private final Stack<Calculator> queuedCalculators;

    public QueueCompiler(){
        consumedCalculators = new ArrayList<>();
        queuedCalculators   = new Stack<>();
    }

    public void pushToQueue(Calculator calculator){
        queuedCalculators.push(calculator);
    }

    public void consumeQueue(){
        while(!isQueueEmpty()){
            consumeQueueTop();
        }
    }

    public boolean isQueueEmpty(){
        return queuedCalculators.empty();
    }

    public void consumeQueueTop(){
        consume(popQueue());
    }

    public void consume(Calculator calculator){
        consumedCalculators.add(calculator);
    }

    public Calculator popQueue(){
        return queuedCalculators.pop();
    }

    public void discardQueueTop(){
        popQueue();
    }

    public Calculator peekQueue(){
        return queuedCalculators.peek();
    }

    public List<Calculator> getConsumedCalculators(){
        return consumedCalculators;
    }
}
