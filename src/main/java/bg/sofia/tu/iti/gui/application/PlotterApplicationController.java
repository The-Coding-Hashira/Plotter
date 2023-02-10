package bg.sofia.tu.iti.gui.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlotterApplicationController implements Initializable{
    public static final String PLOT_TAB     = "Plot";
    public static final String SOLUTION_TAB = "Solution";

    @FXML
    private TextArea      expressionTextArea;
    @FXML
    private TabPane       tabPane;
    private PlotterLoader plotterLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        plotterLoader = new PlotterLoader(findTab(PLOT_TAB));
        plotterLoader.load(expressionTextArea.getText());
    }

    private Tab findTab(String text){
        return tabPane.getTabs()
                      .stream()
                      .filter(tab -> tab.getText()
                                        .contentEquals(text))
                      .findFirst()
                      .orElseThrow(() -> new RuntimeException("Cannot find tab: " + text));
    }

    public void onPlotButtonClicked(ActionEvent actionEvent){
        plotterLoader.load(expressionTextArea.getText());
        actionEvent.consume();
    }
}
