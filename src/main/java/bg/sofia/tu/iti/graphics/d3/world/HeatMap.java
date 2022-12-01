package bg.sofia.tu.iti.graphics.d3.world;

import javafx.scene.paint.Color;

public class HeatMap{
    //TODO rework this class, make the gen method accept the range and points
    private final Color  lowBoundaryColor;
    private final Color  highBoundaryColor;
    private final double lowBoundary;
    private final double range;

    public HeatMap(Color lowBoundaryColor, Color highBoundaryColor, double lowBoundary, double highBoundary){
        this.lowBoundaryColor  = lowBoundaryColor;
        this.highBoundaryColor = highBoundaryColor;
        this.lowBoundary       = lowBoundary;
        this.range             = highBoundary - lowBoundary;
    }

    public Color generateColor(double value){
        double t = (value - lowBoundary) / range;
        //        if(t < 0.5){
        //            return Color.BLUE.interpolate(Color.YELLOW, t * 2);
        //        }
        //        return Color.YELLOW.interpolate(Color.RED, (t - 0.5) * 2);
        return lowBoundaryColor.interpolate(highBoundaryColor,
                                            t);
    }
}
