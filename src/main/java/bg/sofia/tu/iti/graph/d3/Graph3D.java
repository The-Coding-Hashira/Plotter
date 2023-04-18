package bg.sofia.tu.iti.graph.d3;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.axis.tick.TickGenerator;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.math.function.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph3D{
    private final Axis          xAxis;
    private final Axis          yAxis;
    private final Axis          zAxis;
    private final Function      function;
    private final List<Point4D> data;

    private final TickGenerator tickGenerator;

    public Graph3D(Function function){
        tickGenerator = new TickGenerator(8);
        this.xAxis    = new Axis(new Range(-10, 10));
        this.yAxis    = new Axis(new Range(-10, 10));
        this.zAxis    = new Axis(new Range(-10, 10));
        this.function = function;
        data          = new ArrayList<>();
    }

    public void autosizeZAxis(int gridResolution){
        calculateData(gridResolution);
        List<Point4D> sortedData = data.stream()
                                       .sorted(GraphUtils::compareZ)
                                       .collect(Collectors.toList());
        double minZ = sortedData.get(0)
                                .getZ();
        double maxZ = sortedData.get(sortedData.size() - 1)
                                .getZ();
        double delta = Math.abs((maxZ-minZ)*0.1);
        zAxis.setRange(new Range(minZ -delta, maxZ + delta));
        //TODO fix no value present error when Z is out of range of the 3D graph, user case is clicks autosize Z axis
        // to find where the graph is
    }

    public TickData generateTickData(){
        return new TickData(xAxis.generateTicks(tickGenerator),
                            yAxis.generateTicks(tickGenerator),
                            zAxis.generateTicks(tickGenerator));
    }

    public void calculateData(int gridResolution){
        //TODO add a proper parameterized function to generate points
        //TODO make a button to set Z range from max to min and render with proper heatmap
        double xLowBoundary = xAxis.getRange()
                                   .getLowBoundary();
        double xStep = xAxis.getRange()
                            .calculate() / gridResolution;
        double yLowBoundary = yAxis.getRange()
                                   .getLowBoundary();
        double yStep = yAxis.getRange()
                            .calculate() / gridResolution;
        Stack<Double> args = new Stack<>();
        data.clear();
        for(int j = 0; j <= gridResolution; j++){
            double y = (yStep * j) + yLowBoundary;
            for(int i = 0; i <= gridResolution; i++){
                double x = (xStep * i) + xLowBoundary;
                args.push(x);
                args.push(y);
                double z = function.calculate(args)
                                   .getResult();
                data.add(new Point4D(x, y, z));
            }
        }
    }

    public List<Point4D> normalizeDataPoints(){
        double xLowBoundary = xAxis.getRange()
                                   .getLowBoundary();
        double xRange = xAxis.getRange()
                             .calculate();
        double yLowBoundary = yAxis.getRange()
                                   .getLowBoundary();
        double yRange = yAxis.getRange()
                             .calculate();
        double zLowBoundary = zAxis.getRange()
                                   .getLowBoundary();
        double zRange = zAxis.getRange()
                             .calculate();
        return data.stream()
                   .map(point -> new Point4D(GraphUtils.findParameter(point.getX(), xLowBoundary, xRange),
                                             GraphUtils.findParameter(point.getY(), yLowBoundary, yRange),
                                             GraphUtils.findParameter(point.getZ(), zLowBoundary, zRange)))
                   .collect(Collectors.toList());
    }

    public Axis getXAxis(){
        return xAxis;
    }

    public Axis getYAxis(){
        return yAxis;
    }

    public Axis getZAxis(){
        return zAxis;
    }
}
