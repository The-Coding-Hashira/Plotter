package bg.sofia.tu.iti.gui.canvas.region;

import bg.sofia.tu.iti.graph.core.axis.Axis;
import bg.sofia.tu.iti.graph.d2.axis.AxisPainter;
import bg.sofia.tu.iti.gui.event.CanvasRegionEventHandler;
import bg.sofia.tu.iti.gui.event.axis.AxisEventHandlerFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public abstract class AxisCanvasRegion implements CanvasRegion{
    private final Axis                     axis;
    private final CanvasRegionEventHandler canvasRegionEventHandler;
    private final AxisPainter              axisPainter;

    public AxisCanvasRegion(int ticks, AxisEventHandlerFactory axisEventHandlerFactory, AxisPainter axisPainter){
        this.axis                     = new Axis(null,null);
        this.canvasRegionEventHandler = axisEventHandlerFactory.create(axis);
        this.axisPainter              = axisPainter;
    }

    public abstract double getPixelRange();

    @Override
    public boolean containsPoint(double x, double y){
        return axisPainter.getDimension()
                          .containsPoint(x, y);
    }

    @Override
    public void paint(){
        axisPainter.paint();
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent){
        canvasRegionEventHandler.onMousePressed(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent){
        canvasRegionEventHandler.onMouseDragged(mouseEvent);
    }

    @Override
    public void onMouseScrolled(ScrollEvent scrollEvent){
        canvasRegionEventHandler.onMouseScrolled(scrollEvent);
    }

    public Axis getAxis(){
        return axis;
    }

    public AxisPainter getAxisPainter(){
        return axisPainter;
    }
    //    @Override
    //    public void tickValuesUpdated(List<Double> values){
    //        tickCoordinates.clear();
    //        Range  axisRange              = axisTicksManager.getRange();
    //        double valuePerPixel          = axisRange.calculate() / getPixelRange();
    //        double lowBoundaryCoordinates = axisRange.getLowBoundary() / valuePerPixel;
    //        for(double tickValue : values){
    //            double pixelsOffset = tickValue / valuePerPixel - lowBoundaryCoordinates;
    //            tickCoordinates.put(tickValue, pixelsOffset);
    //        }
    //    }

    //TODO when scrolling set an equal value change for both axes, cuz now Y has less pixels so value is higher
    // and changes more than X. Also maybe make Square canvas, or when constructing x range should be
    // pixels*verticalValuePerPixel so that it is technically square but looking from 16:9 canvas

    //    @Override
    //    public void moveRange(double pixels){
    //        double horizontalMoveValue = pixels * getValuePerPixel();
    //        super.moveRange(-horizontalMoveValue);
    //    }
    //
    //    @Override
    //    public void expandRange(double pixels){
    //        super.expandRange(pixels * getValuePerPixel());
    //    }
    //
    //    @Override
    //    public void shrinkRange(double pixels){
    //        super.shrinkRange(pixels * getValuePerPixel());
    //    }

    //    private double getValuePerPixel(){
    //        return getRangeValue() / getPixelRange();
    //    }
}
