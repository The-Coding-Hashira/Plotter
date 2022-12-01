package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;

public abstract class Graph{
    private final GraphicsContext graphicsContext;
    private final Dimension2D     plotAreaDimension;
    private final Function        function;

    public Graph(GraphicsContext graphicsContext, Dimension2D plotAreaDimension, Function function){
        this.graphicsContext   = graphicsContext;
        this.plotAreaDimension = plotAreaDimension;
        this.function          = function;
    }

    public abstract void paint();

    public abstract void horizontalRangeChanged(Range horizontalRange, Range verticalRange);

    public abstract void verticalRangeChanged(Range horizontalRange, Range verticalRange);

    public GraphicsContext getGraphicsContext(){
        return graphicsContext;
    }

    public Dimension2D getPlotAreaDimension(){
        return plotAreaDimension;
    }

    public final Function getFunction(){
        return function;
    }
}
