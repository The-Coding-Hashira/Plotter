package bg.sofia.tu.iti.gui.event.axis;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;

public class VerticalAxisEventHandlerFactory implements AxisEventHandlerFactory{
    @Override
    public CanvasRegionEventHandler create(Axis axis){
        return new VerticalAxisEventHandler(axis);
    }
}
