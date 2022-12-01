package bg.sofia.tu.iti;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.formula.FunctionFormula;
import bg.sofia.tu.iti.math.context.MathContext;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlotterApplication extends Application{

    public static void main(String[] args){
        FunctionFormula     formula    = new FunctionFormula("lin(x)=a*x+b");
        Map<String, Number> parameters = new HashMap<>();
        parameters.put("a", 5);
        parameters.put("b", 50);
        Function       linearFunction = formula.buildFunctionForParameters(parameters);
        MathContext    mathContext    = new MathContextFactory().createMathContext();
        List<Function> functions      = mathContext.getFunctions();
        functions.add(linearFunction);
        ExpressionInterpreter interpreter = new ExpressionInterpreter(mathContext.getTokenTypes(),
                                                                      mathContext.getCalculatorSpecs());
        System.out.println(interpreter.interpret("lin(5)")
                                      .get());
        System.out.println(interpreter.interpret("lin(6)")
                                      .get());
        System.out.println(interpreter.interpret("lin(7)")
                                      .get());
        System.out.println(interpreter.interpret("lin(8)")
                                      .get());

        Point4D p0 = new Point4D(0.5,-0.5,0.5);
        Point4D p1 = new Point4D(0,-1,0);
        System.out.println(p0.dotProduct(p1));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws
                                          IOException{
        Parent root = FXMLLoader.load(Paths.get("src/main/resources/view/PlotterApplicationView.fxml")
                                           .toUri()
                                           .toURL());
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
