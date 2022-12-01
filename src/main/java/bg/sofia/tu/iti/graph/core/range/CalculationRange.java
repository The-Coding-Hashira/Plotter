package bg.sofia.tu.iti.graph.core.range;

public class CalculationRange{
    private final double highBoundary;
    private final double lowBoundary;
    private final double step;

    public CalculationRange(double highBoundary, double lowBoundary, double step){
        this.highBoundary = highBoundary;
        this.lowBoundary  = lowBoundary;
        this.step         = step;
    }

    public double getHighBoundary(){
        return highBoundary;
    }

    public double getLowBoundary(){
        return lowBoundary;
    }

    public double getStep(){
        return step;
    }
}
