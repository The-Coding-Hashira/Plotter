package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.core.range.CalculationRange;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graph.d3.GraphUtils;
import bg.sofia.tu.iti.graphics.d2.geometry.Point2D;
import bg.sofia.tu.iti.math.function.Function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph2D{
    private final Axis          xAxis;
    private final Axis          yAxis;
    private final Function      function;
    private       List<Point2D> data;

    public Graph2D(Axis xAxis, Axis yAxis, Function function){
        this.xAxis    = xAxis;
        this.yAxis    = yAxis;
        this.function = function;
        data          = new ArrayList<>();
    }

    public void autosizeYAxis(double xPoints){
        calculateData(xPoints);
        List<Point2D> sortedData = data.stream()
                                       .sorted(Comparator.comparingDouble(Point2D::getY))
                                       .collect(Collectors.toList());
        double minY = sortedData.get(0)
                                .getY();
        double maxY = sortedData.get(sortedData.size() - 1)
                                .getY();
        yAxis.setRange(new Range(minY * 1.1, maxY * 1.1));
    }

    public void calculateData(double xPoints){
        //TODO add a proper parameterized function to generate points
        //TODO make a button to set Z range from max to min and render with proper heatmap
        Stack<Double> args = new Stack<>();
        data = new ArrayList<>((int) xPoints);
        CalculationRange calculationRange = new CalculationRange(xAxis.getRange()
                                                                      .getHighBoundary(),
                                                                 xAxis.getRange()
                                                                      .getLowBoundary(),
                                                                 xAxis.getRange()
                                                                      .calculate() / xPoints);
        for(double x = calculationRange.getLowBoundary(); x <= calculationRange.getHighBoundary(); x += calculationRange.getStep()){
            args.push(x);
            data.add(new Point2D(x,
                                 function.calculate(args)
                                         .getResult()));
        }
    }

    public List<Point2D> normalizeDataPoints(){
        double xLowBoundary = xAxis.getRange()
                                   .getLowBoundary();
        double xRange = xAxis.getRange()
                             .calculate();
        double yLowBoundary = yAxis.getRange()
                                   .getLowBoundary();
        double yRange = yAxis.getRange()
                             .calculate();
        return data.stream()
                   .map(point -> new Point2D(GraphUtils.findParameter(point.getX(), xLowBoundary, xRange),
                                             GraphUtils.findParameter(point.getY(), yLowBoundary, yRange)))
                   .collect(Collectors.toList());
    }

    public Axis getXAxis(){
        return xAxis;
    }

    public Axis getYAxis(){
        return yAxis;
    }

    public Function getFunction(){
        return function;
    }
}
