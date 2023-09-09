package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Solver2D implements Initializable{
    private final Function function;

    @FXML
    private TextField variableXInput;
    @FXML
    private Text      calculationDescriptionText;

    public Solver2D(Function function){
        this.function = function;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        onSolve(new ActionEvent());
    }

    public void onSolve(ActionEvent actionEvent){
        if(((AnonymousFunction) function).getExpression()
                                         .get(0) instanceof Integral){
            calculationDescriptionText.setText(((Integral) ((AnonymousFunction) function).getExpression()
                                                                                         .get(0)).integrate()
                                                                                                 .getFullDescription());
            actionEvent.consume();
            return;
        }
        Stack<Double> functionArguments = new Stack<>();
        functionArguments.push(Double.parseDouble(variableXInput.getText()));
        calculationDescriptionText.setText(function.calculate(functionArguments)
                                                   .getDescription());
        actionEvent.consume();
    }
}
