package bg.sofia.tu.iti.graphics.d3.geometry;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.Rasterizable;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import bg.sofia.tu.iti.graphics.d3.transform.TransformUtils;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Quad implements Rasterizable{
    private final List<Point4D> points;
    private final Color         color;

    public Quad(Point4D a, Point4D b, Point4D c, Point4D d, Color color){
        points = new ArrayList<>(4);
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        this.color = color;
    }

    @Override
    public void rasterize(GraphicsContext3D graphicsContext3D){
        int colorValue = graphicsContext3D.toInt(color);
        for(int i = 0; i < points.size() - 1; i++){
            graphicsContext3D.drawLine(points.get(i), points.get(i + 1), colorValue);
        }
        graphicsContext3D.drawLine(points.get(3), points.get(0), colorValue);
    }

    public Quad transform(Matrix4x4 matrix){
        List<Point4D> transformedPoints = TransformUtils.transform(points, matrix);
        return new Quad(transformedPoints.get(0),
                        transformedPoints.get(1),
                        transformedPoints.get(2),
                        transformedPoints.get(3),
                        color);
    }

    public Point4D getNormal(){
        Point4D line1 = points.get(1)
                              .subtract(points.get(0));
        Point4D line2 = points.get(3)
                              .subtract(points.get(0));
        return line1.crossProduct(line2).normalize();
    }

    public List<Point4D> getPoints(){
        return points;
    }

    public Color getColor(){
        return color;
    }
}
