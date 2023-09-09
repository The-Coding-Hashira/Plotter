package bg.sofia.tu.iti.graphics.d2.painter;

import bg.sofia.tu.iti.graphics.d2.geometry.Point2D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Painter2D{
    private final GraphicsContext graphicsContext;
    private final Dimension2D     dimension;

    public Painter2D(GraphicsContext graphicsContext, Dimension2D dimension){
        this.graphicsContext = graphicsContext;
        this.dimension       = dimension;
    }

    public void strokePath(List<Point2D> points){
        walkPath(points);
        graphicsContext.stroke();
    }

    public void walkPath(List<Point2D> points){
        double startX = dimension.getStartX();
        double endY   = dimension.getEndY();

        graphicsContext.beginPath();
        graphicsContext.moveTo(startX + points.get(0)
                                              .getX() * getDimension().getWidth(),
                               endY - points.get(0)
                                            .getY() * getDimension().getHeight());
        points.forEach(point -> graphicsContext.lineTo(startX + point.getX() * getDimension().getWidth(),
                                                       endY - point.getY() * getDimension().getHeight()));
    }

    public void strokeVerticalLine(double x, double y, double length){
        graphicsContext.strokeLine(dimension.getStartX() + x,
                                   dimension.getStartY() + y,
                                   dimension.getStartX() + x,
                                   dimension.getStartY() + y + length);
    }

    public void strokeHorizontalLine(double x, double y, double length){
        graphicsContext.strokeLine(dimension.getStartX() + x,
                                   dimension.getStartY() + y,
                                   dimension.getStartX() + x + length,
                                   dimension.getStartY() + y);
    }

    public void fill(){
        fillArea(0, 0, dimension.getWidth(), dimension.getHeight());
    }

    public void fillArea(double x, double y, double w, double h){
        graphicsContext.fillRect(dimension.getStartX() + x, dimension.getStartY() + y, w, h);
    }

    public void fillText(String value, double x, double y){
        graphicsContext.fillText(value, dimension.getStartX() + x, dimension.getStartY() + y);
    }

    public void clear(){
        graphicsContext.clearRect(dimension.getStartX(),
                                  dimension.getStartY(),
                                  dimension.getWidth(),
                                  dimension.getHeight());
    }

    public GraphicsContext getGraphicsContext(){
        return graphicsContext;
    }

    public Dimension2D getDimension(){
        return dimension;
    }
}
