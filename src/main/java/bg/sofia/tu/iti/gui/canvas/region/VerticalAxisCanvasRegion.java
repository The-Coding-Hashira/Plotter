package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graph.d2.axis.AxisColorScheme;
import bg.sofia.tu.iti.gui.event.axis.VerticalAxisEventHandlerFactory;
import bg.sofia.tu.iti.graph.d2.axis.VerticalAxisPainter;
import javafx.scene.canvas.GraphicsContext;

public class VerticalAxisCanvasRegion extends AxisCanvasRegion{
    public VerticalAxisCanvasRegion(Dimension2D dimension, GraphicsContext graphicsContext,
                                    AxisColorScheme colorScheme, int tickLength){
        super(10, new VerticalAxisEventHandlerFactory(), new VerticalAxisPainter(graphicsContext, dimension,
                                                                                 colorScheme, tickLength));
    }

    @Override
    public double getPixelRange(){
        return getAxisPainter().getDimension()
                               .getHeight();
    }
}
