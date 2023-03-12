package bg.sofia.tu.iti.gui.event.axis;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import javafx.scene.input.MouseEvent;

public class AxisTraverseEventHandler{
    private final Axis        axis;
    private final Dimension2D dimension2D;
    private       double      mouseLastDraggedAtX;
    private       double      mouseLastDraggedAtY;

    public AxisTraverseEventHandler(Axis axis, Dimension2D dimension2D){
        this.axis        = axis;
        this.dimension2D = dimension2D;
    }

    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getX();
        mouseLastDraggedAtY = mouseEvent.getY();
    }

    public void onTraversedHorizontally(MouseEvent mouseEvent){
        if(mouseEvent.getX() != mouseLastDraggedAtX){
            axis.getRange()
                .moveParameterized(GraphUtils.findParameter(-(mouseEvent.getX() - mouseLastDraggedAtX),
                                                            0,
                                                            dimension2D.getWidth()));
        }
        mouseLastDraggedAtX = mouseEvent.getX();
    }

    public void onTraversedVertically(MouseEvent mouseEvent){
        if(mouseEvent.getY() != mouseLastDraggedAtY){
            axis.getRange()
                .moveParameterized(GraphUtils.findParameter(mouseEvent.getY() - mouseLastDraggedAtY,
                                                            0,
                                                            dimension2D.getHeight()));
        }
        mouseLastDraggedAtY = mouseEvent.getY();
    }
}
