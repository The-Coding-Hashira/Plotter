package bg.sofia.tu.iti.graphics.d3.transform;

import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

public class TransformManagerFactory{
    public WorldTransformManager create(Camera camera, double width, double height){
        TransformFactory transformFactory = new TransformFactory();
        return new WorldTransformManager.Builder().withTransformFactory(new TransformFactory())
                                                  .withCameraTransform(camera.createTransform())
                                                  .withViewport(transformFactory.createViewport(width, height))
                                                  .withProjection(transformFactory.createOrthographicProjection(0.8,
                                                                                                           width / height))
                                                  .build();
    }
}
