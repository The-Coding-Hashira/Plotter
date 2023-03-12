package bg.sofia.tu.iti.graph.d2.axis;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.canvas.GraphicsContext;

public class HorizontalAxisPainter extends AxisPainter{
    public HorizontalAxisPainter(GraphicsContext graphicsContext, Dimension2D dimension, AxisColorScheme colorScheme,
                                 double tickLength){
        super(graphicsContext, dimension, colorScheme, tickLength);
    }

    @Override
    public void strokeTickLineNormalized(double offset){
        strokeTickLine(offset * getDimension().getWidth());
    }

    @Override
    public void fillTickTextNormalized(String text, double offset){
        fillTickText(text, offset * getDimension().getWidth());
    }

    @Override
    public void strokeTickLine(double offset){
        strokeVerticalLine(0 + offset, 0, -getTickLength());
    }

    @Override
    public void fillTickText(String value, double offset){
        fillText(value, offset - 8, 17);
    }

    @Override
    public void strokeBaseLine(){
        strokeHorizontalLine(0, 0, getDimension().getWidth());
    }
}
