package bg.sofia.tu.iti.graphics.d3.transform;

public class Matrix3x3{
    private final double e00;
    private final double e01;
    private final double e02;
    private final double e10;
    private final double e11;
    private final double e12;
    private final double e20;
    private final double e21;
    private final double e22;

    public Matrix3x3(double[] elements){
        if(elements.length != 9){
            throw new IllegalArgumentException("A 3x3 matrix must have 9 elements, got " + elements.length);
        }
        e00 = elements[0];
        e01 = elements[1];
        e02 = elements[2];
        e10 = elements[3];
        e11 = elements[4];
        e12 = elements[5];
        e20 = elements[6];
        e21 = elements[7];
        e22 = elements[8];
    }

    public double findDeterminant(){
        return (e00 * e11 * e22) + (e01 * e12 * e20) + (e02 * e10 * e21) - ((e20 * e11 * e02) + (e21 * e12 * e00) + (e22 * e10 * e01));
    }
}
