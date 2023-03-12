package bg.sofia.tu.iti.graph.d2.plot;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graphics.d2.painter.Painter2D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;
import java.util.function.Consumer;

public class PlotAreaPainter extends Painter2D{
    private final PlotAreaColorScheme colorScheme;
    private final Consumer<Tick>      horizontalGridLinePainter;
    private final Consumer<Tick>      verticalGridLinePainter;

    public PlotAreaPainter(GraphicsContext graphicsContext, Dimension2D dimension, PlotAreaColorScheme colorScheme){
        super(graphicsContext, dimension);
        this.colorScheme          = colorScheme;
        horizontalGridLinePainter = this::paintHorizontalGridLine;
        verticalGridLinePainter   = this::paintVerticalGridLine;
    }

    public void paint(){
        getGraphicsContext().setFill(colorScheme.getBackground());
        fill();
    }

    public void paintGridLines(List<Tick> horizontalGridLines, List<Tick> verticalGridLines){
        horizontalGridLines.forEach(verticalGridLinePainter);
        verticalGridLines.forEach(horizontalGridLinePainter);
    }

    private void paintVerticalGridLine(Tick tick){
        setUpGraphicsContext(tick.getValue());
        strokeVerticalGridLine(tick.getNormalizedValue());
    }

    private void paintHorizontalGridLine(Tick tick){
        setUpGraphicsContext(tick.getValue());
        strokeHorizontalGridLine(tick.getNormalizedValue());
    }

    private void setUpGraphicsContext(double tickValue){
        if(tickValue == 0){
            setUpOriginGridLineGraphicsContext();
        }
        else{
            setUpDefaultGridLineGraphicsContext();
        }
    }

    private void setUpOriginGridLineGraphicsContext(){
        getGraphicsContext().setStroke(colorScheme.getOriginLine());
        getGraphicsContext().setLineDashes(0);
    }

    private void setUpDefaultGridLineGraphicsContext(){
        getGraphicsContext().setStroke(colorScheme.getGridLine());
        getGraphicsContext().setLineDashes(8, 5);
    }

    private void strokeVerticalGridLine(double normalizedCoordinate){
        strokeVerticalLine(normalizedCoordinate * getDimension().getWidth(), 0, getDimension().getHeight());
    }

    private void strokeHorizontalGridLine(double normalizedCoordinate){
        strokeHorizontalLine(0,
                             getDimension().getHeight() - getDimension().getHeight() * normalizedCoordinate,
                             getDimension().getWidth());
    }
}
