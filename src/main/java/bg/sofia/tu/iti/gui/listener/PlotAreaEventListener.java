package bg.sofia.tu.iti.gui.listener;

import javafx.scene.input.ScrollEvent;

public interface PlotAreaEventListener{
    void movedHorizontally(double pixels);

    void movedVertically(double pixels);

    void scrolled(ScrollEvent scrollEvent);
}
