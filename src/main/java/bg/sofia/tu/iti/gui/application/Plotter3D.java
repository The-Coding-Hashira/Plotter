package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graph.d3.Graph3D;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.canvas.manager.Plot3DCanvasManager;
import bg.sofia.tu.iti.math.function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Plotter3D extends Plotter{
    private final Graph3D             graph3D;
    @FXML
    private       Canvas              plot;
    @FXML
    private       Button              autosizeButton;
    @FXML
    private       Button              updateGraphButton;
    @FXML
    private       TextField           zAxisLowTextField;
    @FXML
    private       TextField           zAxisHighTextField;
    @FXML
    private       TextField           xAxisLowTextField;
    @FXML
    private       TextField           xAxisHighTextField;
    @FXML
    private       TextField           yAxisLowTextField;
    @FXML
    private       TextField           yAxisHighTextField;
    @FXML
    private       TextField           gridResolutionTextField;
    private       Plot3DCanvasManager canvasManager;

    public Plotter3D(Function function){
        super(function);
        graph3D = new Graph3D(function);
        //TODO add heatmap visual
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        canvasManager = new Plot3DCanvasManager(graph3D,
                                                new Dimension2D(0, 0, plot.getWidth(), plot.getHeight()),
                                                plot.getGraphicsContext2D());
        canvasManager.paint();
        gridResolutionTextField.setText(Integer.toString(canvasManager.getGridResolution()));
        updateTextFields();
    }

    public void onMousePressedOnPlot(MouseEvent mouseEvent){
        canvasManager.onMousePressed(mouseEvent);
        mouseEvent.consume();
    }

    public void onMouseDraggedOnPlot(MouseEvent mouseEvent){
        canvasManager.onMouseDragged(mouseEvent);
        updateTextFields();
        mouseEvent.consume();
    }

    public void onMouseScrolledOnPlot(ScrollEvent scrollEvent){
        canvasManager.onMouseScrolled(scrollEvent);
        updateTextFields();
        scrollEvent.consume();
    }

    public void onAutosizeButtonClicked(ActionEvent actionEvent){
        graph3D.autosizeZAxis(Integer.parseInt(gridResolutionTextField.getText()));
        canvasManager.update();
        updateTextFields();
        actionEvent.consume();
    }

    public void onUpdateGraphButtonClicked(ActionEvent actionEvent){
        updateAxisRange(graph3D.getXAxis(), xAxisLowTextField, xAxisHighTextField);
        updateAxisRange(graph3D.getYAxis(), yAxisLowTextField, yAxisHighTextField);
        updateAxisRange(graph3D.getZAxis(), zAxisLowTextField, zAxisHighTextField);
        canvasManager.update();
        actionEvent.consume();
    }

    private void updateTextFields(){
        updateAxisTextField(graph3D.getXAxis(), xAxisLowTextField, xAxisHighTextField);
        updateAxisTextField(graph3D.getYAxis(), yAxisLowTextField, yAxisHighTextField);
        updateAxisTextField(graph3D.getZAxis(), zAxisLowTextField, zAxisHighTextField);
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

    public void toggleWireframe(ActionEvent actionEvent){
        canvasManager.toggleWireframe();
        canvasManager.paint();
        actionEvent.consume();
    }

    public void updateGridResolution(ActionEvent actionEvent){
        canvasManager.setGridResolution(Integer.parseInt(gridResolutionTextField.getText()));
        canvasManager.update();
        actionEvent.consume();
    }
}
