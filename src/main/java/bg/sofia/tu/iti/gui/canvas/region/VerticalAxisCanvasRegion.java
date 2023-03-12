package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorScheme;
import bg.sofia.tu.iti.graph.d2.axis.VerticalAxisPainter;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.axis.VerticalAxisEventHandlerFactory;
import javafx.scene.canvas.GraphicsContext;

public class VerticalAxisCanvasRegion extends AxisCanvasRegion{
    public VerticalAxisCanvasRegion(Axis axis, Dimension2D dimension, GraphicsContext graphicsContext,
                                    AxisColorScheme colorScheme, int tickLength){
        super(axis,
              10,
              new VerticalAxisEventHandlerFactory(),
              new VerticalAxisPainter(graphicsContext, dimension, colorScheme, tickLength));
    }
}
