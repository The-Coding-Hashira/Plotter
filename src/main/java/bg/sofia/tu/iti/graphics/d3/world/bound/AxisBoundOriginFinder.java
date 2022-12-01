package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

public interface AxisBoundOriginFinder{
    Point4D find(Camera camera);
}
