package bg.sofia.tu.iti.graph.d2.axis;

import javafx.scene.paint.Color;

public class AxisColorSchemeFactory{
    public AxisColorScheme createDefaultAxisColorScheme(){
        return new AxisColorScheme.Builder().background(AxisColorScheme.BEIGE_YELLOWISH)
                                            .baseLine(Color.BLACK)
                                            .tick(Color.BLACK)
                                            .tickText(Color.BLACK)
                                            .build();
    }
}
