package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.axis.tick.TickGenerator;
import bg.sofia.tu.iti.graph.d2.axis.AxisPainter;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;
import bg.sofia.tu.iti.gui.event.axis.AxisEventHandlerFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public abstract class AxisCanvasRegion implements CanvasRegion{
    private final Axis                     axis;
    private final CanvasRegionEventHandler canvasRegionEventHandler;
    private final AxisPainter              axisPainter;

    private final TickGenerator tickGenerator;

    public AxisCanvasRegion(Axis axis, int ticks, AxisEventHandlerFactory axisEventHandlerFactory,
                            AxisPainter axisPainter){
        tickGenerator                 = new TickGenerator(ticks);
        this.axis                     = axis;
        this.canvasRegionEventHandler = axisEventHandlerFactory.create(axis);
        this.axisPainter              = axisPainter;
    }

    @Override
    public boolean containsPoint(double x, double y){
        return axisPainter.getDimension()
                          .containsPoint(x, y);
    }

    @Override
    public void paint(){
        axisPainter.paint();
        axisPainter.paintTicks(axis.generateTicks(tickGenerator));
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        canvasRegionEventHandler.onMousePressed(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        canvasRegionEventHandler.onMouseDragged(mouseEvent);
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        canvasRegionEventHandler.onMouseScrolled(scrollEvent);
    }
}
