package bg.sofia.tu.iti.gui.application;

import bg.sofia.tu.iti.gui.canvas.manager.CanvasManager;
import bg.sofia.tu.iti.gui.canvas.manager.CanvasManagerFactory;
import bg.sofia.tu.iti.math.context.MathContext;
import bg.sofia.tu.iti.math.context.MathContextFactory;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class PlotterTemp{
    private final CanvasManagerFactory     canvasManagerFactory;
    private final FunctionDefinitionParser functionDefinitionParser;
    private       CanvasManager            canvasManager;

    public PlotterTemp(Canvas canvas){
        canvasManagerFactory = new CanvasManagerFactory(canvas);
        MathContext mathContext = new MathContextFactory().createMathContext();
        functionDefinitionParser = new FunctionDefinitionParser(mathContext.getTokenTypes());
    }

    public void onMousePressed(MouseEvent mouseEvent){
        canvasManager.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        canvasManager.onMouseDragged(mouseEvent);
    }

    public void onMouseScrolled(ScrollEvent scrollEvent){
        canvasManager.onMouseScrolled(scrollEvent);
    }

    public void plot(String expression){
        //        canvasManager = canvasManagerFactory.createCanvasManager(createFunction(expression));
        //TODO clean up this
        canvasManager = canvasManagerFactory.createCanvasManager(null);
        canvasManager.paint();
    }

    private Function createFunction(String expression){
        StringBuilder stringBuilder = new StringBuilder(expression);
        stringBuilder.insert(0, "f(x)=");
        return functionDefinitionParser.parse(stringBuilder.toString());
    }
}
