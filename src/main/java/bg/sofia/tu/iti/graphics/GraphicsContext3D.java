package bg.sofia.tu.iti.graphics;

import bg.sofia.tu.iti.graphics.d2.world.Dimension2D;
import bg.sofia.tu.iti.graphics.d3.Geometry3DUtils;
import bg.sofia.tu.iti.graphics.d3.Rasterizer;
import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class GraphicsContext3D{
    private final GraphicsContext graphicsContext;
    private final Rasterizer      rasterizer;

    public GraphicsContext3D(GraphicsContext graphicsContext, Dimension2D canvasDimension){
        this.graphicsContext = graphicsContext;
        this.rasterizer      = new Rasterizer((int) canvasDimension.getWidth(), (int) canvasDimension.getHeight());
    }

    public void clear(int color){
        rasterizer.clear(color);
    }

    public void render(){
        graphicsContext.drawImage(rasterizer.toImage(), 0, 0);
    }

    public void rasterizeTriangleData(List<Point4D> data){
        Geometry3DUtils.iterateTriangles(data, rasterizer::fillTriangle);
        Geometry3DUtils.iterateTrianglesAsGrid(data, (p0, p1) -> rasterizer.drawLine(p0, p1, 0xff000000));
    }

    public void drawLine(Point4D from, Point4D to, int color){
        rasterizer.drawLine(from, to, color);
    }

    public void fillText(String text, double x, double y){
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setFont(Font.font("Calibri", 19));
        graphicsContext.fillText(text, x, y);
    }

    public int toInt(Color color){
        int intColor = ((int) (color.getOpacity() * 255)) << 24;
        intColor |= ((int) (color.getRed() * 255) << 16);
        intColor |= ((int) (color.getGreen() * 255) << 8);
        intColor |= (int) (color.getBlue() * 255);
        return intColor;
    }
}
