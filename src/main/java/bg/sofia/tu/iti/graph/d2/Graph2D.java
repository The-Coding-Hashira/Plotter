package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.range.CalculationRange;
import bg.sofia.tu.iti.gui.canvas.region.AxisCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.HorizontalAxisCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.PlotAreaCanvasRegion;
import bg.sofia.tu.iti.gui.canvas.region.VerticalAxisCanvasRegion;
import bg.sofia.tu.iti.graph.d2.painter.Graph2DPainter;
import bg.sofia.tu.iti.graph.core.axis.tick.Tick;
import bg.sofia.tu.iti.math.function.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph2D{
    private final HorizontalAxisCanvasRegion xAxisCanvasRegion;
    private final VerticalAxisCanvasRegion   yAxisCanvasRegion;
    private final PlotAreaCanvasRegion       plotArea;
    private final Graph2DPainter             graph2DPainter;
    private final Function                   function;

    public Graph2D(HorizontalAxisCanvasRegion xAxisCanvasRegion, VerticalAxisCanvasRegion yAxisCanvasRegion,
                   PlotAreaCanvasRegion plotArea, Function function){
        this.xAxisCanvasRegion = xAxisCanvasRegion;
        this.yAxisCanvasRegion = yAxisCanvasRegion;
        this.plotArea          = plotArea;
        graph2DPainter         = new Graph2DPainter(plotArea.getPlotAreaPainter());
        this.function          = function;
    }

    public void paint(){
        graph2DPainter.paintLineForCoordinates(calculateYCoordinates(calculateValues(findCalculationRange())));
        paintGraphElements();
    }

    private CalculationRange findCalculationRange(){
        return new CalculationRange(xAxisCanvasRegion.getAxis()
                                                     .getRange()
                                                     .getHighBoundary(),
                                    xAxisCanvasRegion.getAxis()
                                                     .getRange()
                                                     .getLowBoundary(),
                                    xAxisCanvasRegion.getAxis()
                                                     .getRange()
                                                     .calculate() / plotArea.getPlotAreaPainter()
                                                                            .getDimension()
                                                                            .getWidth());
    }

    private List<Double> calculateValues(CalculationRange calculationRange){
        List<Double>  values    = new ArrayList<>();
        Stack<Double> arguments = new Stack<>();
        for(double argument = calculationRange.getLowBoundary(); argument <= calculationRange.getHighBoundary(); argument += calculationRange.getStep()){
            arguments.push(argument);
            values.add(function.calculate(arguments)
                               .getResult());
            //TODO add if !isNaN cuz some functions will return NaN like division by zero or sqrt(<0)
        }
        return values;
    }

    private List<Double> calculateYCoordinates(List<Double> values){
        List<Double> yCoordinates = new ArrayList<>();
        //TODO use point2d cuz not all funcs have results across all of X and therefore it will be wrong
        double verticalValuePerPixel = calculateValuePerPixel(yAxisCanvasRegion.getAxis()
                                                                               .getRange()
                                                                               .calculate(),
                                                              yAxisCanvasRegion.getPixelRange());
        double lowBoundaryCoordinates = yAxisCanvasRegion.getAxis()
                                                         .getRange()
                                                         .getLowBoundary() / verticalValuePerPixel;
        for(Double value : values){
            double pixelsOffset = value / verticalValuePerPixel - lowBoundaryCoordinates;
            yCoordinates.add(pixelsOffset);
        }
        return yCoordinates;
    }

    private List<Tick> calculateAxisTicks(AxisCanvasRegion axisCanvasRegion, List<Double> tickValues){
        double valuePerPixel = calculateValuePerPixel(axisCanvasRegion.getAxis()
                                                                      .getRange()
                                                                      .calculate(), axisCanvasRegion.getPixelRange());
        double lowBoundaryCoordinates = axisCanvasRegion.getAxis()
                                                        .getRange()
                                                        .getLowBoundary() / valuePerPixel;
        return tickValues.stream()
                         .map(tickValue -> {
                             double pixelsOffset = tickValue / valuePerPixel - lowBoundaryCoordinates;
                             //                             return new Tick(tickValue, pixelsOffset);
                             return new Tick(0, 0, null);
                         })
                         .collect(Collectors.toList());
    }

    private double calculateValuePerPixel(double valueRange, double pixelRange){
        return valueRange / pixelRange;
    }

    private double calculatePixelPerValue(double valueRange, double pixelRange){
        return pixelRange / valueRange;
    }

    private void paintGraphElements(){
//        List<Tick> xTicks = calculateAxisTicks(xAxisCanvasRegion,
//                                               xAxisCanvasRegion.getAxis()
//                                                                .generateTicks());
//        List<Tick> yTicks = calculateAxisTicks(yAxisCanvasRegion,
//                                               yAxisCanvasRegion.getAxis()
//                                                                .generateTicks());
//        plotArea.getPlotAreaPainter()
//                .paintGridLines(xTicks, yTicks);
//        xAxisCanvasRegion.getAxisPainter()
//                         .paintTicks(xTicks);
//        yAxisCanvasRegion.getAxisPainter()
//                         .paintTicks(yTicks);
    }
}
