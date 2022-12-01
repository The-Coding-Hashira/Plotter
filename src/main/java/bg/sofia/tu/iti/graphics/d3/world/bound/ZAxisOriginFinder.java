package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.d3.Geometry3DUtils;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.List;

public class ZAxisOriginFinder implements AxisBoundOriginFinder{
    private final List<Point4D> bottomPlanePoints;

    public ZAxisOriginFinder(BoundingBox boundingBox){
        bottomPlanePoints = boundingBox.getBottomPlane()
                                       .getPoints();
    }

    @Override
    public Point4D find(Camera camera){
        Point4D origin = bottomPlanePoints.stream()
                                          .map(point -> point.subtract(camera.getPosition()))
                                          .max((p0, p1) -> Geometry3DUtils.compareLeftOfCamera(p0, p1, camera))
                                          .orElseThrow(IllegalArgumentException::new);
        return camera.getPosition()
                     .add(origin);
    }
}
