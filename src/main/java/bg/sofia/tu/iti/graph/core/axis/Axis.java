package bg.sofia.tu.iti.graph.core.axis;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graph.core.axis.tick.TickGenerator;
import bg.sofia.tu.iti.graph.core.range.Range;

import java.util.List;

public class Axis{
    private Range range;

    public Axis(Range range){
        this.range = range;
    }

    public List<Tick> generateTicks(TickGenerator tickGenerator){
        return tickGenerator.generate(range);
    }

    public Range getRange(){
        return range;
    }

    public void setRange(Range range){
        this.range = range;
    }
}
