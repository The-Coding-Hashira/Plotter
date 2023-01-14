package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.Rasterizable;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.geometry.Quad;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;
import bg.sofia.tu.iti.graphics.d3.transform.Transformable;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public class BoundingBox implements Transformable<BoundingBox>,
                                    Rasterizable{
    private final Point4D    position;
    private final double     size;
    private final List<Quad> quads;
    private final List<Quad> sideQuads;
    private final Quad       topPlane;
    private final Quad       bottomPlane;

    public BoundingBox(Point4D position, double size, List<Quad> quads){
        this.position = position;
        this.size     = size;
        this.quads    = quads;
        sideQuads     = new ArrayList<>();
        sideQuads.add(quads.get(0));
        sideQuads.add(quads.get(1));
        sideQuads.add(quads.get(2));
        sideQuads.add(quads.get(3));
        topPlane    = quads.get(4);
        bottomPlane = quads.get(5);
    }

    @Override
    public BoundingBox transform(Matrix4x4 matrix){
        List<Quad> transformedQuads = new ArrayList<>(6);
        quads.forEach(quad -> transformedQuads.add(quad.transform(matrix)));
        return new BoundingBox(position, size, transformedQuads);
    }

    @Override
    public void rasterize(GraphicsContext3D rasterizer){
        quads.forEach(quad -> quad.rasterize(rasterizer));
    }

    public void rasterizeOccluded(GraphicsContext3D graphicsContext3D, Camera camera){
        sideQuads.forEach(quad -> {
            double d = quad.getNormal()
                           .dotProduct(camera.getW());
            if(d >= 0){
                quad.rasterize(graphicsContext3D);
            }
        });
    }

    public Point4D getPosition(){
        return position;
    }

    public double getSize(){
        return size;
    }

    public List<Quad> getSideQuads(){
        return sideQuads;
    }

    public List<Quad> getQuads(){
        return quads;
    }

    public Quad getTopPlane(){
        return topPlane;
    }

    public Quad getBottomPlane(){
        return bottomPlane;
    }
}
