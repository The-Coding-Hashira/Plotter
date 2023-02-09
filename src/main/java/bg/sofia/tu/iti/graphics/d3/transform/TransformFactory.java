package bg.sofia.tu.iti.graphics.d3.transform;

public class TransformFactory{
    public Matrix4x4 createRotationX(double rad){
        //@formatter:off
        return new Matrix4x4(new double[]{1, 0, 0, 0,
                                          0, Math.cos(rad), -Math.sin(rad), 0,
                                          0, Math.sin(rad), Math.cos(rad), 0,
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 createRotationY(double rad){
        //@formatter:off
        return new Matrix4x4(new double[]{Math.cos(rad), 0, Math.sin(rad), 0,
                                          0, 1, 0, 0,
                                          -Math.sin(rad), 0, Math.cos(rad), 0,
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 createRotationZ(double rad){
        //@formatter:off
        return new Matrix4x4(new double[]{Math.cos(rad), -Math.sin(rad), 0, 0,
                                          Math.sin(rad), Math.cos(rad), 0,0,
                                          0, 0, 1, 0,
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 createViewport(double width, double height){
        //@formatter:off
        //TODO check if z should be 0.5 0.5
        return new Matrix4x4(new double[]{width / 2, 0, 0, width / 2,
                                          0, -height / 2, 0, height / 2,
                                          0, 0, 1, 0,
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 createOrthographicProjection(double size, double aspectRatio){
        double f = size * 2;
        double n = 0;
        double r = aspectRatio * size;
        double l = -r;
        double t = size;
        double b = -t;
        //@formatter:off
        return new Matrix4x4(new double[]{2 / (r - l), 0, 0, -((r + l) / (r - l)),
                                          0, 2 / (t - b), 0, -((t + b) / (t - b)),
                                          0, 0, -(2 / (f - n)), -((f + n) / (f - n)),
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 createPerspectiveProjection(double fovDeg, double aspectRatio, double near, double far){
        near = Math.abs(near);
        far  = Math.abs(far);
        double t = Math.tan(Math.toRadians(fovDeg) / 2) * near;
        double r = t * aspectRatio;
        double b = -t;
        double l = -r;
        //@formatter:off
        return new Matrix4x4(new double[]{(2*near)/(r-l), 0, (r+l)/(r-l), 0,
                                          0, (2*near)/(t-b), (t+b)/(t-b), 0,
                                          0, 0, (near+far)/(near-far), (2*far*near)/(near-far),
                                          0, 0, -1, 0});
        //@formatter:on
    }
}
