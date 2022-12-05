package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import bg.sofia.tu.iti.graphics.d3.transform.TransformFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CameraMovementEventHandler{
    private final List<CameraMovementEventListener>     listeners;
    private final Consumer<CameraMovementEventListener> listenersNotifier;
    private final TransformFactory                      transformFactory;
    private final double                                sensitivity;

    private double    mouseLastDraggedAtX;
    private double    mouseLastDraggedAtY;
    private double    xRotation;
    private double    zRotation;
    private Matrix4x4 rotation;

    public CameraMovementEventHandler(){
        listeners         = new ArrayList<>();
        listenersNotifier = this::notifyListeners;
        transformFactory  = new TransformFactory();
        sensitivity       = 0.005;
        xRotation         = 0;
        zRotation         = 0;
        rotation          = Matrix4x4.generateIdentity();
    }

    public void addListener(CameraMovementEventListener listener){
        listeners.add(listener);
    }

    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getSceneX() != mouseLastDraggedAtX){
            zRotation += calculateRotation(-(mouseLastDraggedAtX - mouseEvent.getSceneX()));

        }
        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
            xRotation += calculateRotation(-(mouseLastDraggedAtY - mouseEvent.getSceneY()));
        }
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
        rotation            = createRotation();
        listeners.forEach(listenersNotifier);
    }

    private double calculateRotation(double pixelDistance){
        return pixelDistance * sensitivity;
    }

    private void notifyListeners(CameraMovementEventListener cameraMovementEventListener){
        cameraMovementEventListener.onCameraMoved(rotation);
    }

    private Matrix4x4 createRotation(){
        return transformFactory.createRotationZ(zRotation)
                               .multiply(transformFactory.createRotationX(xRotation));
    }
}
