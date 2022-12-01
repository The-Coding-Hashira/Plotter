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
        mouseLastDraggedAtX = mouseEvent.getSceneX();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getSceneX() < mouseLastDraggedAtX){
            axis.getRange()
                .expand(1);
        }
        else{
            axis.getRange()
                .shrink(1);
        }
        //TODO bring modification ratio here
        mouseLastDraggedAtX = mouseEvent.getSceneX();
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
