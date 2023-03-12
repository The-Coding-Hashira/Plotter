package bg.sofia.tu.iti.graph.core.axis.tick;

import bg.sofia.tu.iti.graph.d2.axis.AxisPainter;

public class Tick{
    private final double value;
    private final double normalizedValue;
    private final String text;

    public Tick(double value, double normalizedValue, String text){
        this.value           = value;
        this.normalizedValue = normalizedValue;
        this.text            = text;
    }

    public double getValue(){
        return value;
    }

    public double getNormalizedValue(){
        return normalizedValue;
    }

    public String getText(){
        return text;
    }
    public void paint(AxisPainter axisPainter){
        axisPainter.strokeTickLineNormalized(normalizedValue);
        axisPainter.fillTickTextNormalized(text, normalizedValue);
    }
}
