package bg.sofia.tu.iti.math.expression.compiler;

import bg.sofia.tu.iti.math.core.calculator.Calculator;

public interface CompileHandler{
    void handle(QueueCompiler queueCompiler, Calculator calculator);
}
