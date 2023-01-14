package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.transform.WorldTransformManager;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

public class BoundsRenderer{
    private final BoundingBox           boundingBox;
    private final WorldTransformManager worldTransformManager;

    public BoundsRenderer(BoundingBox boundingBox, WorldTransformManager worldTransformManager){
        this.boundingBox           = boundingBox;
        this.worldTransformManager = worldTransformManager;
    }

    public void renderOccludedBounds(GraphicsContext3D graphicsContext3D, Camera camera){
        boundingBox.transform(worldTransformManager.getTotalTransform())
                   .rasterizeOccluded(graphicsContext3D, camera);
    }
}
