package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

public class TickDataRenderer{
    private final XTickDataRenderer xTickDataRenderer;
    private final YTickDataRenderer yTickDataRenderer;
    private final ZTickDataRenderer zTickDataRenderer;

    public TickDataRenderer(){
        this.xTickDataRenderer = new XTickDataRenderer();
        this.yTickDataRenderer = new YTickDataRenderer();
        this.zTickDataRenderer = new ZTickDataRenderer();
    }

    public void render(Tick3DData tickData, GraphicsContext3D graphicsContext3D, Camera camera){
        xTickDataRenderer.render(tickData.getXTicks(), graphicsContext3D, camera);
        yTickDataRenderer.render(tickData.getYTicks(), graphicsContext3D, camera);
        zTickDataRenderer.render(tickData.getZTicks(), graphicsContext3D, camera);
    }
}
