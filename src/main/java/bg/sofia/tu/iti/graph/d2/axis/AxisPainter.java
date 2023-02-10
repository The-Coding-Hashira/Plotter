package bg.sofia.tu.iti.graph.d2.axis;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graphics.d2.painter.Painter2D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.util.List;

public abstract class AxisPainter extends Painter2D{
    private final AxisColorScheme colorScheme;
    private final DecimalFormat   tickTextFormat;
    private final double          tickLength;

    public AxisPainter(GraphicsContext graphicsContext, Dimension2D dimension, AxisColorScheme colorScheme,
                       double tickLength){
        super(graphicsContext, dimension);
        this.colorScheme = colorScheme;
        tickTextFormat   = new DecimalFormat("##0.##E0");
        this.tickLength  = tickLength;
    }

    public abstract void strokeTickLine(double offset);

    public abstract void fillTickText(String text, double offset);

    public void paint(){
        clear();
        paintBackground();
        getGraphicsContext().setLineDashes(0);
        paintBaseLine();
    }

    private void paintBackground(){
        getGraphicsContext().setFill(colorScheme.getBackground());
        fill();
    }

    private void paintBaseLine(){
        getGraphicsContext().setStroke(colorScheme.getBaseLine());
        strokeBaseLine();
    }

    public abstract void strokeBaseLine();

    //tickCoordinates.clear();
    //    Range  axisRange     = axisTicksManager.getRange();
    //    double valuePerPixel = axisRange.calculate() / getPixelRange();
    //    double lowBoundaryCoordinates = axisRange.getLowBoundary() / valuePerPixel;
    //        for(double tickValue : values){
    //        double pixelsOffset = tickValue / valuePerPixel - lowBoundaryCoordinates;
    //        tickCoordinates.put(tickValue, pixelsOffset);
    //    }
    public void paintTicks(List<Tick> ticks){
        getGraphicsContext().setStroke(colorScheme.getTick());
        getGraphicsContext().setFill(colorScheme.getTickText());
        getGraphicsContext().setFont(Font.font("Calibri", 21));
        ticks.forEach(this::paintTick);
    }

    public void paintTick(Tick tick){
        //        strokeTickLine(tick.getCoordinate());
        //        fillTickText(tickTextFormat.format(tick.getValue()), tick.getCoordinate());
    }

    public double getTickLength(){
        return tickLength;
    }
}
