package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graphics.d3.world.camera.CameraManager;
import bg.sofia.tu.iti.gui.canvas.manager.CanvasManager;
import bg.sofia.tu.iti.gui.canvas.manager.Plot2DCanvasManager;
import bg.sofia.tu.iti.gui.canvas.manager.Plot3DCanvasManager;
import bg.sofia.tu.iti.math.function.Function;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Plotter2D extends Plotter{
    @FXML
    private Canvas              plot;
    private Plot2DCanvasManager canvasManager;

    public Plotter2D(Function function){
        super(function);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        canvasManager = new Plot2DCanvasManager(new Dimension2D(0, 0, plot.getWidth(), plot.getHeight()),
                                                plot.getGraphicsContext2D(),
                                                getFunction());
        canvasManager.paint();
    }

    public void plotOnMouseDragged(MouseEvent mouseEvent){
        canvasManager.onMouseDragged(mouseEvent);
    }

    public void plotOnMousePressed(MouseEvent mouseEvent){
        canvasManager.onMousePressed(mouseEvent);
    }

    public void plotOnMouseScrolled(ScrollEvent scrollEvent){
        canvasManager.onMouseScrolled(scrollEvent);
    }
}
