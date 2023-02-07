package bg.sofia.tu.iti;

import bg.sofia.tu.iti.math.context.MathContext;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;
import bg.sofia.tu.iti.math.expression.input.token.TokenTypeFactory;
import bg.sofia.tu.iti.math.expression.interpreter.ExpressionInterpreter;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.formula.FunctionFormula;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PlotterApplication extends Application{
    public static void main(String[] args){
        //TODO deal with this shit
        //        FunctionFormula     formula    = new FunctionFormula("lin(x)=a*x+b");
        //        Map<String, Number> parameters = new HashMap<>();
        //        parameters.put("a", 5);
        //        parameters.put("b", 50);
        //        MathContext mathContext = new MathContextFactory().createMathContext();
        //        Function linearFunction = formula.buildFunctionForParameters(parameters, mathContext.getTokenTypes());
        //        List<Function> functions = mathContext.getFunctions();
        //        functions.add(linearFunction);
        //        ExpressionInterpreter interpreter =
        //                new ExpressionInterpreter(new TokenTypeFactory().createTokenTypes(functions),
        //                                                                      mathContext.getCalculatorSpecs());
        FunctionDefinitionParser fdp = new FunctionDefinitionParser(new MathContextFactory().createMathContext()
                                                                                            .getTokenTypes());
        Function      f = fdp.parse("kur(x,y,z,a,b,c)=x+y+(z*a+b*c)");
        Stack<Double> a = new Stack<>();
        a.push(10.0);
        a.push(15.0);
        a.push(2.0);
        a.push(3.0);
        a.push(5.0);
        a.push(6.0);
        System.out.println("kurec = " + f.calculate(a)
                                         .getResult());
        //        System.out.println(interpreter.interpret("lin(5)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(6)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(7)")
        //                                      .get());
        //        System.out.println(interpreter.interpret("lin(8)")
        //                                      .get());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Plotter");
        primaryStage.setScene(new Scene(FXMLUtil.load("main")));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
