package bg.sofia.tu.iti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlotterApplication extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(new Scene(FXMLUtil.load("main")));
        primaryStage.sizeToScene();
        primaryStage.show();

        //TODO deal with this shit
        //        FunctionFormula     formula    = new FunctionFormula("lin(x)=a*x+b");
        //        Map<String, Number> parameters = new HashMap<>();
        //        parameters.put("a", 5);
        //        parameters.put("b", 50);
        //        Function       linearFunction = formula.buildFunctionForParameters(parameters);
        //        MathContext    mathContext    = new MathContextFactory().createMathContext();
        //        List<Function> functions      = mathContext.getFunctions();
        //        functions.add(linearFunction);
        //        ExpressionInterpreter interpreter = new ExpressionInterpreter(mathContext.getTokenTypes(),
        //                                                                      mathContext.getCalculatorSpecs());
        //        System.out.println(interpreter.interpret("lin(5)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(6)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(7)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(8)")
        //                                      .get());
    }
}
