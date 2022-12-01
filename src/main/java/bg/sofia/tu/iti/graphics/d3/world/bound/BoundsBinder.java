package bg.sofia.tu.iti.graphics.d3.world.bound;

import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graph.d3.TickData;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.Tick3D;
import bg.sofia.tu.iti.graphics.d3.world.Tick3DData;
import bg.sofia.tu.iti.graphics.d3.world.camera.Camera;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BoundsBinder{
    private final double            boundingBoxSize;
    private final double            xBoundingBoxLowBoundary;
    private final double            yBoundingBoxLowBoundary;
    private final double            zBoundingBoxLowBoundary;
    private final XAxisOriginFinder xAxisOriginFinder;
    private final YAxisOriginFinder yAxisOriginFinder;
    private final ZAxisOriginFinder zAxisOriginFinder;

    public BoundsBinder(BoundingBox boundingBox){
        boundingBoxSize         = boundingBox.getSize();
        xBoundingBoxLowBoundary = boundingBox.getPosition()
                                             .getX() - (boundingBoxSize / 2);
        yBoundingBoxLowBoundary = boundingBox.getPosition()
                                             .getY() - (boundingBoxSize / 2);
        zBoundingBoxLowBoundary = boundingBox.getPosition()
                                             .getZ() - (boundingBoxSize / 2);
        xAxisOriginFinder       = new XAxisOriginFinder(boundingBox);
        yAxisOriginFinder       = new YAxisOriginFinder(boundingBox);
        zAxisOriginFinder       = new ZAxisOriginFinder(boundingBox);
    }

    public List<Point4D> bindToBoundingBox(List<Point4D> normalizedPoints){
        return normalizedPoints.stream()
                               .map(point -> new Point4D(GraphUtils.findParameterizedValue(point.getX(),
                                                                                           xBoundingBoxLowBoundary,
                                                                                           boundingBoxSize),
                                                         GraphUtils.findParameterizedValue(point.getY(),
                                                                                           yBoundingBoxLowBoundary,
                                                                                           boundingBoxSize),
                                                         GraphUtils.findParameterizedValue(point.getZ(),
                                                                                           zBoundingBoxLowBoundary,
                                                                                           boundingBoxSize),
                                                         point.getColor()))
                               .collect(Collectors.toList());
    }

    public Tick3DData bindTickData(TickData tickData, Camera camera){
        return new Tick3DData(bindToXAxis(tickData.getXTicks(), xAxisOriginFinder.find(camera)),
                              bindToYAxis(tickData.getYTicks(), yAxisOriginFinder.find(camera)),
                              bindToZAxis(tickData.getZTicks(), zAxisOriginFinder.find(camera)));
    }

    public List<Tick3D> bindToXAxis(List<Tick> ticks, Point4D origin){
        return mapToTick3D(ticks, tick -> new Tick3D(tick, placeOnXAxis(tick, origin)));
    }

    public List<Tick3D> bindToYAxis(List<Tick> ticks, Point4D origin){
        return mapToTick3D(ticks, tick -> new Tick3D(tick, placeOnYAxis(tick, origin)));
    }

    public List<Tick3D> bindToZAxis(List<Tick> ticks, Point4D origin){
        return mapToTick3D(ticks, tick -> new Tick3D(tick, placeOnZAxis(tick, origin)));
    }

    private List<Tick3D> mapToTick3D(List<Tick> ticks, Function<Tick, Tick3D> mapper){
        return ticks.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
    }

    private Point4D placeOnXAxis(Tick tick, Point4D origin){
        return new Point4D(GraphUtils.findParameterizedValue(tick.getNormalizedValue(), origin.getX(), boundingBoxSize),
                           origin.getY(),
                           origin.getZ());
    }

    private Point4D placeOnYAxis(Tick tick, Point4D origin){
        return new Point4D(origin.getX(),
                           GraphUtils.findParameterizedValue(tick.getNormalizedValue(), origin.getY(), boundingBoxSize),
                           origin.getZ());
    }

    private Point4D placeOnZAxis(Tick tick, Point4D origin){
        return new Point4D(origin.getX(),
                           origin.getY(),
                           GraphUtils.findParameterizedValue(tick.getNormalizedValue(),
                                                             origin.getZ(),
                                                             boundingBoxSize));
    }
}
