package bg.sofia.tu.iti.gui.event.axis;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class VerticalAxisEventHandler implements CanvasRegionEventHandler{
    private final Axis   axis;
    private       double mouseLastDraggedAtY;

    public VerticalAxisEventHandler(Axis axis){
        this.axis = axis;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getSceneY() > mouseLastDraggedAtY){
            axis.getRange()
                .expand(10);
        }
        else{
            axis.getRange()
                .shrink(10);
        }
        //TODO fix mod ratio same as horizontal
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        double pixels = scrollEvent.getDeltaY();
        if(pixels > 0){
            axis.getRange()
                .shrink(pixels);
        }
        else{
            axis.getRange()
                .expand(-pixels);
        }
    }
}
