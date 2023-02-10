package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.canvas.manager.Plot3DCanvasManager;
import bg.sofia.tu.iti.math.function.Function;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Plotter3D extends Plotter{
    @FXML
    private Canvas              plot;
    private Plot3DCanvasManager canvasManager;

    public Plotter3D(Function function){
        super(function);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        canvasManager = new Plot3DCanvasManager(new Dimension2D(0, 0, plot.getWidth(), plot.getHeight()),
                                                plot.getGraphicsContext2D(),
                                                getFunction());
        canvasManager.paint();
    }

    public void plotOnMousePressed(MouseEvent mouseEvent){
        canvasManager.onMousePressed(mouseEvent);
    }

    public void plotOnMouseDragged(MouseEvent mouseEvent){
        canvasManager.onMouseDragged(mouseEvent);
    }

    public void plotOnMouseScrolled(ScrollEvent scrollEvent){
        canvasManager.onMouseScrolled(scrollEvent);
    }
}
