package bg.sofia.tu.iti.gui.event;

import bg.sofia.tu.iti.gui.listener.PlotAreaEventListener;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;
import java.util.List;

public class PlotAreaEventHandler implements CanvasRegionEventHandler{
    private final List<PlotAreaEventListener> plotAreaEventListeners;
    private       double                      mouseLastDraggedAtX;
    private       double                      mouseLastDraggedAtY;

    public PlotAreaEventHandler(){
        plotAreaEventListeners = new ArrayList<>();
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.getSceneX() != mouseLastDraggedAtX){
            notifyListenersPlotAreaMovedHorizontally(mouseEvent.getSceneX() - mouseLastDraggedAtX);
        }
        if(mouseEvent.getSceneY() != mouseLastDraggedAtY){
            notifyListenersPlotAreaMovedVertically(mouseEvent.getSceneY() - mouseLastDraggedAtY);
        }
        mouseLastDraggedAtX = mouseEvent.getSceneX();
        mouseLastDraggedAtY = mouseEvent.getSceneY();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        notifyListenersPlotAreaScrolled(scrollEvent);
    }

    private void notifyListenersPlotAreaScrolled(ScrollEvent scrollEvent){
        plotAreaEventListeners.forEach(plotAreaEventListener -> plotAreaEventListener.scrolled(scrollEvent));
    }

    private void notifyListenersPlotAreaMovedHorizontally(double pixels){
        plotAreaEventListeners.forEach(plotAreaEventListener -> plotAreaEventListener.movedHorizontally(pixels));
    }

    private void notifyListenersPlotAreaMovedVertically(double pixels){
        plotAreaEventListeners.forEach(plotAreaEventListener -> plotAreaEventListener.movedVertically(pixels));
    }

    public void addPlotAreaMovedListener(PlotAreaEventListener plotAreaEventListener){
        plotAreaEventListeners.add(plotAreaEventListener);
    }
}
