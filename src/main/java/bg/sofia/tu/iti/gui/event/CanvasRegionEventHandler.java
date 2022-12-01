package bg.sofia.tu.iti.gui.event;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public interface CanvasRegionEventHandler{
    void onMousePressed(MouseEvent mouseEvent);

    void onMouseDragged(MouseEvent mouseEvent);

    void onMouseScrolled(ScrollEvent scrollEvent);
}
