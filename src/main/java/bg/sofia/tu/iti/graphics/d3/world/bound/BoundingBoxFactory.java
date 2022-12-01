package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.geometry.Quad;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BoundingBoxFactory{
    public BoundingBox createBoundingBox(Point4D position, double size, Color color){
        double        offset = size / 2;
        List<Point4D> points = new ArrayList<>();
        points.addAll(createFrontPointsRightHanded(position, offset));
        points.addAll(createBackPointsRightHanded(position, offset));
        List<Quad> quads = createQuads(points, color);
        return new BoundingBox(position, size, quads);
    }

    private List<Point4D> createFrontPointsRightHanded(Point4D position, double offset){
        List<Point4D> points = new ArrayList<>();
        points.add(new Point4D(position.getX() - offset, position.getY() - offset, position.getZ() - offset));
        points.add(new Point4D(position.getX() + offset, position.getY() - offset, position.getZ() - offset));
        points.add(new Point4D(position.getX() + offset, position.getY() - offset, position.getZ() + offset));
        points.add(new Point4D(position.getX() - offset, position.getY() - offset, position.getZ() + offset));
        return points;
    }

    private List<Point4D> createBackPointsRightHanded(Point4D position, double offset){
        List<Point4D> points = new ArrayList<>();
        points.add(new Point4D(position.getX() + offset, position.getY() + offset, position.getZ() - offset));
        points.add(new Point4D(position.getX() - offset, position.getY() + offset, position.getZ() - offset));
        points.add(new Point4D(position.getX() - offset, position.getY() + offset, position.getZ() + offset));
        points.add(new Point4D(position.getX() + offset, position.getY() + offset, position.getZ() + offset));
        return points;
    }

    private List<Quad> createQuads(List<Point4D> points, Color color){
        List<Quad> quads = new ArrayList<>(6);
        quads.add(createFrontQuad(points, color));
        quads.add(createRightQuad(points, color));
        quads.add(createBackQuad(points, color));
        quads.add(createLeftQuad(points, color));
        quads.add(createTopQuadRightHanded(points, color));
        quads.add(createBottomQuadRightHanded(points, color));
        return quads;
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

    private Quad createTopQuadRightHanded(List<Point4D> points, Color color){
        return new Quad(points.get(3), points.get(2), points.get(7), points.get(6), color);
    }

    private Quad createBottomQuadRightHanded(List<Point4D> points, Color color){
        return new Quad(points.get(0), points.get(5), points.get(4), points.get(1), color);
    }
}
