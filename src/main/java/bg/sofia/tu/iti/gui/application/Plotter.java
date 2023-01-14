package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.math.function.Function;
import javafx.fxml.Initializable;

public abstract class Plotter implements Initializable{
    private final Function function;

    public Plotter(Function function){
        this.function = function;
    }

    public Function getFunction(){
        return function;
    }
}
