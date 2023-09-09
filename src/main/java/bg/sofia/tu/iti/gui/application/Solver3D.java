package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.math.function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Solver3D implements Initializable{
    private final Function function;

    @FXML
    private TextField variableXInput;
    @FXML
    private TextField variableYInput;
    @FXML
    private Text      calculationDescriptionText;

    public Solver3D(Function function){
        this.function = function;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        onSolve(new ActionEvent());
    }

    public void onSolve(ActionEvent actionEvent){
        Stack<Double> functionArguments = new Stack<>();
        functionArguments.push(Double.parseDouble(variableXInput.getText()));
        functionArguments.push(Double.parseDouble(variableYInput.getText()));
        calculationDescriptionText.setText(function.calculate(functionArguments)
                                                   .getDescription());
        actionEvent.consume();
    }
}
