package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;

import java.util.List;

public class Tick2DData{
    private final List<Tick> xTicks;
    private final List<Tick> yTicks;

    public Tick2DData(List<Tick> xTicks, List<Tick> yTicks){
        this.xTicks = xTicks;
        this.yTicks = yTicks;
    }

    public List<Tick> getXTicks(){
        return xTicks;
    }

    public List<Tick> getYTicks(){
        return yTicks;
    }
}
