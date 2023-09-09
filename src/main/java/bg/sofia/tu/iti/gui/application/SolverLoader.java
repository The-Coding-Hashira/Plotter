package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.FXMLUtil;
import bg.sofia.tu.iti.math.expression.input.FunctionFactory;
import bg.sofia.tu.iti.math.function.AnonymousFunction;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Integral;
import javafx.scene.control.Tab;

public class SolverLoader{
    private static final String SOLUTION_2D_VIEW = "solution2d";
    private static final String SOLUTION_3D_VIEW = "solution3d";

    private final Tab plotTab;

    public SolverLoader(Tab plotTab){
        this.plotTab = plotTab;
    }

    public void load(Function function){
        if(function.getNumberOfParameters() == 1){
            plotTab.setContent(FXMLUtil.load(SOLUTION_2D_VIEW, param -> new Solver2D(function)));
            return;
        }
        if(function.getNumberOfParameters() == 2){
            plotTab.setContent(FXMLUtil.load(SOLUTION_3D_VIEW, param -> new Solver3D(function)));
            return;
        }
        if(function instanceof AnonymousFunction){
            AnonymousFunction anonymousFunction = (AnonymousFunction) function;
            if(anonymousFunction.getExpression().get(0) instanceof Integral){
                plotTab.setContent(FXMLUtil.load(SOLUTION_2D_VIEW, param -> new Solver2D(function)));
                return;
            }
        }
        throw new IllegalArgumentException("Compatible functions can accept 1 or 2 args, instead got: " + function.getNumberOfParameters() + " args.");
    }
}
