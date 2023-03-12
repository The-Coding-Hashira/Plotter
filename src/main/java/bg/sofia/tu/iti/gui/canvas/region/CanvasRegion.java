package bg.sofia.tu.iti.gui.canvas.region;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public interface CanvasRegion{
    boolean containsPoint(double x, double y);

    void onMousePressed(MouseEvent mouseEvent);

    void onMouseDragged(MouseEvent mouseEvent);

    void onMouseScrolled(ScrollEvent scrollEvent);

    void paint();
}
