package bg.sofia.tu.iti.math.expression;

import bg.sofia.tu.iti.math.function.Parameter;

import java.util.List;

public class ParameterValueSupplier{
    private final List<Parameter> consumers;

    public ParameterValueSupplier(List<Parameter> consumers){
        this.consumers = consumers;
    }

    public void emitValue(double value){
        consumers.forEach(parameter -> parameter.setValue(value));
    }
}
