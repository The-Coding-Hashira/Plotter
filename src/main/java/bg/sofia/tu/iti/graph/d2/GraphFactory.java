package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;

public class GraphFactory{
    private final Function function;

    public GraphFactory(Function function){
        this.function = function;
    }

    public Graph createGraph(GraphicsContext graphicsContext, Dimension2D plotAreaDimension){
        //TODO if integral new AreaGraph else LineGraph
        return new AreaGraph(graphicsContext, plotAreaDimension, function, -10, 10);
    }
}
