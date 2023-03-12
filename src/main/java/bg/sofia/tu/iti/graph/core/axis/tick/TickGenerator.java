package bg.sofia.tu.iti.graph.core.axis.tick;

import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graph.d3.GraphUtils;

import java.util.ArrayList;
import java.util.List;

public class TickGenerator{
    private final int numberOfTicks;

    public TickGenerator(int numberOfTicks){
        this.numberOfTicks = numberOfTicks;
    }

    public List<Tick> generate(Range range){
        List<Tick> ticks             = new ArrayList<>(numberOfTicks);
        double     spacing           = calculateTickSpacing(range);
        double     rangeLowBoundary  = range.getLowBoundary();
        double     tick              = Math.floor(rangeLowBoundary / spacing) * spacing;
        double     rangeHighBoundary = range.getHighBoundary();
        while(tick <= rangeHighBoundary){
            if(tick >= rangeLowBoundary){
                ticks.add(createTick(tick, range));
            }
            tick += spacing;
        }
        return ticks;
    }

    private double calculateTickSpacing(Range range){
        double spacing = range.calculate() / numberOfTicks;
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
        if(isWholeNumber(spacing)){
            return spacing;
        }
        return toWholeNumber(spacing);
    }

    private Tick createTick(double value, Range range){
        return new Tick(value,
                        GraphUtils.findParameter(value, range.getLowBoundary(), range.calculate()),
                        GraphUtils.toEngineeringNotation(value));
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
