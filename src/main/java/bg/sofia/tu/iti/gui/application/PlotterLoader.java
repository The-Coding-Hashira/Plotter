package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.FXMLUtil;
import bg.sofia.tu.iti.math.expression.input.FunctionFactory;
import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.util.Stack;

public class PlotterLoader{
    private static final String PLOT_2D_VIEW = "plot2d";
    private static final String PLOT_3D_VIEW = "plot3d";

    private final Tab plotTab;

    public PlotterLoader(Tab plotTab){
        this.plotTab = plotTab;
    }

    public void load(String mathExpressionInput){
        Function function = new FunctionFactory().create(mathExpressionInput);
        //TODO if integral new AreaGraph else LineGraph
        if(function.getNumberOfParameters() == 1){
            plotTab.setContent(FXMLUtil.load(PLOT_2D_VIEW, param -> new Plotter2D(function)));
            return;
        }
        if(function.getNumberOfParameters() == 2){
            plotTab.setContent(FXMLUtil.load(PLOT_3D_VIEW, param -> new Plotter3D(function)));
            return;
        }
        if(function instanceof AnonymousFunction){
            AnonymousFunction anonymousFunction = (AnonymousFunction) function;
            if(anonymousFunction.getExpression().get(0) instanceof Integral){
                plotTab.setContent(FXMLUtil.load(PLOT_2D_VIEW, param -> new Plotter2D(function)));
                return;
            }
        }
        throw new IllegalArgumentException("Compatible functions can accept 1 or 2 args, instead got: " + function.getNumberOfParameters() + " args.");
    }
}
