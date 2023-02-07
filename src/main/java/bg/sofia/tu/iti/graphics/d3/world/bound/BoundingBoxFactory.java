package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.geometry.Quad;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundingBoxFactory{
    public BoundingBox createBoundingBox(Point4D position, double size, Color color){
        double        offset = size / 2;
        List<Point4D> points = new ArrayList<>();
        points.addAll(createFrontPointsRightHanded(position, offset));
        points.addAll(createBackPointsRightHanded(position, offset));
        return new BoundingBox(position, size, createQuadsRightHanded(points, color));
    }

    private List<Point4D> createFrontPointsRightHanded(Point4D position, double offset){
        return Arrays.asList(new Point4D(position.getX() - offset, position.getY() - offset, position.getZ() - offset),
                             new Point4D(position.getX() + offset, position.getY() - offset, position.getZ() - offset),
                             new Point4D(position.getX() + offset, position.getY() - offset, position.getZ() + offset),
                             new Point4D(position.getX() - offset, position.getY() - offset, position.getZ() + offset));
    }

    private List<Point4D> createBackPointsRightHanded(Point4D position, double offset){
        return Arrays.asList(new Point4D(position.getX() + offset, position.getY() + offset, position.getZ() - offset),
                             new Point4D(position.getX() - offset, position.getY() + offset, position.getZ() - offset),
                             new Point4D(position.getX() - offset, position.getY() + offset, position.getZ() + offset),
                             new Point4D(position.getX() + offset, position.getY() + offset, position.getZ() + offset));
    }

    private List<Quad> createQuadsRightHanded(List<Point4D> points, Color color){
        return Arrays.asList(createFrontQuad(points, color),
                             createRightQuad(points, color),
                             createBackQuad(points, color),
                             createLeftQuad(points, color),
                             createTopQuad(points, color),
                             createBottomQuad(points, color));
    }

    private Quad createFrontQuad(List<Point4D> points, Color color){
        return new Quad(points.get(0), points.get(1), points.get(2), points.get(3), color);
    }

    private Quad createRightQuad(List<Point4D> points, Color color){
        return new Quad(points.get(1), points.get(4), points.get(7), points.get(2), color);
    }

    private Quad createBackQuad(List<Point4D> points, Color color){
        return new Quad(points.get(4), points.get(5), points.get(6), points.get(7), color);
    }

    private Quad createLeftQuad(List<Point4D> points, Color color){
        return new Quad(points.get(5), points.get(0), points.get(3), points.get(6), color);
    }

    private Quad createTopQuad(List<Point4D> points, Color color){
        return new Quad(points.get(3), points.get(2), points.get(7), points.get(6), color);
    }

    private Quad createBottomQuad(List<Point4D> points, Color color){
        return new Quad(points.get(5), points.get(4), points.get(1), points.get(0), color);
    }
}
