package bg.sofia.tu.iti.gui.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlotterApplicationController implements Initializable{
    @FXML
    private Canvas   plot;
    @FXML
    private TextArea expressionTextArea;

    private Plotter plotter;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        plotter = new Plotter(plot);
        plotter.plot(expressionTextArea.getText());
    }

    //TODO fix names to onPlotMousePressed or sm like that
    public void plotOnMousePressed(MouseEvent mouseEvent){
        plotter.onMousePressed(mouseEvent);
    }

    public void plotOnMouseDragged(MouseEvent mouseEvent){
        plotter.onMouseDragged(mouseEvent);
    }

    public void plotOnMouseScrolled(ScrollEvent scrollEvent){
        plotter.onMouseScrolled(scrollEvent);
    }

    public void onPlotButtonClicked(ActionEvent actionEvent){
        plotter.plot(expressionTextArea.getText());
    }
}
