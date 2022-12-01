package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

public class Tick3D{
    private final Tick tick;
    private final Point4D position;

    public Tick3D(Tick tick, Point4D position){
        this.tick     = tick;
        this.position = position;
    }

    public Tick getTick(){
        return tick;
    }

    public Point4D getPosition(){
        return position;
    }
}
