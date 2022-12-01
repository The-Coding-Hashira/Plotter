package bg.sofia.tu.iti.gui.canvas.manager;

import bg.sofia.tu.iti.graph.d2.axis.AxisColorScheme;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorSchemeFactory;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaColorSchemeFactory;
import bg.sofia.tu.iti.graph.d2.Graph2D;
import bg.sofia.tu.iti.gui.canvas.region.HorizontalAxisCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.PlotAreaCanvasRegion;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.canvas.region.CanvasRegionManager;
import bg.sofia.tu.iti.gui.canvas.region.VerticalAxisCanvasRegion;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Plot2DCanvasManager implements CanvasManager{
    private final CanvasRegionManager canvasRegionManager;
    private final Graph2D             graph;
    GraphicsContext graphicsContext;
    Dimension2D     canvasDimension;

    public Plot2DCanvasManager(Dimension2D canvasDimension, GraphicsContext graphicsContext, Function function){
        this.graphicsContext = graphicsContext;
        this.canvasDimension = canvasDimension;
        double          padding           = 25;
        Dimension2D     plotAreaDimension = createPlotAreaDimension(canvasDimension, padding);
        Dimension2D     xAxisDimension    = createXAxisDimension(canvasDimension, padding);
        Dimension2D     yAxisDimension    = createYAxisDimension(canvasDimension, padding);
        AxisColorScheme axisColorScheme   = new AxisColorSchemeFactory().createDefaultAxisColorScheme();
        int             tickLength        = 8;
        HorizontalAxisCanvasRegion xAxisCanvasRegion = new HorizontalAxisCanvasRegion(xAxisDimension, graphicsContext
                , axisColorScheme, tickLength);
        VerticalAxisCanvasRegion yAxisCanvasRegion = new VerticalAxisCanvasRegion(yAxisDimension, graphicsContext,
                                                                                  axisColorScheme, tickLength);
        PlotAreaCanvasRegion plotArea = new PlotAreaCanvasRegion(plotAreaDimension, graphicsContext,
                                                                 new PlotAreaColorSchemeFactory().createDefaultPlotAreaColorScheme());

        canvasRegionManager = new CanvasRegionManager(Arrays.asList(plotArea, xAxisCanvasRegion, yAxisCanvasRegion));
        graph               = new Graph2D(xAxisCanvasRegion, yAxisCanvasRegion, plotArea, function);
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        canvasRegionManager.onMousePressed(mouseEvent);
        paint();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        canvasRegionManager.onMouseDragged(mouseEvent);
        paint();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        canvasRegionManager.onMouseScrolled(scrollEvent);
        paint();
    }

    @Override
    public void paint(){
        //TODO remove yellow
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(0, 0, canvasDimension.getWidth(), canvasDimension.getHeight());
        canvasRegionManager.paint();
        graph.paint();
    }

    private Dimension2D createXAxisDimension(Dimension2D canvasDimension, double padding){
        return new Dimension2D(padding, canvasDimension.getHeight() - padding, canvasDimension.getWidth(),
                               canvasDimension.getHeight());
    }

    private Dimension2D createYAxisDimension(Dimension2D canvasDimension, double padding){
        return new Dimension2D(0, 0, padding, canvasDimension.getHeight() - padding);
    }

    private Dimension2D createPlotAreaDimension(Dimension2D canvasDimension, double padding){
        return new Dimension2D(padding, 0, canvasDimension.getWidth(), canvasDimension.getHeight() - padding);
    }
    //    @Override
    //    public void movedHorizontally(double pixels){
    //        xAxisCanvasRegion.moveRange(pixels);
    //    }
    //
    //    @Override
    //    public void movedVertically(double pixels){
    //        yAxisCanvasRegion.moveRange(pixels);
    //    }
    //
    //    @Override
    //    public void scrolled(ScrollEvent scrollEvent){
    //        xAxisCanvasRegion.onMouseScrolled(scrollEvent);
    //        yAxisCanvasRegion.onMouseScrolled(scrollEvent);
    //    }
    //
    //    public void horizontalAxisRangeChanged(Range range){
    //        plotArea.horizontalAxisRangeChanged(range, yAxisCanvasRegion.getRange());
    //    }
    //
    //    public void verticalAxisRangeChanged(Range range){
    //        plotArea.verticalAxisRangeChanged(xAxisCanvasRegion.getRange(), range);
    //    }

    //    public void setGraph(GraphFactory graphFactory){
    //        plotArea.setGraph(graphFactory);
    //    }

    //    public void setGraph(GraphFactory graphFactory){
    //        plotCanvasRegionManager.setGraph(graphFactory);
    //    }

    //TODO this exists until a button is added
    //        private void fillBackground(){
    //            graphicsContext.setFill(Color.AQUA);
    //            graphicsContext.fillRect(0, 0, canvasDimension.getWidth(), canvasDimension.getHeight());
    //        }
}
