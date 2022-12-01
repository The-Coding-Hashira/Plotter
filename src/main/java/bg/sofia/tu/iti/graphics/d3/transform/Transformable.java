package bg.sofia.tu.iti.graphics.d3.transform;

public interface Transformable<T>{
    T transform(Matrix4x4 matrix);
}
