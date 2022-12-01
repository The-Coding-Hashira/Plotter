package bg.sofia.tu.iti.graph.d2.plot;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import javafx.scene.paint.Color;

public class PlotAreaColorScheme extends ColorScheme{
    private final Color background;
    private final Color gridLine;
    private final Color originLine;

    public PlotAreaColorScheme(Builder builder){
        background = builder.background;
        gridLine   = builder.gridLine;
        originLine = builder.originLine;
    }

    public Color getBackground(){
        return background;
    }

    public Color getGridLine(){
        return gridLine;
    }

    public Color getOriginLine(){
        return originLine;
    }

    public static class Builder{
        private Color background;
        private Color gridLine;
        private Color originLine;

        public Builder background(Color background){
            this.background = background;
            return this;
        }

        public Builder gridLine(Color gridLine){
            this.gridLine = gridLine;
            return this;
        }

        public Builder originLine(Color originLine){
            this.originLine = originLine;
            return this;
        }

        public PlotAreaColorScheme build(){
            return new PlotAreaColorScheme(this);
        }
    }
}
