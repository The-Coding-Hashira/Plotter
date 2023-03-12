package bg.sofia.tu.iti.gui.event;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.axis.HorizontalAxisEventHandler;
import bg.sofia.tu.iti.gui.event.axis.VerticalAxisEventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class PlaneEventHandler implements CanvasRegionEventHandler{
    private       double mouseLastDraggedAtX;
    private       double mouseLastDraggedAtY;
    private final Axis   xAxis;
    private final Axis   yAxis;

    private final Dimension2D                dimension2D;
    private final HorizontalAxisEventHandler horizontalAxisEventHandler;
    private final VerticalAxisEventHandler   verticalAxisEventHandler;

    public PlaneEventHandler(Axis xAxis, Axis yAxis, Dimension2D dimension2D){
        this.xAxis                 = xAxis;
        this.yAxis                 = yAxis;
        this.dimension2D           = dimension2D;
        horizontalAxisEventHandler = new HorizontalAxisEventHandler(xAxis);
        verticalAxisEventHandler   = new VerticalAxisEventHandler(yAxis);
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getX();
        mouseLastDraggedAtY = mouseEvent.getY();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getX() != mouseLastDraggedAtX){
            xAxis.getRange()
                 .moveParameterized(GraphUtils.findParameter(-(mouseEvent.getX() - mouseLastDraggedAtX),
                                                             0,
                                                             dimension2D.getWidth()));
        }
        if(mouseEvent.getY() != mouseLastDraggedAtY){
            yAxis.getRange()
                 .moveParameterized(GraphUtils.findParameter(mouseEvent.getY() - mouseLastDraggedAtY,
                                                             0,
                                                             dimension2D.getHeight()));
        }
        mouseLastDraggedAtX = mouseEvent.getX();
        mouseLastDraggedAtY = mouseEvent.getY();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        horizontalAxisEventHandler.onMouseScrolled(scrollEvent);
        verticalAxisEventHandler.onMouseScrolled(scrollEvent);
    }
}
