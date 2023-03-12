package bg.sofia.tu.iti.gui.canvas.manager;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import bg.sofia.tu.iti.graph.d3.Graph3D;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.World3DEngine;
import bg.sofia.tu.iti.graphics.d3.world.World3DEngineFactory;
import bg.sofia.tu.iti.gui.event.GraphEventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.List;

public class Plot3DCanvasManager implements CanvasManager{
    private final Graph3D           graph3D;
    private final GraphEventHandler graphEventHandler;
    private final World3DEngine     world3DEngine;
    private final GraphicsContext3D graphicsContext3D;
    private final int               backgroundColor;
    private       List<Point4D>     graphData;
    boolean drawWireframe;
    private int gridResolution;

    public Plot3DCanvasManager(Graph3D graph3D, Dimension2D canvasDimension, GraphicsContext graphicsContext){
        gridResolution    = 50;
        this.graph3D      = graph3D;
        graphEventHandler = new GraphEventHandler(graph3D, canvasDimension);
        world3DEngine     = new World3DEngineFactory().create(canvasDimension.getWidth(), canvasDimension.getHeight());
        graphicsContext3D = new GraphicsContext3D(graphicsContext, canvasDimension);
        backgroundColor   = graphicsContext3D.toInt(ColorScheme.BEIGE_YELLOWISH);
        drawWireframe     = true;
        updateGraphData();
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        graphEventHandler.updateMousePosition(mouseEvent);
        world3DEngine.onMousePressed(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        if(mouseEvent.isControlDown()){
            if(mouseEvent.isPrimaryButtonDown()){
                graphEventHandler.traverseZAxis(mouseEvent);
            }
            else if(mouseEvent.isSecondaryButtonDown()){
                graphEventHandler.traverseXYPlane(mouseEvent);
            }
            updateGraphData();
        }
        else if(mouseEvent.isShiftDown()){
            if(mouseEvent.isPrimaryButtonDown() && mouseEvent.isSecondaryButtonDown()){
                graphEventHandler.alterZAxis(mouseEvent);
            }
            else if(mouseEvent.isPrimaryButtonDown()){
                graphEventHandler.alterXAxis(mouseEvent);
            }
            else if(mouseEvent.isSecondaryButtonDown()){
                graphEventHandler.alterYAxis(mouseEvent);
            }
            updateGraphData();
        }
        else{
            world3DEngine.onMouseDragged(mouseEvent);
        }
        paint();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        if(scrollEvent.isControlDown() && scrollEvent.isShiftDown()){
            graphEventHandler.alterYAxis(scrollEvent);
        }
        else if(scrollEvent.isControlDown()){
            graphEventHandler.alterZAxis(scrollEvent);
        }
        else if(scrollEvent.isShiftDown()){
            graphEventHandler.alterXAxis(scrollEvent);
        }
        else{
            graphEventHandler.zoom(scrollEvent);
        }
        update();
    }

    @Override
    public void paint(){
        graphicsContext3D.clear(backgroundColor);
        List<Point4D> triangles = world3DEngine.transform(graphData);
        graphicsContext3D.rasterize(triangles);
        if(drawWireframe){
            graphicsContext3D.rasterizeWireframe(triangles);
        }
        world3DEngine.renderOccludedBounds(graphicsContext3D);

        graphicsContext3D.render();
        world3DEngine.renderTickData(graph3D.generateTickData(), graphicsContext3D);
        System.out.println("DONE - 3D");
    }

    public void update(){
        updateGraphData();
        paint();
    }

    public void updateGraphData(){
        graph3D.calculateData(gridResolution);
        graphData = GraphUtils.mapToHeat(world3DEngine.convertToWorldPoints(graph3D.normalizeDataPoints()));
    }

    public void toggleWireframe(){
        drawWireframe = !drawWireframe;
    }

    public void setGridResolution(int gridResolution){
        this.gridResolution = gridResolution;
    }

    public int getGridResolution(){
        return gridResolution;
    }
}
