package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graph.d2.Graph2D;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorScheme;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorSchemeFactory;
import bg.sofia.tu.iti.graph.d2.plot.PlotAreaColorSchemeFactory;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.canvas.region.CanvasRegionManager;
import bg.sofia.tu.iti.gui.canvas.region.HorizontalAxisCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.PlotAreaCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.VerticalAxisCanvasRegion;
import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Plotter2D extends Plotter{
    private final Graph2D              graph;
    @FXML
    private       Button               autosizeButton;
    @FXML
    private       Button               updateGraphButton;
    @FXML
    private       TextField            yAxisLowTextField;
    @FXML
    private       TextField            yAxisHighTextField;
    @FXML
    private       TextField            xAxisLowTextField;
    @FXML
    private       TextField            xAxisHighTextField;
    @FXML
    private       Canvas               plot;
    private       PlotAreaCanvasRegion plotArea;
    private       CanvasRegionManager  canvasRegionManager;
    GraphicsContext graphicsContext;
    Dimension2D     canvasDimension;

    public Plotter2D(Function function){
        super(function);
        graph = new Graph2D(new Axis(new Range(-10, 10)), new Axis(new Range(-10, 10)), function);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.graphicsContext = plot.getGraphicsContext2D();
        this.canvasDimension = new Dimension2D(0, 0, plot.getWidth(), plot.getHeight());
        Dimension2D     plotAreaDimension = createPlotAreaDimension(canvasDimension);
        Dimension2D     xAxisDimension    = createXAxisDimension(canvasDimension);
        Dimension2D     yAxisDimension    = createYAxisDimension(canvasDimension);
        AxisColorScheme axisColorScheme   = new AxisColorSchemeFactory().createDefaultAxisColorScheme();
        int             tickLength        = 8;
        HorizontalAxisCanvasRegion xAxisCanvasRegion = new HorizontalAxisCanvasRegion(graph.getXAxis(),
                                                                                      xAxisDimension,
                                                                                      graphicsContext,
                                                                                      axisColorScheme,
                                                                                      tickLength);
        VerticalAxisCanvasRegion yAxisCanvasRegion = new VerticalAxisCanvasRegion(graph.getYAxis(),
                                                                                  yAxisDimension,
                                                                                  graphicsContext,
                                                                                  axisColorScheme,
                                                                                  tickLength);
        plotArea = new PlotAreaCanvasRegion(graph,
                                                                 plotAreaDimension,
                                                                 graphicsContext,
                                                                 new PlotAreaColorSchemeFactory().createDefaultPlotAreaColorScheme());

        canvasRegionManager = new CanvasRegionManager(Arrays.asList(plotArea, xAxisCanvasRegion, yAxisCanvasRegion));
        updateTextFields();
        paint();
    }

    public void plotOnMousePressed(MouseEvent mouseEvent){
        canvasRegionManager.onMousePressed(mouseEvent);
        mouseEvent.consume();
    }

    public void plotOnMouseDragged(MouseEvent mouseEvent){
        canvasRegionManager.onMouseDragged(mouseEvent);
        paint();
        updateTextFields();
        mouseEvent.consume();
    }

    public void plotOnMouseScrolled(ScrollEvent scrollEvent){
        canvasRegionManager.onMouseScrolled(scrollEvent);
        paint();
        updateTextFields();
        scrollEvent.consume();
    }

    private void paint(){
        //TODO remove yellow
        graphicsContext.setFill(ColorScheme.BEIGE_YELLOWISH);
        graphicsContext.fillRect(0, 0, canvasDimension.getWidth(), canvasDimension.getHeight());
        canvasRegionManager.paint();
        System.out.println("DONE - 2D");
    }

    private Dimension2D createPlotAreaDimension(Dimension2D canvasDimension){
        return new Dimension2D(50, 0, canvasDimension.getWidth(), canvasDimension.getHeight() - 25);
    }

    private Dimension2D createXAxisDimension(Dimension2D canvasDimension){
        return new Dimension2D(50,
                               canvasDimension.getHeight() - 25,
                               canvasDimension.getWidth(),
                               canvasDimension.getHeight());
    }

    private Dimension2D createYAxisDimension(Dimension2D canvasDimension){
        return new Dimension2D(0, 0, 50, canvasDimension.getHeight() - 25);
    }

    public void onAutosizeButtonClicked(ActionEvent actionEvent){
        plotArea.autosizeYAxis();
        paint();
        updateTextFields();
        actionEvent.consume();
    }

    public void onUpdateGraphButtonClicked(ActionEvent actionEvent){
//        updateAxisRange(graph.getXAxis(), xAxisLowTextField, xAxisHighTextField);
//        updateAxisRange(graph.getYAxis(), yAxisLowTextField, yAxisHighTextField);
//        paint();
//        updateTextFields();
        if(((AnonymousFunction) getFunction()).getExpression().get(0) instanceof Integral){
            System.out.println(((Integral) ((AnonymousFunction) getFunction()).getExpression()
                                                                              .get(0)).integrate().getResult());
        }
        actionEvent.consume();
    }

    private void updateTextFields(){
        updateAxisTextField(graph.getXAxis(), xAxisLowTextField, xAxisHighTextField);
        updateAxisTextField(graph.getYAxis(), yAxisLowTextField, yAxisHighTextField);
    }

    private void updateAxisTextField(Axis axis, TextField axisLow, TextField axisHigh){
        axisLow.setText(GraphUtils.toEngineeringNotation(axis.getRange()
                                                             .getLowBoundary()));
        axisHigh.setText(GraphUtils.toEngineeringNotation(axis.getRange()
                                                              .getHighBoundary()));
    }

    private void updateAxisRange(Axis axis, TextField axisLow, TextField axisHigh){
        double low  = Double.parseDouble(axisLow.getText());
        double high = Double.parseDouble(axisHigh.getText());
        if(low < high){
            axis.setRange(new Range(low, high));
        }
    }
}
