package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.tick.TickGenerator;
import bg.sofia.tu.iti.graph.d2.Graph2D;
import bg.sofia.tu.iti.graph.d2.painter.Graph2DPainter;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaColorScheme;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaPainter;
import bg.sofia.tu.iti.graphics.d2.geometry.Point2D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.PlaneEventHandler;
import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Integral;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.List;
import java.util.stream.Collectors;

public class PlotAreaCanvasRegion implements CanvasRegion{
    private final PlotAreaPainter   plotAreaPainter;
    private final PlaneEventHandler plotAreaEventHandler;
    private final Graph2D           graph2D;
    private final Graph2DPainter    graph2DPainter;

    public PlotAreaCanvasRegion(Graph2D graph2D, Dimension2D dimension2D, GraphicsContext graphicsContext,
                                PlotAreaColorScheme colorScheme){
        this.graph2D              = graph2D;
        this.plotAreaPainter      = new PlotAreaPainter(graphicsContext, dimension2D, colorScheme);
        graph2DPainter            = new Graph2DPainter(plotAreaPainter);
        this.plotAreaEventHandler = new PlaneEventHandler(graph2D.getXAxis(), graph2D.getYAxis(), dimension2D);
    }

    @Override
    public boolean containsPoint(double x, double y){
        return plotAreaPainter.getDimension()
                              .containsPoint(x, y);
    }

    @Override
    public void paint(){
        plotAreaPainter.paint();
        plotAreaPainter.paintGridLines(graph2D.getXAxis()
                                              .generateTicks(new TickGenerator(10)),
                                       graph2D.getYAxis()
                                              .generateTicks(new TickGenerator(10)));
        graph2D.calculateData(plotAreaPainter.getDimension()
                                             .getWidth());
        List<Point2D> points = graph2D.normalizeDataPoints()
                                      .stream()
                                      .filter(point2D -> !Double.isNaN(point2D.getY()))
                                      .collect(Collectors.toList());
        graph2DPainter.paintPath(points);
        if(((AnonymousFunction) graph2D.getFunction()).getExpression().get(0) instanceof Integral){
            graph2DPainter.paintArea(points,graph2D.getYAxis().getRange());
        }
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        plotAreaEventHandler.onMousePressed(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        plotAreaEventHandler.onMouseDragged(mouseEvent);
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        plotAreaEventHandler.onMouseScrolled(scrollEvent);
    }

    public void autosizeYAxis(){
        graph2D.autosizeYAxis(plotAreaPainter.getDimension()
                                             .getWidth());
    }
}
