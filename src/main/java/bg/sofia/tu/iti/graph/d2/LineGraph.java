package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.range.CalculationRange;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graphics.d2.geometry.Point2D;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LineGraph extends Graph{
    private final List<Point2D> coordinates;
    private final List<Double>  xValues;
    private final List<Double>  yValues;

    public LineGraph(GraphicsContext graphicsContext, Dimension2D plotAreaDimension, Function function){
        super(graphicsContext, plotAreaDimension, function);
        coordinates = new ArrayList<>();
        xValues     = new ArrayList<>();
        yValues     = new ArrayList<>();
    }

    @Override
    public void paint(){
        /*
        painter.paint();
        if(xCoordinates.isEmpty() || yCoordinates.isEmpty()){
            return;
        }
        GraphicsContext graphicsContext = getGraphicsContext();
        graphicsContext.setStroke(Color.DODGERBLUE);
        graphicsContext.setLineDashes(0);

        graphicsContext.beginPath();
        graphicsContext.moveTo(getPlotAreaDimension().getStartX() + xCoordinates.get(0),
                               getPlotAreaDimension().getEndY() - yCoordinates.get(0));
        for(int i = 0; i < xCoordinates.size(); i++){
            graphicsContext.lineTo(getPlotAreaDimension().getStartX() + xCoordinates.get(i),
                                   getPlotAreaDimension().getEndY() - yCoordinates.get(i));
        }
        graphicsContext.stroke();

         */
    }

    @Override
    public void horizontalRangeChanged(Range horizontalRange, Range verticalRange){
        CalculationRange calculationRange = new CalculationRange(horizontalRange.getHighBoundary(),
                                                                 horizontalRange.getLowBoundary(),
                                                                 horizontalRange.calculate() / getPlotAreaDimension().getWidth());
        updateValues(calculationRange);
        updateXCoordinates(horizontalRange.getLowBoundary(),
                           horizontalRange.calculate() / getPlotAreaDimension().getWidth());
        updateYCoordinates(verticalRange.getLowBoundary(),
                           verticalRange.calculate() / getPlotAreaDimension().getHeight());
    }

    @Override
    public void verticalRangeChanged(Range horizontalRange, Range verticalRange){
        CalculationRange calculationRange = new CalculationRange(horizontalRange.getHighBoundary(),
                                                                 horizontalRange.getLowBoundary(),
                                                                 horizontalRange.calculate() / getPlotAreaDimension().getWidth());
        updateValues(calculationRange);
        updateYCoordinates(verticalRange.getLowBoundary(),
                           verticalRange.calculate() / getPlotAreaDimension().getHeight());
    }

    private void updateValues(CalculationRange calculationRange){
        yValues.clear();
        xValues.clear();
        Stack<Double> arguments = new Stack<>();
        for(double argument = calculationRange.getLowBoundary(); argument <= calculationRange.getHighBoundary(); argument += calculationRange.getStep()){
            arguments.push(argument);
            yValues.add(getFunction().calculate(arguments)
                                     .getResult());
            xValues.add(argument);
            //TODO add if !isNaN cuz some functions will return NaN like division by zero or sqrt(<0)
        }
    }

    private void updateXCoordinates(double horizontalLowBoundary, double horizontalValuePerPixel){
        //xCoordinates.clear();
        //double lowBoundaryCoordinates = horizontalLowBoundary / horizontalValuePerPixel;
        //for(double value : xValues){
        //    double pixelsOffset = value / horizontalValuePerPixel - lowBoundaryCoordinates;
        //    xCoordinates.add(pixelsOffset);
        //}
    }

    private void updateYCoordinates(double verticalLowBoundary, double verticalValuePerPixel){
        //yCoordinates.clear();
        //double lowBoundaryCoordinates = verticalLowBoundary / verticalValuePerPixel;
        //for(Double value : yValues){
        //    double pixelsOffset = value / verticalValuePerPixel - lowBoundaryCoordinates;
        //    yCoordinates.add(pixelsOffset);
        //}
    }
}
