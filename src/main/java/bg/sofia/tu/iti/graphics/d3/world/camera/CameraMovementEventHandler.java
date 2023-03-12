package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CameraMovementEventHandler{
    private final List<CameraMovementEventListener>     listeners;
    private final Consumer<CameraMovementEventListener> listenersNotifier;
    private final double                                sensitivity;
    private final double                                sphereRadius;
    private final Point4D                               lookAt   = new Point4D(0, 0, 0);
    private final Point4D                               cameraUp = new Point4D(0, 0, 1, 0);

    private double mouseLastDraggedAtX;
    private double mouseLastDraggedAtY;
    private double xRotation;
    private double yRotation;
    private Camera camera;

    public CameraMovementEventHandler(){
        listeners         = new ArrayList<>();
        listenersNotifier = this::notifyListeners;
        sensitivity       = 0.008;
        xRotation         = 0;
        yRotation         = 0;
        sphereRadius      = 3;
        camera            = new Camera(createCameraPosition(), lookAt, new Point4D(-1, 0, 0, 0));
    }

    public Camera createInitialCamera(double deltaX, double deltaY){
        xRotation += Math.toRadians(deltaX);
        onXRotation();
        deltaY = Math.toRadians(deltaY);
        yRotation += deltaY;
        onYRotation(deltaY);
        return camera;
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
            double deltaY = calculateRotation(mouseLastDraggedAtX - mouseEvent.getSceneX());
            yRotation += deltaY;
            onYRotation(deltaY);
        }
        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
            xRotation += calculateRotation(mouseLastDraggedAtY - mouseEvent.getSceneY());
            onXRotation();
        }
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
        listeners.forEach(listenersNotifier);
    }

    private double calculateRotation(double pixelDistance){
        return pixelDistance * sensitivity;
    }

    private void onYRotation(double deltaY){
        camera = new Camera(createCameraPosition(), lookAt, cameraUp);
    }

    private void onXRotation(){
        if(xRotation < 0.001){
            xRotation = 0.001;
        }
        else if(xRotation > Math.PI - 0.001){
            xRotation = Math.PI - 0.001;
        }
        Point4D cameraPosition = createCameraPosition();
        Point4D w = lookAt.subtract(cameraPosition)
                          .negate()
                          .normalize();
        camera = new Camera(cameraPosition,
                            camera.getU(),
                            camera.getU()
                                  .crossProduct(w)
                                  .negate()
                                  .normalize(),
                            w);
    }

    private Point4D createCameraPosition(){
        return new Point4D(sphereRadius * Math.sin(xRotation) * Math.cos(yRotation),
                           sphereRadius * Math.sin(xRotation) * Math.sin(yRotation),
                           sphereRadius * Math.cos(xRotation));
    }

    private void notifyListeners(CameraMovementEventListener cameraMovementEventListener){
        cameraMovementEventListener.onCameraMoved(camera);
    }
}
