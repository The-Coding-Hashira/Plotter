package bg.sofia.tu.iti.graphics.d3.world;

import bg.sofia.tu.iti.graph.d3.TickData;
import bg.sofia.tu.iti.graphics.GraphicsContext3D;
import bg.sofia.tu.iti.graphics.d3.Geometry3DUtils;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.transform.WorldTransformManager;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundingBox;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundsBinder;
import bg.sofia.tu.iti.graphics.d3.world.bound.BoundsRenderer;
import bg.sofia.tu.iti.graphics.d3.world.camera.CameraManager;
import bg.sofia.tu.iti.graphics.d3.world.clip.BoundsClipper;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.stream.Collectors;

public class World3DEngine{
    private final BoundingBox           boundingBox;
    private final BoundsBinder          boundsBinder;
    private final BoundsClipper         boundsClipper;
    private final BoundsRenderer        boundsRenderer;
    private final TickDataRenderer      tickDataRenderer;
    private final CameraManager         cameraManager;
    private final WorldTransformManager worldTransformManager;

    public World3DEngine(BoundingBox boundingBox, CameraManager cameraManager,
                         WorldTransformManager worldTransformManager){
        this.boundingBox           = boundingBox;
        boundsBinder               = new BoundsBinder(boundingBox);
        boundsClipper              = new BoundsClipper();
        boundsRenderer             = new BoundsRenderer(boundingBox, worldTransformManager);
        tickDataRenderer           = new TickDataRenderer();
        this.cameraManager         = cameraManager;
        this.worldTransformManager = worldTransformManager;
    }

    public void onMousePressed(MouseEvent mouseEvent){
        cameraManager.onMousePressed(mouseEvent);
    }

    public void onMouseDragged(MouseEvent mouseEvent){
        cameraManager.onMouseDragged(mouseEvent);
        worldTransformManager.updateCameraTransform(cameraManager.getTransform());
    }

    public List<Point4D> transform(List<Point4D> points){
        return worldTransformManager.applyTotalTransform(points);
    }

    public List<Point4D> convertToWorldPoints(List<Point4D> normalizedPoints){
        return boundsClipper.clipAgainstBoundingBox(Geometry3DUtils.sortToTriangles(bindToBoundingBox(normalizedPoints)),
                                                    boundingBox);
    }

    private List<Point4D> bindToBoundingBox(List<Point4D> normalizedPoints){
        return boundsBinder.bindToBoundingBox(normalizedPoints);
    }

    public void renderOccludedBounds(GraphicsContext3D graphicsContext3D){
        boundsRenderer.renderOccludedBounds(graphicsContext3D, cameraManager.getCamera());
    }

    public void renderTickData(TickData tickData, GraphicsContext3D graphicsContext3D){
        tickDataRenderer.render(transformTick3DData(boundsBinder.bindTickData(tickData, cameraManager.getCamera())),
                                graphicsContext3D,
                                cameraManager.getCamera());
    }

    private Tick3DData transformTick3DData(Tick3DData tick3DData){
        return new Tick3DData(transformTicks(tick3DData.getXTicks()),
                              transformTicks(tick3DData.getYTicks()),
                              transformTicks(tick3DData.getZTicks()));
    }

    private List<Tick3D> transformTicks(List<Tick3D> ticks){
        return ticks.stream()
                    .map(tick3D -> new Tick3D(tick3D.getTick(), transform(tick3D.getPosition())))
                    .collect(Collectors.toList());
    }

    private Point4D transform(Point4D point){
        return worldTransformManager.applyTotalTransform(point);
    }
}
