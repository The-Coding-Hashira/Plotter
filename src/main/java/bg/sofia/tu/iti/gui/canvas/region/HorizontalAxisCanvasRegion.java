package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorScheme;
import bg.sofia.tu.iti.graph.d2.axis.HorizontalAxisPainter;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.axis.HorizontalAxisEventHandlerFactory;
import javafx.scene.canvas.GraphicsContext;

public class HorizontalAxisCanvasRegion extends AxisCanvasRegion{
    public HorizontalAxisCanvasRegion(Axis axis, Dimension2D dimension, GraphicsContext graphicsContext,
                                      AxisColorScheme colorScheme, int tickLength){
        super(axis,
              10,
              new HorizontalAxisEventHandlerFactory(),
              new HorizontalAxisPainter(graphicsContext, dimension, colorScheme, tickLength));
    }
}
