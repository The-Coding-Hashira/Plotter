package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
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
    private double    yRotation;
    private Matrix4x4 rotation;

    public CameraMovementEventHandler(){
        listeners         = new ArrayList<>();
        listenersNotifier = this::notifyListeners;
        transformFactory  = new TransformFactory();
        sensitivity       = 0.008;
        xRotation         = 0;
        yRotation         = 0;
        rotation          = Matrix4x4.generateIdentity();
    }

    public void addListener(CameraMovementEventListener listener){
        listeners.add(listener);
    }

    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    //    public void onMouseDragged(MouseEvent mouseEvent){
    //        if(mouseEvent.getSceneX() != mouseLastDraggedAtX){
    //            yRotation = calculateRotation(mouseLastDraggedAtX - mouseEvent.getSceneX());
    //        }
    //        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
    //            xRotation = calculateRotation(mouseLastDraggedAtY - mouseEvent.getSceneY());
    //        }
    //        mouseLastDraggedAtX = mouseEvent.getSceneX();
    //        mouseLastDraggedAtY = mouseEvent.getSceneY();
    //
    //        rotation = createRotation();
    //        listeners.forEach(listenersNotifier);
    //    }
    public void onMouseDragged(MouseEvent mouseEvent){
        double r = 2;
        if(mouseEvent.getSceneX() != mouseLastDraggedAtX){
            yRotation += calculateRotation(mouseLastDraggedAtX - mouseEvent.getSceneX());
            CameraManager.cPos     = new Point4D(r * Math.sin(xRotation) * Math.cos(yRotation),
                                                 r * Math.sin(xRotation) * Math.sin(yRotation),
                                                 r * Math.cos(xRotation));
            CameraManager.LECamera = new Camera(CameraManager.cPos, CameraManager.lookPos, CameraManager.upPos);
        }
        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
            xRotation += calculateRotation(mouseLastDraggedAtY - mouseEvent.getSceneY());
            if(xRotation < 0.001){
                xRotation = 0.001;
            }
            else if(xRotation > Math.PI - 0.001){
                xRotation = Math.PI - 0.001;
            }
            CameraManager.cPos = new Point4D(r * Math.sin(xRotation) * Math.cos(yRotation),
                                             r * Math.sin(xRotation) * Math.sin(yRotation),
                                             r * Math.cos(xRotation));
            Point4D w = CameraManager.lookPos.subtract(CameraManager.cPos)
                                             .negate()
                                             .normalize();
            CameraManager.LECamera = new Camera(CameraManager.cPos,
                                                CameraManager.LECamera.getU(),
                                                CameraManager.LECamera.getU()
                                                                      .crossProduct(w)
                                                                      .negate(),
                                                w);
        }
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
        //        rotation = createRotation();
        listeners.forEach(listenersNotifier);
    }

    private double calculateRotation(double pixelDistance){
        return pixelDistance * sensitivity;
    }

    private Matrix4x4 createRotation(){
        return transformFactory.createRotationY(yRotation)
                               .multiply(transformFactory.createRotationX(xRotation));
    }

    private void notifyListeners(CameraMovementEventListener cameraMovementEventListener){
        cameraMovementEventListener.onCameraMoved(rotation);
    }
}
