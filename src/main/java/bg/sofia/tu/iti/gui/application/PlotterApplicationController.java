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

    public void plotRipple(ActionEvent actionEvent){
        expressionTextArea.setText("sin(10*(x^2+y^2))/10");
        onPlotButtonClicked(actionEvent);
    }

    public void plotTorus(ActionEvent actionEvent){
        expressionTextArea.setText("(0.4^2-(0.6-(x^2+y^2)^0.5)^2)^0.5");
        onPlotButtonClicked(actionEvent);
    }

    public void plotBumps(ActionEvent actionEvent){
        expressionTextArea.setText("sin(5*x)*cos(5*y)/5");
        onPlotButtonClicked(actionEvent);
    }

    public void plotCone(ActionEvent actionEvent){
        expressionTextArea.setText("(x^2+y^2)^0.5");
        onPlotButtonClicked(actionEvent);
    }

    public void plotStairs(ActionEvent actionEvent){
        expressionTextArea.setText("(sign(-.65-x)+sign(-.35-y)+sign(-.05-x)+sign(.25-y)+sign(.55-x))/7");
        onPlotButtonClicked(actionEvent);
    }

    public void plotPyramid(ActionEvent actionEvent){
        expressionTextArea.setText("1-abs(x+y)-abs(y-x)");
        onPlotButtonClicked(actionEvent);
    }

    public void plotLetterA(ActionEvent actionEvent){
        expressionTextArea.setText(
                "((1-sign(-x-.9+abs(y*2)))/3*(sign(.9-x)+1)/3)*(sign(x+.65)+1)/2 - ((1-sign(-x-.39+abs(y*2)))/3*" +
                        "(sign" + "(.9-x)+1)/3) + ((1-sign(-x-.39+abs(y*2)))/3*(sign(.6-x)+1)/3)*(sign(x-.35)+1)/2");
        onPlotButtonClicked(actionEvent);
    }

    public void plotIntersectingFences(ActionEvent actionEvent){
        expressionTextArea.setText(".75/e^((x*5)^2*(y*5)^2)");
        onPlotButtonClicked(actionEvent);
    }

    public void plotWindmill(ActionEvent actionEvent){
        expressionTextArea.setText("x*y^3-y*x^3");
        onPlotButtonClicked(actionEvent);
    }

    public void plotBlackHole(ActionEvent actionEvent){
        expressionTextArea.setText("-1/(x^2+y^2)");
        onPlotButtonClicked(actionEvent);
    }

    public void plotRuggedRipple(ActionEvent actionEvent){
        expressionTextArea.setText("cos(abs(x)+abs(y))");
        onPlotButtonClicked(actionEvent);
    }

    public void plotRuggedRippleStairs(ActionEvent actionEvent){
        expressionTextArea.setText("cos(abs(x)+abs(y))*(abs(x)+abs(y))");
        onPlotButtonClicked(actionEvent);
    }

    public void plotNoisySine(ActionEvent actionEvent){
        expressionTextArea.setText("sin(954*x)-2*cos(x)");
        onPlotButtonClicked(actionEvent);
    }

    public void plotWeird2D(ActionEvent actionEvent){
        expressionTextArea.setText("((x-0.2)*sin(1/(x-0.2)) + x +0.8)*(10*(x-0.1)^2+0.9)");
        onPlotButtonClicked(actionEvent);
    }

    public void plotOscillation(ActionEvent actionEvent){
        expressionTextArea.setText("5*e^-(0.3*x)*sin(2*x+30)");
        onPlotButtonClicked(actionEvent);
    }
}
