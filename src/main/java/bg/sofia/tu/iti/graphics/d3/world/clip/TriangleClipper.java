package bg.sofia.tu.iti.graphics.d3.world.clip;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

import java.util.List;

public class TriangleClipper{
    public List<Point4D> clip(Point4D p0, Point4D p1, Point4D p2, Point4D clippingPlaneNormal){
        ClipData clipData = generateClipData(p0, p1, p2, clippingPlaneNormal);
        if(clipData.getAcceptedPoints()
                   .size() == 3 || clipData.getRejectedPoints()
                                           .size() == 3){
            return clipData.getAcceptedPoints();
        }
        if(clipData.getAcceptedPoints()
                   .size() == 1){
            return createTriangleFromIntersections(clippingPlaneNormal, clipData);
        }
        return createTrianglesFromIntersection(clippingPlaneNormal, clipData);
    }

    private ClipData generateClipData(Point4D p0, Point4D p1, Point4D p2, Point4D clippingPlaneNormal){
        ClipData clipData = new ClipData();
        verifyPoint(clipData, clippingPlaneNormal, p0);
        verifyPoint(clipData, clippingPlaneNormal, p1);
        verifyPoint(clipData, clippingPlaneNormal, p2);
        return clipData;
    }

    private List<Point4D> createTriangleFromIntersections(Point4D clippingPlaneNormal, ClipData clipData){
        Point4D a = clipData.getAcceptedPoints()
                            .get(0);
        clipData.addAcceptedPoint(findIntersection(a,
                                                   clipData.getRejectedPoints()
                                                           .get(0),
                                                   clippingPlaneNormal));
        clipData.addAcceptedPoint(findIntersection(a,
                                                   clipData.getRejectedPoints()
                                                           .get(1),
                                                   clippingPlaneNormal));
        return clipData.getAcceptedPoints();
    }

    private List<Point4D> createTrianglesFromIntersection(Point4D clippingPlaneNormal, ClipData clipData){
        Point4D a1 = clipData.getAcceptedPoints()
                             .get(0);
        Point4D a2 = clipData.getAcceptedPoints()
                             .get(1);
        Point4D r = clipData.getRejectedPoints()
                            .get(0);
        Point4D a2R = findIntersection(a2, r, clippingPlaneNormal);
        Point4D a1R = findIntersection(a1, r, clippingPlaneNormal);
        clipData.addAcceptedPoint(a2R);
        clipData.addAcceptedPoint(a1);
        clipData.addAcceptedPoint(a2R);
        clipData.addAcceptedPoint(a1R);
        return clipData.getAcceptedPoints();
    }

    private void verifyPoint(ClipData clipData, Point4D clippingPlaneNormal, Point4D p){
        double d = -clippingPlaneNormal.dotProduct(p) + 0.5;
        if(d >= 0){
            clipData.addAcceptedPoint(p);
        }
        else{
            clipData.addRejectedPoint(p);
        }
    }

    private Point4D findIntersection(Point4D p0, Point4D p1, Point4D n){
        Point4D p10S = p1.subtract(p0);
        double  t    = (-n.dotProduct(p0) + 0.5) / (n.dotProduct(p10S));
        return p0.add(p10S.multiply(t));
    }
}
