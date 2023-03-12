package bg.sofia.tu.iti.gui.event.axis;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class HorizontalAxisEventHandler implements CanvasRegionEventHandler{
    private final Axis   axis;
    private       double mouseLastDraggedAtX;

    public HorizontalAxisEventHandler(Axis axis){
        this.axis = axis;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getX();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getX() < mouseLastDraggedAtX){
            axis.getRange()
                .expand();
        }
        else{
            axis.getRange()
                .shrink();
        }
        mouseLastDraggedAtX = mouseEvent.getX();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        if(scrollEvent.getDeltaY() > 0){
            onMouseScrolled(scrollEvent.getDeltaY());
        }
        else{
            onMouseScrolled(scrollEvent.getDeltaX());
        }
    }

    private void onMouseScrolled(double pixels){
        if(pixels > 0){
            axis.getRange()
                .shrink();
        }
        else{
            axis.getRange()
                .expand();
        }
    }
}
