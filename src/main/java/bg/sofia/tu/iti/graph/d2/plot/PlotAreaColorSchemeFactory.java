package bg.sofia.tu.iti.graph.d2.plot;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import javafx.scene.paint.Color;

public class PlotAreaColorSchemeFactory{
    public PlotAreaColorScheme createDefaultPlotAreaColorScheme(){
        return new PlotAreaColorScheme.Builder().background(ColorScheme.BEIGE_YELLOWISH)
                                                .gridLine(ColorScheme.GRAY_LIGHT)
                                                .originLine(Color.BLACK)
                                                .build();
    }
}
