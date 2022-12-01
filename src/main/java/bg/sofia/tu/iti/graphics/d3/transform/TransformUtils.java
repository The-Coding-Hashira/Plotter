package bg.sofia.tu.iti.graphics.d3.transform;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

import java.util.List;
import java.util.stream.Collectors;

public class TransformUtils{
    public static Point4D transform(Point4D point, Matrix4x4 matrix){
        return matrix.multiply(point);
    }

    public static List<Point4D> transform(List<Point4D> points, Matrix4x4 matrix){
        return points.stream()
                     .map(matrix::multiply)
                     .collect(Collectors.toList());
    }
}
