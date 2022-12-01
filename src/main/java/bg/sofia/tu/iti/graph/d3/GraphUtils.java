package bg.sofia.tu.iti.graph.d3;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import bg.sofia.tu.iti.graphics.d3.world.HeatMap;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;

public class GraphUtils{
    public static double findParameter(double value, double lowBoundary, double range){
        return (value - lowBoundary) / range;
    }

    public static double findParameterizedValue(double parameter, double lowBoundary, double range){
        return (parameter * range) + lowBoundary;
    }

    public static List<Point4D> mapToHeat(List<Point4D> points){
        HeatMap heatMap = new HeatMap(Color.BLUE,
                                      Color.RED,
                                      points.stream()
                                            .min(GraphUtils::compareZ)
                                            .get()
                                            .getZ(),
                                      points.stream()
                                            .max(GraphUtils::compareZ)
                                            .get()
                                            .getZ());
        //TODO figure out the heatmap thingy maybe refactoring will make things a lot easier
        return points.stream()
                     .map(point -> new Point4D(point.getX(),
                                               point.getY(),
                                               point.getZ(),
                                               heatMap.generateColor(point.getZ())))
                     .collect(Collectors.toList());
    }

    private static int compareZ(Point4D p1, Point4D p2){
        if(p1.getZ() > p2.getZ()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
