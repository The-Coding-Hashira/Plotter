package bg.sofia.tu.iti.gui.canvas.manager;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvasManagerFactory{
    private final Dimension2D     canvasDimension;
    private final GraphicsContext graphicsContext;

    public CanvasManagerFactory(Canvas canvas){
        canvasDimension = new Dimension2D(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public CanvasManager createCanvasManager(Function function){
        //TODO if integral new AreaGraph else LineGraph
        if(function.getNumberOfParameters() == 1){
            return new Plot2DCanvasManager(canvasDimension, graphicsContext, function);
        }
        if(function.getNumberOfParameters() == 2){
            return new Plot3DCanvasManager(canvasDimension, graphicsContext, function);
        }
        throw new IllegalArgumentException("Compatible functions are with 1 and 2 args, got " + function.getNumberOfParameters() + " args.");
    }
}
