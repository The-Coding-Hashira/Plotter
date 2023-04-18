package bg.sofia.tu.iti.gui.event;

import bg.sofia.tu.iti.graph.d3.Graph3D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.gui.event.axis.AxisTraverseEventHandler;
import bg.sofia.tu.iti.gui.event.axis.VerticalAxisEventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class GraphEventHandler{
    private final VerticalAxisEventHandler xAxisEventHandler;
    private final VerticalAxisEventHandler yAxisEventHandler;
    private final VerticalAxisEventHandler   zAxisEventHandler;
    private final AxisTraverseEventHandler   zAxisTraverseEventHandler;
    private final PlaneEventHandler          xyPlaneEventHandler;

    public GraphEventHandler(Graph3D graph3D, Dimension2D canvasDimension){
        xAxisEventHandler         = new VerticalAxisEventHandler(graph3D.getXAxis());
        yAxisEventHandler         = new VerticalAxisEventHandler(graph3D.getYAxis());
        zAxisEventHandler         = new VerticalAxisEventHandler(graph3D.getZAxis());
        zAxisTraverseEventHandler = new AxisTraverseEventHandler(graph3D.getZAxis(), canvasDimension);
        xyPlaneEventHandler       = new PlaneEventHandler(graph3D.getXAxis(), graph3D.getYAxis(), canvasDimension);
    }

    public void updateMousePosition(MouseEvent mouseEvent){
        xAxisEventHandler.onMousePressed(mouseEvent);
        yAxisEventHandler.onMousePressed(mouseEvent);
        zAxisEventHandler.onMousePressed(mouseEvent);
        xyPlaneEventHandler.onMousePressed(mouseEvent);
        zAxisTraverseEventHandler.onMousePressed(mouseEvent);
    }

    public void traverseXYPlane(MouseEvent mouseEvent){
        xyPlaneEventHandler.onMouseDragged(mouseEvent);
    }

    public void traverseZAxis(MouseEvent mouseEvent){
        zAxisTraverseEventHandler.onTraversedVertically(mouseEvent);
    }

    public void zoom(ScrollEvent scrollEvent){
        xAxisEventHandler.onMouseScrolled(scrollEvent);
        yAxisEventHandler.onMouseScrolled(scrollEvent);
        zAxisEventHandler.onMouseScrolled(scrollEvent);
    }

    public void alterXAxis(MouseEvent mouseEvent){
        xAxisEventHandler.onMouseDragged(mouseEvent);
    }

    public void alterXAxis(ScrollEvent scrollEvent){
        xAxisEventHandler.onMouseScrolled(scrollEvent);
    }

    public void alterYAxis(MouseEvent mouseEvent){
        yAxisEventHandler.onMouseDragged(mouseEvent);
    }

    public void alterYAxis(ScrollEvent scrollEvent){
        yAxisEventHandler.onMouseScrolled(scrollEvent);
    }

    public void alterZAxis(MouseEvent mouseEvent){
        zAxisEventHandler.onMouseDragged(mouseEvent);
    }

    public void alterZAxis(ScrollEvent scrollEvent){
        zAxisEventHandler.onMouseScrolled(scrollEvent);
    }
}
