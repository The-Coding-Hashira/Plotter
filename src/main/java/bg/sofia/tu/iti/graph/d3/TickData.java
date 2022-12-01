package bg.sofia.tu.iti.graph.d3;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;

import java.util.List;

public class TickData{
    private final List<Tick> xTicks;
    private final List<Tick> yTicks;
    private final List<Tick> zTicks;

    public TickData(List<Tick> xTicks, List<Tick> yTicks, List<Tick> zTicks){
        this.xTicks = xTicks;
        this.yTicks = yTicks;
        this.zTicks = zTicks;
    }

    public List<Tick> getXTicks(){
        return xTicks;
    }

    public List<Tick> getYTicks(){
        return yTicks;
    }

    public List<Tick> getZTicks(){
        return zTicks;
    }
}
