package bg.sofia.tu.iti.graphics.d3.geometry;

import javafx.scene.paint.Color;

public class Point4D{
    private final double x;
    private final double y;
    private final double z;
    private final double w;
    private final Color  color;

    public Point4D(double x, double y, double z){
        this(x, y, z, 1);
    }

    public Point4D(double x, double y, double z, double w){
        this(x, y, z, w, Color.TRANSPARENT);
    }

    public Point4D(double x, double y, double z, double w, Color color){
        this.x     = x;
        this.y     = y;
        this.z     = z;
        this.w     = w;
        this.color = color;
    }

    public Point4D(double x, double y, double z, Color color){
        this(x, y, z, 1, color);
    }

    public Point4D crossProduct(Point4D point){
        final double px = point.x;
        final double py = point.y;
        final double pz = point.z;
        return new Point4D(y * pz - z * py, z * px - x * pz, x * py - y * px, w, point.color);
    }

    public Point4D normalize(){
        final double magnitude = magnitude();
        return new Point4D(x / magnitude, y / magnitude, z / magnitude, w, color);
    }

    public double magnitude(){
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Point4D negate(){
        return new Point4D(-x, -y, -z, w, color);
    }

    public Point4D multiply(double scalar){
        return new Point4D(x * scalar, y * scalar, z * scalar, w, color);
    }

    public Point4D add(Point4D point){
        return new Point4D(x + point.x, y + point.y, z + point.z, w, color);
    }

    public Point4D subtract(Point4D point){
        return new Point4D(x - point.x, y - point.y, z - point.z, w, color);
    }

    public double dotProduct(Point4D point){
        return dotProduct(point.x, point.y, point.z);
    }

    public double dotProduct(double x, double y, double z){
        return this.x * x + this.y * y + this.z * z;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public double getW(){
        return w;
    }

    public Color getColor(){
        return color;
    }

    public boolean equals(Point4D point){
        return x == point.x && y == point.y && z == point.z && w == point.w;
    }

    @Override
    public String toString(){
        return "Point4D{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
}
