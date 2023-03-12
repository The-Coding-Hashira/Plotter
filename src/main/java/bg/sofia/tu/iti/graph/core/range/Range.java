package bg.sofia.tu.iti.graph.core.range;

public class Range{
    private double lowBoundary;
    private double highBoundary;

    private final double modificationRatio;

    public Range(double lowBoundary, double highBoundary){
        this.lowBoundary  = lowBoundary;
        this.highBoundary = highBoundary;
        modificationRatio = 0.05;
    }

    public void expand(){
        expand(findModificationValue());
    }

    public void expand(double value){
        double newLowBoundary  = lowBoundary - value;
        double newHighBoundary = highBoundary + value;
        if(newLowBoundary >= newHighBoundary){
            return;
        }
        lowBoundary -= value;
        highBoundary += value;
    }

    public void shrink(){
        shrink(findModificationValue());
    }

    public void shrink(double value){
        double newLowBoundary  = lowBoundary + value;
        double newHighBoundary = highBoundary - value;
        if(newLowBoundary >= newHighBoundary){
            return;
        }
        lowBoundary  = newLowBoundary;
        highBoundary = newHighBoundary;
    }

    public void moveParameterized(double parameter){
        move(parameter * calculate());
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

    private double findModificationValue(){
        return modificationRatio * calculate();
    }
}
