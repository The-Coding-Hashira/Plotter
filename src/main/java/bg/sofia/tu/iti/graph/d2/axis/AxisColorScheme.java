package bg.sofia.tu.iti.graph.d2.axis;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import javafx.scene.paint.Color;

public class AxisColorScheme extends ColorScheme{
    private final Color background;
    private final Color baseLine;
    private final Color tick;
    private final Color tickText;

    public AxisColorScheme(Builder builder){
        background = builder.background;
        baseLine   = builder.baseLine;
        tick       = builder.tick;
        tickText   = builder.tickText;
    }

    public Color getBackground(){
        return background;
    }

    public Color getBaseLine(){
        return baseLine;
    }

    public Color getTick(){
        return tick;
    }

    public Color getTickText(){
        return tickText;
    }

    public static class Builder{
        private Color background;
        private Color baseLine;
        private Color tick;
        private Color tickText;

        public Builder background(Color background){
            this.background = background;
            return this;
        }

        public Builder baseLine(Color baseLine){
            this.baseLine = baseLine;
            return this;
        }

        public Builder tick(Color tick){
            this.tick = tick;
            return this;
        }

        public Builder tickText(Color tickText){
            this.tickText = tickText;
            return this;
        }

        public AxisColorScheme build(){
            return new AxisColorScheme(this);
        }
    }
}
