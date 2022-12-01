package bg.sofia.tu.iti.graphics.d3.world;

import java.util.List;

public class Tick3DData{
    private final List<Tick3D> xTicks;
    private final List<Tick3D> yTicks;
    private final List<Tick3D> zTicks;

    public Tick3DData(List<Tick3D> xTicks, List<Tick3D> yTicks, List<Tick3D> zTicks){
        this.xTicks = xTicks;
        this.yTicks = yTicks;
        this.zTicks = zTicks;
    }

    public List<Tick3D> getXTicks(){
        return xTicks;
    }

    public List<Tick3D> getYTicks(){
        return yTicks;
    }

    public List<Tick3D> getZTicks(){
        return zTicks;
    }
}
