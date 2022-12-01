package bg.sofia.tu.iti.graph.d3;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.axis.TickGenerator;
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
    private final List<Point4D> data;
    private final Function      function;

    public Graph3D(Function function){
        TickGenerator tickGenerator = new TickGenerator(5);
        Range         range         = new Range(-10, 10);
        this.xAxis    = new Axis(range, tickGenerator);
        this.yAxis    = new Axis(range, tickGenerator);
        this.zAxis    = new Axis(range, tickGenerator);
        data          = new ArrayList<>();
        this.function = function;
    }

    public TickData generateTickData(){
        return new TickData(xAxis.generateTicks(), yAxis.generateTicks(), zAxis.generateTicks());
    }

    public void calculateData(){
        //TODO add a proper parameterized function to generate points
        //TODO make a button to set Z range from max to min and render with proper heatmap
        int gridResolution = 50;
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
        System.out.println("after calculation " + data.size());
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
}
