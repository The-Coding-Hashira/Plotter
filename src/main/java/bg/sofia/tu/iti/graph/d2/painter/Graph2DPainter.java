package bg.sofia.tu.iti.graph.d2.painter;

import bg.sofia.tu.iti.graphics.d2.geometry.Point2D;
import bg.sofia.tu.iti.graphics.d2.painter.Painter2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Graph2DPainter extends Painter2D{
    public Graph2DPainter(Painter2D plotAreaPainter){
        super(plotAreaPainter.getGraphicsContext(), plotAreaPainter.getDimension());
    }

    public void paintLineForCoordinates(List<Double> yCoordinates){
        getGraphicsContext().setStroke(Color.DODGERBLUE);
        getGraphicsContext().setLineDashes(0);
        strokePathForYCoordinates(yCoordinates);
    }

    public void paintPath(List<Point2D> points){
        if(points.isEmpty()){
            return;
        }
        getGraphicsContext().setStroke(Color.DODGERBLUE);
        getGraphicsContext().setLineDashes(0);
        strokePath(points);
    }

    public void paintArea(List<Point2D> points){
        Color           lineColor       = Color.DODGERBLUE;
        GraphicsContext graphicsContext = getGraphicsContext();
        graphicsContext.setStroke(lineColor);
        graphicsContext.setLineDashes(0);
        strokePath(points);

        graphicsContext.setFill(Color.color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), 0.13));
        graphicsContext.fill();
    }
}
