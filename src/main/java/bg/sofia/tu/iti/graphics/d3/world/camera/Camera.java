package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;

public class Camera{
    private final Point4D   position;
    private final Point4D   u;
    private final Point4D   v;
    private final Point4D   w;
    private final Matrix4x4 matrix;

    public Camera(Point4D position, Point4D u, Point4D v, Point4D w){
        this.position = position;
        this.u        = u;
        this.v        = v;
        this.w        = w;
        //@formatter:off
        matrix        = new Matrix4x4(new double[]{u.getX(), v.getX(), w.getX(), position.getX(),
                                                   u.getY(), v.getY(), w.getY(), position.getY(),
                                                   u.getZ(), v.getZ(), w.getZ(), position.getZ(),
                                                   0, 0, 0, 1});
        //@formatter:on
    }

    public Camera(Point4D position, Point4D lookAt, Point4D up){
        this.position = position;

        w = lookAt.subtract(position)
                  .negate()
                  .normalize();
        u = up.crossProduct(w)
              .normalize();
        v = w.crossProduct(u)
             .normalize();

        //@formatter:off
        matrix = new Matrix4x4(new double[]{u.getX(), v.getX(), w.getX(), position.getX(),
                                            u.getY(), v.getY(), w.getY(), position.getY(),
                                            u.getZ(), v.getZ(), w.getZ(), position.getZ(),
                                            0, 0, 0, 1});
        //@formatter:on
    }

    public Point4D getPosition(){
        return position;
    }

    public Point4D getU(){
        return u;
    }

    public Point4D getV(){
        return v;
    }

    public Point4D getW(){
        return w;
    }

    public Matrix4x4 createTransform(){
        return matrix.invert();
    }

    @Override
    public String toString(){
        return "Camera{" + "position=" + position + ", u=" + u + ", v=" + v + ", w=" + w + '}';
    }
}
