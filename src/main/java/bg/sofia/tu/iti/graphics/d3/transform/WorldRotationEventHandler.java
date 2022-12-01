package bg.sofia.tu.iti.graphics.d3.transform;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class WorldRotationEventHandler{
    private static final double sensitivity = 0.0001;

    private final List<WorldRotationEventListener> listeners;
    private       double                           mouseLastDraggedAtX;
    private       double                           mouseLastDraggedAtY;
    private       double                           xRotationRadians;
    private       double                           zRotationRadians;

    public WorldRotationEventHandler(){
        listeners = new ArrayList<>();
    }

    public void addListener(WorldRotationEventListener listener){
        listeners.add(listener);
    }

    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getSceneX() != mouseLastDraggedAtX){
            double radians = calculateRotationRadians(mouseLastDraggedAtX - mouseEvent.getSceneX());
            xRotationRadians -= radians;
            listeners.forEach(listener -> listener.onZRotation(Math.toDegrees(xRotationRadians)));
        }
        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
            double radians = calculateRotationRadians(mouseLastDraggedAtY - mouseEvent.getSceneY());
            zRotationRadians -= radians;
            listeners.forEach(listener -> listener.onXRotation(Math.toDegrees(zRotationRadians)));
        }
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    private double calculateRotationRadians(double pixelDistance){
        return pixelDistance * sensitivity;
    }
}
