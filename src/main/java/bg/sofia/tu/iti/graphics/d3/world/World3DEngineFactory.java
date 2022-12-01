package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.TransformManagerFactory;
import bg.sofia.tu.iti.graphics.d3.transform.WorldTransformManager;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundingBox;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundingBoxFactory;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;
import bg.sofia.tu.iti.graphics.d3.world.camera.CameraManager;
import javafx.scene.paint.Color;

public class World3DEngineFactory{
    public World3DEngine create(double width, double height){
        //TODO use camera with proper UP vector
        Camera camera = createCamera();
        return new World3DEngine(createBoundingBox(),
                                 new CameraManager(camera),
                                 createWorldTransformManager(camera, width, height));
    }

    private Camera createCamera(){
        //@formatter:off
        return new Camera(new Point4D(0, -1, 0),
                          new Point4D(0, 0, 0),
                          new Point4D(0, 0, 1));
        //@formatter:on
    }

    private BoundingBox createBoundingBox(){
        return new BoundingBoxFactory().createBoundingBox(new Point4D(0, 0, 0), 1, Color.BLACK);
    }

    private WorldTransformManager createWorldTransformManager(Camera camera, double width, double height){
        return new TransformManagerFactory().create(camera, width, height);
    }
}
