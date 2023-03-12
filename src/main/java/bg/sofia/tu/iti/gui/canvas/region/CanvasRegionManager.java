package bg.sofia.tu.iti.gui.canvas.region;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.List;

public class CanvasRegionManager{
    private final List<CanvasRegion> canvasRegions;
    private       CanvasRegion       selectedRegion;

    public CanvasRegionManager(List<CanvasRegion> canvasRegions){
        if(canvasRegions.isEmpty()){
            throw new IllegalArgumentException("Canvas regions list cannot be empty.");
        }
        this.canvasRegions = canvasRegions;
        selectedRegion     = canvasRegions.get(0);
    }

    public void onMousePressed(MouseEvent mouseEvent){
        selectedRegion = getRegionAt(mouseEvent.getX(), mouseEvent.getY());
        selectedRegion.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        selectedRegion.onMouseDragged(mouseEvent);
    }

    public void onMouseScrolled(ScrollEvent scrollEvent){
        selectedRegion = getRegionAt(scrollEvent.getX(), scrollEvent.getY());
        selectedRegion.onMouseScrolled(scrollEvent);
    }

    public void paint(){
        canvasRegions.forEach(CanvasRegion::paint);
    }

    private CanvasRegion getRegionAt(double x, double y){
        for(CanvasRegion canvasRegion : canvasRegions){
            if(canvasRegion.containsPoint(x, y)){
                return canvasRegion;
            }
        }
        throw new RuntimeException("Unknown region at: " + x + ", " + y);
    }
}
