package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import javafx.scene.input.MouseEvent;

public class CameraManager implements CameraMovementEventListener{
    public static Point4D                    cPos     = new Point4D(-1, 0, 0);
    public static Point4D                    lookPos  = new Point4D(0, 0, 0);
    public static Point4D                    upPos    = new Point4D(0, 0, 1, 0);
    public static Camera                     LECamera = new Camera(cPos, lookPos, upPos);
    private final CameraMovementEventHandler cameraMovementEventHandler;
    private final Camera                     camera;
    Matrix4x4 cameraTransform;
    private       Camera                     tempCamera;

    public CameraManager(Camera camera){
        cameraMovementEventHandler = new CameraMovementEventHandler();
        cameraMovementEventHandler.addListener(this);
        this.camera     = camera;
        tempCamera      = camera;
        cameraTransform = camera.createTransform();
    }

    @Override
    public void onCameraMoved(Matrix4x4 transform){
        tempCamera      = LECamera;
        cameraTransform = tempCamera.createTransform();
        //        Matrix4x4 arg = cameraTransform.invert();
        //        arg             = arg.multiply(transform);
        //        cameraTransform = arg.invert();
        //        tempCamera      = Camera.from(cameraTransform);
        //        cameraTransform = camera.rotate(transform)
        //                                .createTransform();
        //        tempCamera      = Camera.from(cameraTransform);
        //        cameraTransform = camera.rotate(matrix4x4).createTransform();
    }

    public void onMousePressed(MouseEvent mouseEvent){
        cameraMovementEventHandler.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        cameraMovementEventHandler.onMouseDragged(mouseEvent);
    }

    public Camera getCamera(){
        return tempCamera;
    }

    public Matrix4x4 createTransform(){
        return cameraTransform;
    }
}
