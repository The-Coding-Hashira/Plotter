package bg.sofia.tu.iti.gui.canvas.manager;

import bg.sofia.tu.iti.graph.core.ColorScheme;
import bg.sofia.tu.iti.graph.d3.Graph3D;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.World3DEngine;
import bg.sofia.tu.iti.graphics.d3.world.World3DEngineFactory;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.List;

public class Plot3DCanvasManager implements CanvasManager{
    private final Graph3D           graph3D;
    private final World3DEngine     world3DEngine;
    private final GraphicsContext3D graphicsContext3D;
    private final int               backgroundColor;
    List<Point4D> points;
    public Plot3DCanvasManager(Dimension2D canvasDimension, GraphicsContext graphicsContext, Function function){
        graph3D           = new Graph3D(function);
        world3DEngine     = new World3DEngineFactory().create(canvasDimension.getWidth(), canvasDimension.getHeight());
        graphicsContext3D = new GraphicsContext3D(graphicsContext, canvasDimension);
        backgroundColor   = graphicsContext3D.toInt(ColorScheme.BEIGE_YELLOWISH);
        graph3D.calculateData();
        points = GraphUtils.mapToHeat(world3DEngine.convertToWorldPoints(graph3D.normalizeDataPoints()));
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
//        world3DEngine.onMousePressed(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
//        world3DEngine.onMouseDragged(mouseEvent);
        paint();
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){

    }

    @Override
    public void paint(){
        graphicsContext3D.clear(backgroundColor);
        graphicsContext3D.rasterizeTriangleData(world3DEngine.transform(points));
        world3DEngine.renderOccludedBounds(graphicsContext3D);

        graphicsContext3D.render();
        world3DEngine.renderTickData(graph3D.generateTickData(), graphicsContext3D);
        System.out.println("DONE - 3D");
    }
}
