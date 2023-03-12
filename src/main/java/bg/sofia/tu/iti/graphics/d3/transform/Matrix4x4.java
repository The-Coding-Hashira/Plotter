package bg.sofia.tu.iti.graphics.d3.transform;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

public class Matrix4x4{
    private final double[] elements;

    private final double e00;
    private final double e01;
    private final double e02;
    private final double e03;
    private final double e10;
    private final double e11;
    private final double e12;
    private final double e13;
    private final double e20;
    private final double e21;
    private final double e22;
    private final double e23;
    private final double e30;
    private final double e31;
    private final double e32;
    private final double e33;

    public Matrix4x4(double[] elements){
        if(elements.length != 16){
            throw new IllegalArgumentException("A 4x4 matrix must have 16 elements, got " + elements.length);
        }
        this.elements = elements;
        e00           = elements[0];
        e01           = elements[1];
        e02           = elements[2];
        e03           = elements[3];
        e10           = elements[4];
        e11           = elements[5];
        e12           = elements[6];
        e13           = elements[7];
        e20           = elements[8];
        e21           = elements[9];
        e22           = elements[10];
        e23           = elements[11];
        e30           = elements[12];
        e31           = elements[13];
        e32           = elements[14];
        e33           = elements[15];
    }

    public static Matrix4x4 generateIdentity(){
        //@formatter:off
        return new Matrix4x4(new double[]{1, 0, 0, 0,
                                          0, 1, 0, 0,
                                          0, 0, 1, 0,
                                          0, 0, 0, 1});
        //@formatter:on
    }

    public Matrix4x4 multiply(Matrix4x4 matrix){
        double[] elements = new double[16];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    elements[j + (i << 2)] += get(i, k) * matrix.get(k, j);
                }
            }
        }
        return new Matrix4x4(elements);
    }

    private double get(int row, int column){
        return elements[column + (row << 2)];
    }

    public Point4D multiply(Point4D point){
        final double px = point.getX();
        final double py = point.getY();
        final double pz = point.getZ();
        final double pw = point.getW();

        double x = (px * e00) + (py * e01) + (pz * e02) + (pw * e03);
        double y = (px * e10) + (py * e11) + (pz * e12) + (pw * e13);
        double z = (px * e20) + (py * e21) + (pz * e22) + (pw * e23);
        double w = (px * e30) + (py * e31) + (pz * e32) + (pw * e33);
        return new Point4D(x, y, z, w, point.getColor());
    }

    public Matrix4x4 invert(){
        Matrix4x4 transposedCofactorMatrix = transpose().findCofactorMatrix();
        int       k                        = 0;
        double    determinant              = 0;
        for(int j = 0; j < 4; j++){
            determinant += get(k, j) * transposedCofactorMatrix.get(j, k);
        }
        return transposedCofactorMatrix.divide(determinant);
    }

    public Matrix4x4 transpose(){
        double[] transposedMatrix = new double[16];
        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                transposedMatrix[column + (row << 2)] = get(column, row);
            }
        }
        return new Matrix4x4(transposedMatrix);
    }

    private Matrix4x4 findCofactorMatrix(){
        double[] cofactorMatrix = new double[16];
        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                double cofactor = extractSubMatrix(row, column).findDeterminant();
                if((row + column) % 2 != 0){
                    cofactor *= -1;
                }
                cofactorMatrix[column + (row << 2)] = cofactor;
            }
        }
        return new Matrix4x4(cofactorMatrix);
    }

    private Matrix4x4 divide(double scalar){
        double[] result = new double[16];
        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                result[column + (row << 2)] = get(row, column) / scalar;
            }
        }
        return new Matrix4x4(result);
    }

    private Matrix3x3 extractSubMatrix(int row, int column){
        double[] subMatrix = new double[9];
        for(int i = 0; i < 4; i++){
            if(i != row){
                int rowNumber = i > row ? i - 1 : i;
                for(int j = 0; j < 4; j++){
                    if(j != column){
                        int columnNumber = j > column ? j - 1 : j;
                        subMatrix[columnNumber + rowNumber * 3] = get(i, j);
                    }
                }
            }
        }
        return new Matrix3x3(subMatrix);
    }

    public double[] getElements(){
        return elements;
    }

    //TODO remove description methods
    @Override
    public String toString(){
        return "Matrix4x4:\n" + generateContentsDescription();
    }

    private String generateContentsDescription(){
        StringBuilder contentsDescription = new StringBuilder();
        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                contentsDescription.append(get(row, column));
                contentsDescription.append(" ");
            }
            contentsDescription.append("\n");
        }
        return contentsDescription.toString();
    }
}
