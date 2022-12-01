package bg.sofia.tu.iti.graphics.d3.transform;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class WorldTransformManager implements WorldRotationEventListener{
    private final TransformFactory          transformFactory;
    private       Matrix4x4                 cameraTransform;
    private final Matrix4x4                 projection;
    private final Matrix4x4                 viewport;
    private final Matrix4x4                 viewingTransform;
    private       Matrix4x4                 objectTransform;
    private       Matrix4x4                 worldTransform;
    private       Matrix4x4                 xRotationTransform;
    private       Matrix4x4                 zRotationTransform;
    private       Matrix4x4                 totalTransform;
    private final WorldRotationEventHandler worldRotationEventHandler;

    public WorldTransformManager(Builder builder){
        //TODO fix this somehow :) just make it work
        this.transformFactory     = builder.transformFactory;
        this.cameraTransform      = builder.cameraTransform;
        this.projection           = builder.projection;
        this.viewport             = builder.viewport;
        this.viewingTransform     = builder.viewingTransform;
        this.objectTransform      = builder.objectTransform;
        this.worldTransform       = builder.worldTransform;
        this.totalTransform       = builder.totalTransform;
        this.xRotationTransform   = builder.xRotationTransform;
        this.zRotationTransform   = builder.zRotationTransform;
        worldRotationEventHandler = new WorldRotationEventHandler();
        worldRotationEventHandler.addListener(this);
    }

    @Override
    public void onXRotation(double degrees){
        xRotationTransform = transformFactory.createRotationX(degrees);
        updateTransforms();
    }

    @Override
    public void onZRotation(double degrees){
        zRotationTransform = transformFactory.createRotationZ(degrees);
        updateTransforms();
    }

    public void onMousePressed(MouseEvent mouseEvent){
        worldRotationEventHandler.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        worldRotationEventHandler.onMouseDragged(mouseEvent);
    }

    public Point4D applyTotalTransform(Point4D point){
        return TransformUtils.transform(point, totalTransform);
    }

    public List<Point4D> applyTotalTransform(List<Point4D> points){
        return TransformUtils.transform(points, totalTransform);
    }

    public Matrix4x4 getViewingTransform(){
        return viewingTransform;
    }

    public Matrix4x4 getWorldTransform(){
        return worldTransform;
    }

    public Matrix4x4 getTotalTransform(){
        return totalTransform;
    }

    private void updateTransforms(){
        objectTransform = xRotationTransform.multiply(zRotationTransform);
        worldTransform  = cameraTransform.multiply(objectTransform);
        totalTransform  = viewingTransform.multiply(worldTransform);
    }

    public static class Builder{
        private TransformFactory transformFactory;
        private Matrix4x4        cameraTransform;
        private Matrix4x4        projection;
        private Matrix4x4        viewport;
        private Matrix4x4        viewingTransform;
        private Matrix4x4        objectTransform;
        private Matrix4x4        worldTransform;
        private Matrix4x4        xRotationTransform;
        private Matrix4x4        zRotationTransform;
        private Matrix4x4        totalTransform;

        public Builder withTransformFactory(TransformFactory transformFactory){
            this.transformFactory = transformFactory;
            return this;
        }

        public Builder withCameraTransform(Matrix4x4 cameraTransform){
            this.cameraTransform = cameraTransform;
            return this;
        }

        public Builder withProjection(Matrix4x4 projection){
            this.projection = projection;
            return this;
        }

        public Builder withViewport(Matrix4x4 viewport){
            this.viewport = viewport;
            return this;
        }

        public WorldTransformManager build(){
            viewingTransform   = viewport.multiply(projection);
            xRotationTransform = Matrix4x4.generateIdentity();
            zRotationTransform = Matrix4x4.generateIdentity();
            objectTransform    = Matrix4x4.generateIdentity();
            worldTransform     = Matrix4x4.generateIdentity();
            totalTransform     = viewingTransform.multiply(cameraTransform);
            return new WorldTransformManager(this);
        }
    }
}
