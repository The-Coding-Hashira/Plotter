package bg.sofia.tu.iti.math.expression;

import bg.sofia.tu.iti.math.function.Variable;

import java.util.List;

public class VariableValueSupplier{
    private final List<Variable> consumers;

    public VariableValueSupplier(List<Variable> consumers){
        this.consumers = consumers;
    }

    public void supply(double value){
        consumers.forEach(variable -> variable.setValue(value));
    }
}
