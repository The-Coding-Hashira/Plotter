package bg.sofia.tu.iti.graph.d2.axis;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.canvas.GraphicsContext;

public class VerticalAxisPainter extends AxisPainter{

    public VerticalAxisPainter(GraphicsContext graphicsContext, Dimension2D dimension, AxisColorScheme colorScheme,
                               double tickLength){
        super(graphicsContext, dimension, colorScheme, tickLength);
    }

    @Override
    public void strokeTickLine(double offset){
        strokeHorizontalLine(getDimension().getWidth(), getDimension().getHeight() - offset, getTickLength());
    }

    @Override
    public void fillTickText(String value, double offset){
        fillText(value, getDimension().getWidth() - 21, getDimension().getHeight() - offset);
    }

    @Override
    public void strokeBaseLine(){
        strokeVerticalLine(getDimension().getWidth(), 0, getDimension().getHeight());
    }
}
