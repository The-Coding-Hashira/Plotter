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
        return clipAgainstPlane(clipAgainstPlane(points, boundingBox.getTopPlane()), boundingBox.getBottomPlane());
    }

    private List<Point4D> clipAgainstPlane(List<Point4D> points, Quad quad){
        List<Point4D> clippedPoints = new ArrayList<>();
        Geometry3DUtils.iterateTriangles(points,
                                         ((p0, p1, p2) -> clippedPoints.addAll(triangleClipper.clip(p0,
                                                                                                    p1,
                                                                                                    p2,
                                                                                                    quad))));
        return clippedPoints;
    }
}
