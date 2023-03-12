package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.tick.TickGenerator;
import bg.sofia.tu.iti.graph.d2.Graph2D;
import bg.sofia.tu.iti.graph.d2.painter.Graph2DPainter;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaColorScheme;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaPainter;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.PlaneEventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

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
        graph2DPainter.paintPath(graph2D.normalizeDataPoints());
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
