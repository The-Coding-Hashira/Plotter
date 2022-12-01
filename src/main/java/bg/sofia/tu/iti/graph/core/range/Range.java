package bg.sofia.tu.iti.graph.core.range;

public class Range{
    private double lowBoundary;
    private double highBoundary;

    public Range(double lowBoundary, double highBoundary){
        this.lowBoundary  = lowBoundary;
        this.highBoundary = highBoundary;
    }

    public void expand(double value){
        lowBoundary -= value;
        highBoundary += value;
    }

    public void shrink(double value){
        lowBoundary += value;
        highBoundary -= value;
    }

    public void move(double value){
        lowBoundary += value;
        highBoundary += value;
    }

    public double calculate(){
        return highBoundary - lowBoundary;
    }

    public double getLowBoundary(){
        return lowBoundary;
    }

    public double getHighBoundary(){
        return highBoundary;
    }
}
