package bg.sofia.tu.iti.graph.core.axis.tick;

import bg.sofia.tu.iti.graph.core.range.Range;

import java.util.ArrayList;
import java.util.List;

public class AxisTickValuesManager{
    private final int          ticks;
    private final List<Double> tickValues;

    public AxisTickValuesManager(int ticks){
        this.ticks = ticks;
        tickValues = new ArrayList<>();
    }

    public void updateTickValues(Range range){
        tickValues.clear();
        if(range.getLowBoundary() >= 0){
            addPositiveTickValues(range);
            return;
        }
        if(range.getHighBoundary() <= 0){
            addNegativeTickValues(range);
            return;
        }
        addPositiveTickValues(range);
        addNegativeTickValues(range);
    }

    public List<Double> getTickValues(){
        return tickValues;
    }

    private void addPositiveTickValues(Range range){
        for(double tickValue = 0; tickValue <= range.getHighBoundary(); tickValue += calculateTickSpacing(range)){
            if(tickValue >= range.getLowBoundary()){
                tickValues.add(tickValue);
            }
        }
    }

    private void addNegativeTickValues(Range range){
        for(double tickValue = 0; tickValue >= range.getLowBoundary(); tickValue -= calculateTickSpacing(range)){
            if(tickValue <= range.getHighBoundary()){
                tickValues.add(tickValue);
            }
        }
    }

    private double calculateTickSpacing(Range range){
        double spacing = range.calculate() / ticks;
        if(spacing < 1){
            int factor = 0;
            while(spacing < 1){
                spacing *= 10;
                factor++;
            }
            if(!isWholeNumber(spacing)){
                spacing = toWholeNumber(spacing);
            }
            return reduceNumber(spacing, factor);
        }
        if(!isWholeNumber(spacing)){
            return toWholeNumber(spacing);
        }
        return spacing;
    }

    private boolean isWholeNumber(double number){
        return number % 1 == 0;
    }

    private double toWholeNumber(double number){
        return number - number % 1;
    }

    private double reduceNumber(double number, double factor){
        for(int i = 1; i <= factor; i++){
            number /= 10;
        }
        return number;
    }
}
