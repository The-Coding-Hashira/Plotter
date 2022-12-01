package bg.sofia.tu.iti.gui.canvas.manager;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public interface CanvasManager{
    void onMousePressed(MouseEvent mouseEvent);

    void onMouseDragged(MouseEvent mouseEvent);

    void onMouseScrolled(ScrollEvent scrollEvent);

    //TODO not setGraph, but from factory get CanvasManager based on func
    //void setGraph(GraphFactory graphFactory);

    void paint();
}
