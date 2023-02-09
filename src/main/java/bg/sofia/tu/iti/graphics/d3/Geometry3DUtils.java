package bg.sofia.tu.iti.graphics.d3;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.geometry.TriangleConsumer;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public class Geometry3DUtils{
    public static List<Point4D> sortToTriangles(List<Point4D> gridDataPoints){
        List<Point4D> sortedPoints   = new ArrayList<>(gridDataPoints.size());
        int           squareGridSize = (int) Math.sqrt(gridDataPoints.size());
        int           iterableSize   = gridDataPoints.size() - squareGridSize;
        int           p0;
        int           p1;
        int           p2;
        for(int i = 0; i < iterableSize; i += squareGridSize){
            for(int k = 0; k < squareGridSize - 1; k++){
                p0 = i + k;
                p1 = p0 + 1;
                p2 = p1 + squareGridSize;
                sortedPoints.add(gridDataPoints.get(p0));
                sortedPoints.add(gridDataPoints.get(p1));
                sortedPoints.add(gridDataPoints.get(p2));

                p0 = i + k;
                p1 = p0 + 1 + squareGridSize;
                p2 = p1 - 1;
                sortedPoints.add(gridDataPoints.get(p0));
                sortedPoints.add(gridDataPoints.get(p1));
                sortedPoints.add(gridDataPoints.get(p2));
            }
        }
        return sortedPoints;
    }

    public static void iterateTriangles(List<Point4D> points, TriangleConsumer triangleConsumer){
        int pointsSize   = points.size();
        int pointCounter = 1;
        for(int i = 0; i < pointsSize; i++, pointCounter++){
            if(pointCounter == 3){
                triangleConsumer.accept(points.get(i - 2), points.get(i - 1), points.get(i));
                pointCounter = 0;
            }
        }
    }

    public static void iterateTrianglesAsGrid(List<Point4D> points, LineConsumer lineConsumer){
        //TODO check handedness of clipped triangles, so that when iterate as grid it works better
        final int[] lowerTriangle = {1};
        iterateTriangles(points, (p0, p1, p2) -> {
            if(lowerTriangle[0] == 1){
                lineConsumer.accept(p0, p1);
                lineConsumer.accept(p1, p2);
            }
            else{
                lineConsumer.accept(p1, p2);
                lineConsumer.accept(p2, p0);
            }
            lowerTriangle[0] *= -1;
        });
    }

    public static int compareFarthestDistanceToCamera(Point4D pointer0, Point4D pointer1){
        return -compareSmallestDistanceToCamera(pointer0, pointer1);
    }

    public static int compareSmallestDistanceToCamera(Point4D pointer0, Point4D pointer1){
        double mag0 = pointer0.magnitude();
        double mag1 = pointer1.magnitude();
        if(mag0 < mag1){
            return 1;
        }
        else if(mag0 > mag1){
            return -1;
        }
        return 0;
    }

    public static int compareLeftOfCamera(Point4D p0, Point4D p1, Camera camera){
        double dot0 = camera.getU()
                            .dotProduct(p0);
        double dot1 = camera.getU()
                            .dotProduct(p1);
        if(dot0 < dot1){
            return 1;
        }
        else if(dot0 > dot1){
            return -1;
        }
        return 0;
    }

    public static int compareRightOfCamera(Point4D p0, Point4D p1, Camera camera){
        double dot0 = camera.getU()
                            .dotProduct(p0);
        double dot1 = camera.getU()
                            .dotProduct(p1);
        if(dot0 > dot1){
            return 1;
        }
        else if(dot0 < dot1){
            return -1;
        }
        return 0;
    }
}
