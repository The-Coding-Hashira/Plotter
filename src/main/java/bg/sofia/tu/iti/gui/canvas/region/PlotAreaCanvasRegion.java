package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaColorScheme;
import bg.sofia.tu.iti.gui.event.PlotAreaEventHandler;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaPainter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class PlotAreaCanvasRegion implements CanvasRegion{
    private final PlotAreaPainter      plotAreaPainter;
    private final PlotAreaEventHandler plotAreaEventHandler;

    public PlotAreaCanvasRegion(Dimension2D dimension2D, GraphicsContext graphicsContext,
                                PlotAreaColorScheme colorScheme){
        this.plotAreaPainter      = new PlotAreaPainter(graphicsContext, dimension2D, colorScheme);
        this.plotAreaEventHandler = new PlotAreaEventHandler();
    }

    @Override
    public boolean containsPoint(double x, double y){
        return plotAreaPainter.getDimension()
                              .containsPoint(x, y);
    }

    @Override
    public void paint(){
        plotAreaPainter.paint();
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

    public PlotAreaPainter getPlotAreaPainter(){
        return plotAreaPainter;
    }
}
