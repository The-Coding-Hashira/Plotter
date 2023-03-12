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

    public void strokePathForYCoordinates(List<Double> yCoordinates){
        double startX = dimension.getStartX();
        double startY = dimension.getStartY();

        graphicsContext.beginPath();
        graphicsContext.moveTo(startX, startY + yCoordinates.get(0));
        for(int i = 0, size = yCoordinates.size(); i < size; i++){
            graphicsContext.lineTo(startX + i, startY + yCoordinates.get(i));
        }
        graphicsContext.stroke();
    }

    public void strokePath(List<Point2D> points){
        double startX = dimension.getStartX();
        double endY   = dimension.getEndY();

        //TODO create proper path drawing 1/sin(x) is benchmark
        //        points.forEach(point -> {
        //            graphicsContext.beginPath();
        //            graphicsContext.moveTo(startX + point.getX() * getDimension().getWidth(),
        //                                   endY - point.getY() * getDimension().getHeight());
        //            graphicsContext.lineTo(startX + point.getX() * getDimension().getWidth(),
        //                                   endY - point.getY() * getDimension().getHeight());
        //            graphicsContext.stroke();
        //        });

        graphicsContext.beginPath();
        graphicsContext.moveTo(startX + points.get(0)
                                              .getX() * getDimension().getWidth(),
                               endY - points.get(0)
                                            .getY() * getDimension().getHeight());
        points.forEach(point -> graphicsContext.lineTo(startX + point.getX() * getDimension().getWidth(),
                                                       endY - point.getY() * getDimension().getHeight()));
        graphicsContext.stroke();
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
