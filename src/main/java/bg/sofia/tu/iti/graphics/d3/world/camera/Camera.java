package bg.sofia.tu.iti.graphics.d3.world.camera;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.Matrix4x4;

public class Camera{
    public static Camera from(Matrix4x4 cameraTransform){
        //TODO maybe move camera far away and rotate the world to avoid vector inversion
        double[] els = cameraTransform.getElements();
        Point4D  pos = new Point4D(els[3], els[7], els[11]);
        Point4D  u   = new Point4D(els[0], els[4], els[8]);
        Point4D  v   = new Point4D(els[1], els[5], els[9]);
        Point4D  w   = new Point4D(els[2], els[6], els[10]);
        return new Camera(pos, u, v, w);
    }

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

    public Camera rotate(Matrix4x4 matrix){
        return new Camera(matrix.multiply(position), matrix.multiply(u), matrix.multiply(v), matrix.multiply(w));
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

    public Matrix4x4 getMatrix(){
        return matrix;
    }

    public Matrix4x4 createTransform(){
        return matrix.invert();
    }
}
