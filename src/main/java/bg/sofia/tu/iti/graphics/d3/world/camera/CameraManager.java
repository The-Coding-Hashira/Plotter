package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import javafx.scene.input.MouseEvent;

public class CameraManager implements CameraMovementEventListener{
    private final CameraMovementEventHandler cameraMovementEventHandler;
    private final Camera                     camera;
    private       Camera                     tempCamera;
    Matrix4x4 cameraTransform;

    public CameraManager(Camera camera){
        cameraMovementEventHandler = new CameraMovementEventHandler();
        cameraMovementEventHandler.addListener(this);
        this.camera = camera;
        tempCamera  = camera;
    }

    @Override
    public void onCameraMoved(Matrix4x4 matrix4x4){
        cameraTransform = camera.rotate(matrix4x4)
                                .createTransform();
        tempCamera      = Camera.from(cameraTransform);
        //        cameraTransform = camera.rotate(matrix4x4).createTransform();
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

    public Matrix4x4 createTransform(){
        return cameraTransform;
    }
}
