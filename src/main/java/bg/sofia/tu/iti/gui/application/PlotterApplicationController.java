package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.math.expression.input.FunctionFactory;
import bg.sofia.tu.iti.math.function.Function;
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
    private TextArea        expressionTextArea;
    @FXML
    private TabPane         tabPane;
    private FunctionFactory functionFactory;
    private PlotterLoader   plotterLoader;
    private SolverLoader    solverLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        tabPane.setTabMinWidth(60);
        functionFactory = new FunctionFactory();
        plotterLoader   = new PlotterLoader(findTab(PLOT_TAB));
        solverLoader    = new SolverLoader(findTab(SOLUTION_TAB));

        updateComponents();
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
        updateComponents();
        actionEvent.consume();
    }

    private void updateComponents(){
        Function function = createFunction();
        plotterLoader.load(function);
        solverLoader.load(function);
    }

    private Function createFunction(){
        return functionFactory.create(expressionTextArea.getText());
    }
}
