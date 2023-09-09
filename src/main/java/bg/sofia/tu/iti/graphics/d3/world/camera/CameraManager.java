package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import javafx.scene.input.MouseEvent;

public class CameraManager implements CameraMovementEventListener{
    private final CameraMovementEventHandler cameraMovementEventHandler;
    private       Camera                     camera;
    private       Matrix4x4                  cameraTransform;

    public CameraManager(double cameraXRotation, double cameraYRotation){
        cameraMovementEventHandler = new CameraMovementEventHandler();
        cameraMovementEventHandler.addListener(this);
        camera          = cameraMovementEventHandler.createInitialCamera(cameraXRotation, cameraYRotation);
        cameraTransform = camera.createTransform();
    }

    @Override
    public void onCameraMoved(Camera camera){
        this.camera     = camera;
        cameraTransform = camera.createTransform();
    }

    public void onMousePressed(MouseEvent mouseEvent){
        cameraMovementEventHandler.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        cameraMovementEventHandler.onMouseDragged(mouseEvent);
    }

    public Camera getCamera(){
        return camera;
    }

    public Matrix4x4 getTransform(){
        return cameraTransform;
    }
}
