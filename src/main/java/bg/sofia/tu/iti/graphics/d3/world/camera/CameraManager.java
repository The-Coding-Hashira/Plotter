package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import javafx.scene.input.MouseEvent;

public class CameraManager{
    private Camera camera;

    public CameraManager(Camera camera){
        this.camera = camera;
    }

    public Camera getCamera(){
        return camera;
    }
}
