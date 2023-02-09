package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.List;

public class ZTickDataRenderer{
    public void render(List<Tick3D> ticks, GraphicsContext3D graphicsContext3D, Camera camera){
        //TODO check why grid is 51 when 50 entered
        //TODO can check if x is more than half display res, therefore + or - offset(50)
        for(Tick3D tick3D : ticks){
            graphicsContext3D.fillText(tick3D.getTick()
                                             .getText(),
                                       tick3D.getPosition()
                                             .getX() - 50,
                                       tick3D.getPosition()
                                             .getY());
        }
    }
}
