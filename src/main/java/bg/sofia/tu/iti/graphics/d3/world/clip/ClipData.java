package bg.sofia.tu.iti.graphics.d3.world.clip;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

import java.util.ArrayList;
import java.util.List;

public class ClipData{
    private final List<Point4D> acceptedPoints;
    private final List<Point4D> rejectedPoints;

    public ClipData(){
        this.acceptedPoints = new ArrayList<>();
        this.rejectedPoints = new ArrayList<>();
    }

    public void addAcceptedPoint(Point4D point){
        acceptedPoints.add(point);
    }

    public void addRejectedPoint(Point4D point){
        rejectedPoints.add(point);
    }

    public List<Point4D> getAcceptedPoints(){
        return acceptedPoints;
    }

    public List<Point4D> getRejectedPoints(){
        return rejectedPoints;
    }
}
