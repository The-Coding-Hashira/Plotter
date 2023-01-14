package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.FXMLUtil;
import bg.sofia.tu.iti.math.expression.input.FunctionFactory;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.control.Tab;

public class PlotterLoader{
    private static final String PLOT_2D_VIEW = "plot2d";
    private static final String PLOT_3D_VIEW = "plot3d";

    private final Tab             plotTab;
    private final FunctionFactory functionFactory;

    public PlotterLoader(Tab plotTab){
        this.plotTab    = plotTab;
        functionFactory = new FunctionFactory();
    }

    public void load(String mathExpressionInput){
        Function function = functionFactory.create(mathExpressionInput);
        //TODO if integral new AreaGraph else LineGraph
        if(function.getNumberOfArguments() == 1){
            plotTab.setContent(FXMLUtil.load(PLOT_2D_VIEW, param -> new Plotter2D(function)));
            return;
        }
        if(function.getNumberOfArguments() == 2){
            plotTab.setContent(FXMLUtil.load(PLOT_3D_VIEW, param -> new Plotter3D(function)));
            return;
        }
        throw new IllegalArgumentException("Compatible functions can accept 1 or 2 args, instead got: " + function.getNumberOfArguments() + " args.");
    }
}
