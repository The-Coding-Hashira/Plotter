package bg.sofia.tu.iti.gui.event.axis;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;

public interface AxisEventHandlerFactory{
    CanvasRegionEventHandler create(Axis axis);
}
