package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.d3.Geometry3DUtils;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.Comparator;
import java.util.List;

public class YAxisOriginFinder implements AxisBoundOriginFinder{
    private final List<Point4D> bottomPlanePoints;
    private final Point4D       yAxis;

    public YAxisOriginFinder(BoundingBox boundingBox){
        bottomPlanePoints = boundingBox.getBottomPlane()
                                       .getPoints();
        yAxis             = new Point4D(0, 1, 0);
    }

    @Override
    public Point4D find(Camera camera){
        double xDotU = yAxis.dotProduct(camera.getU());
        double xDotW = yAxis.dotProduct(camera.getW());
        if(camera.getW()
                 .getZ() > 0){
            if(xDotU > 0 && xDotW > 0){
                return find(camera, (p0, p1) -> Geometry3DUtils.compareLeftOfCamera(p0, p1, camera));
            }
            if(xDotW > 0){
                return find(camera, (p0, p1) -> Geometry3DUtils.compareRightOfCamera(p0, p1, camera));
            }
            return find(camera, Geometry3DUtils::compareSmallestDistanceToCamera);
        }
        if(xDotU < 0 && xDotW < 0){
            return find(camera, (p0, p1) -> Geometry3DUtils.compareRightOfCamera(p0, p1, camera));
        }
        if(xDotW < 0){
            return find(camera, (p0, p1) -> Geometry3DUtils.compareLeftOfCamera(p0, p1, camera));
        }
        return find(camera, Geometry3DUtils::compareFarthestDistanceToCamera);
    }

    private Point4D find(Camera camera, Comparator<Point4D> comparator){
        Point4D origin = bottomPlanePoints.stream()
                                          .map(point -> point.subtract(camera.getPosition()))
                                          .max(comparator)
                                          .orElseThrow(IllegalArgumentException::new);
        return camera.getPosition()
                     .add(origin);
    }
}
