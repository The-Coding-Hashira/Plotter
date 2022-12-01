package bg.sofia.tu.iti.graphics.d2.world;

public class Dimension2D{
    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;
    private final double width;
    private final double height;

    public Dimension2D(double startX, double startY, double endX, double endY){
        this.startX = startX;
        this.startY = startY;
        this.endX   = endX;
        this.endY   = endY;
        width       = endX - startX + 1;
        height      = endY - startY + 1;
    }

    public boolean containsPoint(double x, double y){
        if(x >= startX && x <= endX){
            return y >= startY && y <= endY;
        }
        return false;
    }

    public double getStartX(){
        return startX;
    }

    public double getStartY(){
        return startY;
    }

    public double getEndX(){
        return endX;
    }

    public double getEndY(){
        return endY;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }
}
