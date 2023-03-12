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
        graphicsContext.setImageSmoothing(true);
    }

    public void clear(int color){
        rasterizer.clear(color);
    }

    public void render(){
        rasterizer.writePixelData(graphicsContext.getPixelWriter());
    }

    public void rasterize(List<Point4D> triangles){
        Geometry3DUtils.iterateTriangles(triangles, rasterizer::fillTriangle);
    }

    public void rasterizeWireframe(List<Point4D> triangles){
        Geometry3DUtils.iterateTrianglesAsGrid(triangles, (p0, p1) -> rasterizer.drawLine(p0, p1, 0xff000000));
    }

    public void drawLine(Point4D p0, Point4D p1, int color){
        rasterizer.drawLine(p0, p1, color);
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
