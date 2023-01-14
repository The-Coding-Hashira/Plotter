package bg.sofia.tu.iti.graph.d3;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.math.function.Function;

import java.util.List;

public class Graph3DData{
    private final Function     function;
    private final List<Tick>   xTicks;
    private final List<Tick>   yTicks;
    private final List<Tick>   zTicks;
    private final List<Double> graphValues;

    public Graph3DData(Function function, List<Tick> xTicks, List<Tick> yTicks, List<Tick> zTicks,
                       List<Double> graphValues){
        this.function    = function;
        this.xTicks      = xTicks;
        this.yTicks      = yTicks;
        this.zTicks      = zTicks;
        this.graphValues = graphValues;
    }
}
