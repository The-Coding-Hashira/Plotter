package bg.sofia.tu.iti.graphics.d3.world.clip;

import bg.sofia.tu.iti.graphics.d3.Geometry3DUtils;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.geometry.Quad;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class BoundsClipper{
    private final TriangleClipper triangleClipper;

    public BoundsClipper(){
        this.triangleClipper = new TriangleClipper();
    }

    public List<Point4D> clipAgainstBoundingBox(List<Point4D> points, BoundingBox boundingBox){
        return clipAgainstBottomPlane(clipAgainstPlane(points,
                                                       boundingBox.getTopPlane()
                                                                  .getNormal()), boundingBox.getBottomPlane());
    }

    private List<Point4D> clipAgainstBottomPlane(List<Point4D> points, Quad bottomPlane){
        return clipAgainstPlane(points, bottomPlane.getNormal());
    }

    private List<Point4D> clipAgainstPlane(List<Point4D> points, Point4D normal){
        List<Point4D> clippedPoints = new ArrayList<>();
        Geometry3DUtils.iterateTriangles(points,
                                         ((p0, p1, p2) -> clippedPoints.addAll(triangleClipper.clip(p0,
                                                                                                    p1,
                                                                                                    p2,
                                                                                                    normal))));
        return clippedPoints;
    }
}
