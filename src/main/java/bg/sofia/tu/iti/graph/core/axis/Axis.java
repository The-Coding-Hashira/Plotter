package bg.sofia.tu.iti.graph.core.axis;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graph.core.range.Range;

import java.util.List;

public class Axis{
    private final Range         range;
    private final TickGenerator tickGenerator;

    public Axis(Range range, TickGenerator tickGenerator){
        this.range         = range;
        this.tickGenerator = tickGenerator;
    }

    public List<Tick> generateTicks(){
        return tickGenerator.generate(range);
    }

    public Range getRange(){
        return range;
    }
}
