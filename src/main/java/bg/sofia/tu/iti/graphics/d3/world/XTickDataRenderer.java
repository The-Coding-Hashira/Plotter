package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.List;

public class XTickDataRenderer{
    public void render(List<Tick3D> ticks, GraphicsContext3D graphicsContext3D, Camera camera){
        for(Tick3D tick3D : ticks){
            graphicsContext3D.fillText(tick3D.getTick()
                                             .getText(),
                                       tick3D.getPosition()
                                             .getX(),
                                       tick3D.getPosition()
                                             .getY() + 21);
        }
    }
}
