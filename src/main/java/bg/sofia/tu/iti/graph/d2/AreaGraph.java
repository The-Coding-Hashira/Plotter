package bg.sofia.tu.iti.graph.d2;

import bg.sofia.tu.iti.graph.core.range.CalculationRange;
import bg.sofia.tu.iti.graph.core.range.Range;
import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.math.function.Function;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AreaGraph extends Graph{
    private final List<Double> xCoordinates;
    private final List<Double> yCoordinates;
    private final List<Double> xValues;
    private final List<Double> yValues;
    private final double       from;
    private final double       to;

    public AreaGraph(GraphicsContext graphicsContext, Dimension2D plotAreaDimension, Function function, double from,
                     double to){
        super(graphicsContext, plotAreaDimension, function);
        xCoordinates = new ArrayList<>();
        yCoordinates = new ArrayList<>();
        xValues      = new ArrayList<>();
        yValues      = new ArrayList<>();
        this.from    = from;
        this.to      = to;
    }

    @Override
    public void paint(){
        if(xCoordinates.isEmpty() || yCoordinates.isEmpty()){
            return;
        }
        GraphicsContext graphicsContext = getGraphicsContext();
        Color           stroke          = Color.DODGERBLUE;
        Color           fill            = stroke.deriveColor(1, 1, 1, 0.05);

        graphicsContext.beginPath();
        graphicsContext.moveTo(getPlotAreaDimension().getStartX() + xCoordinates.get(0),
                               getPlotAreaDimension().getEndY() - yCoordinates.get(0));
        for(int i = 0; i < xCoordinates.size(); i++){
            graphicsContext.lineTo(getPlotAreaDimension().getStartX() + xCoordinates.get(i),
                                   getPlotAreaDimension().getEndY() - yCoordinates.get(i));
        }
        graphicsContext.closePath();

        graphicsContext.setLineDashes(0);
        graphicsContext.setStroke(stroke);
        graphicsContext.stroke();
        graphicsContext.setFill(fill);
        graphicsContext.fill();
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
            if(argument >= from){
                if(argument <= to){
                    arguments.push(argument);
                    yValues.add(getFunction().calculate(arguments)
                                             .getResult());
                    xValues.add(argument);
                }
                else{
                    break;
                }
            }
        }
    }

    private void updateXCoordinates(double horizontalLowBoundary, double horizontalValuePerPixel){
        xCoordinates.clear();
        double lowBoundaryCoordinates = horizontalLowBoundary / horizontalValuePerPixel;
        for(double value : xValues){
            double pixelsOffset = value / horizontalValuePerPixel - lowBoundaryCoordinates;
            xCoordinates.add(pixelsOffset);
        }
        xCoordinates.add(xCoordinates.get(xCoordinates.size() - 1));
        xCoordinates.add(xCoordinates.get(0));
    }

    private void updateYCoordinates(double verticalLowBoundary, double verticalValuePerPixel){
        yCoordinates.clear();
        double lowBoundaryCoordinates = verticalLowBoundary / verticalValuePerPixel;
        for(double value : yValues){
            double pixelsOffset = value / verticalValuePerPixel - lowBoundaryCoordinates;
            yCoordinates.add(pixelsOffset);
        }
        yCoordinates.add(0 / verticalValuePerPixel - lowBoundaryCoordinates);
        yCoordinates.add(0 / verticalValuePerPixel - lowBoundaryCoordinates);
    }
}


