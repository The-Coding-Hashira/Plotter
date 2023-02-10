package bg.sofia.tu.iti.graphics.d3;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.nio.IntBuffer;

public class Rasterizer{
    private final int                    width;
    private final int                    height;
    private final int[]                  frameBuffer;
    private final double[]               depthBuffer;
    private final double                 maxDepth;
    private final WritableImage          writableImage;
    private final PixelWriter            pixelWriter;
    private final PixelFormat<IntBuffer> pixelFormat;

    public Rasterizer(int width, int height){
        this.width    = width;
        this.height   = height;
        frameBuffer   = new int[width * height];
        depthBuffer   = new double[width * height];
        maxDepth      = Double.POSITIVE_INFINITY;
        writableImage = new WritableImage(width, height);
        pixelWriter   = writableImage.getPixelWriter();
        pixelFormat   = PixelFormat.getIntArgbPreInstance();

        clearDepthBuffer();
    }

    private void clearDepthBuffer(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                setDepth(x, y, maxDepth);
            }
        }
    }

    private void setDepth(int x, int y, double depth){
        depthBuffer[x + (y * width)] = depth;
    }

    public void strokeTriangle(Point4D p0, Point4D p1, Point4D p2){
        int color = 0xff000000;
        drawLine(p0.getX(), p0.getY(), p0.getZ(), p1.getX(), p1.getY(), p1.getZ(), color);
        drawLine(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ(), color);
        drawLine(p2.getX(), p2.getY(), p2.getZ(), p0.getX(), p0.getY(), p0.getZ(), color);
    }

    public void drawLine(double p0x, double p0y, double p0z, double p1x, double p1y, double p1z, int color){
        int       x0    = (int) Math.ceil(p0x - 0.5);
        int       y0    = (int) Math.ceil(p0y - 0.5);
        final int x1    = (int) Math.floor(p1x + 0.5);
        final int y1    = (int) Math.floor(p1y + 0.5);
        double    z0    = p0z;
        int       dx    = Math.abs(x1 - x0);
        int       sx    = x0 < x1 ? 1 : -1;
        int       dy    = -Math.abs(y1 - y0);
        int       sy    = y0 < y1 ? 1 : -1;
        double    dz    = p1z - z0;
        double    dzdx  = dz / dx;
        double    dzdy  = dz / dy;
        int       error = dx + dy;

        while(true){
            if(isValidIndex(x0, y0)){
                if(isVisible(x0, y0, z0)){
                    setPixel(x0, y0, color);
                    setDepth(x0, y0, z0);
                }
            }
            if(x0 == x1 && y0 == y1){
                break;
            }
            int e2 = error << 1;
            if(e2 >= dy){
                if(x0 == x1){
                    break;
                }
                error += dy;
                x0 += sx;
                if(dx > dy){
                    z0 += dzdx;
                }
            }
            if(e2 <= dx){
                if(y0 == y1){
                    break;
                }
                error += dx;
                y0 += sy;
                if(dy > dx){
                    z0 += dzdy;
                }
            }
        }
    }

    private boolean isValidIndex(int x, int y){
        if(y < 0 || y >= height){
            return false;
        }
        return x >= 0 && x < width;
    }

    private boolean isVisible(int x, int y, double depth){
        return depthBuffer[x + (y * width)] >= depth;
    }

    private void setPixel(int x, int y, int color){
        frameBuffer[x + (y * width)] = color;
    }

    public void drawLine(Point4D p0, Point4D p1, int color){
        drawLine(p0.getX(), p0.getY(), p0.getZ(), p1.getX(), p1.getY(), p1.getZ(), color);
    }

    public void fillTriangle(Point4D p0, Point4D p1, Point4D p2){
        Color color0 = p0.getColor();
        Color color1 = p1.getColor();
        Color color2 = p2.getColor();

        final double c0a = color0.getOpacity();
        final double c0r = color0.getRed();
        final double c0g = color0.getGreen();
        final double c0b = color0.getBlue();
        final double c1a = color1.getOpacity();
        final double c1r = color1.getRed();
        final double c1g = color1.getGreen();
        final double c1b = color1.getBlue();
        final double c2a = color2.getOpacity();
        final double c2r = color2.getRed();
        final double c2g = color2.getGreen();
        final double c2b = color2.getBlue();

        final double p0x = p0.getX();
        final double p0y = p0.getY();
        final double p0z = p0.getZ();
        final double p1x = p1.getX();
        final double p1y = p1.getY();
        final double p1z = p1.getZ();
        final double p2x = p2.getX();
        final double p2y = p2.getY();
        final double p2z = p2.getZ();

        final double p12Ydif     = p1y - p2y;
        final double p02Xdif     = p0x - p2x;
        final double p21Xdif     = p2x - p1x;
        final double p20Ydif     = p2y - p0y;
        final double denominator = (p12Ydif * p02Xdif) + (p21Xdif * (p0y - p2y));
        final double oneOverD    = 1 / denominator;
        final double p02Zdif     = p0z - p2z;
        final double p12Zdif     = p1z - p2z;

        final double xMinAbs = Math.min(p0x, Math.min(p1x, p2x));
        final double yMinAbs = Math.min(p0y, Math.min(p1y, p2y));
        final double xMaxAbs = Math.max(p0x, Math.max(p1x, p2x));
        final double yMaxAbs = Math.max(p0y, Math.max(p1y, p2y));
        final int    xMinI   = (int) Math.ceil(xMinAbs - 0.5);
        final int    yMinI   = (int) Math.ceil(yMinAbs - 0.5);
        final int    xMaxI   = (int) Math.floor(xMaxAbs + 0.5);
        final int    yMaxI   = (int) Math.floor(yMaxAbs + 0.5);
        final int    xMin    = Math.max(xMinI, 0);
        final int    yMin    = Math.max(yMinI, 0);
        final int    xMax    = Math.min(xMaxI, width - 1);
        final int    yMax    = Math.min(yMaxI, height - 1);

        int    color;
        int    a;
        int    r;
        int    g;
        int    b;
        double u;
        double v;
        double w;
        double z;
        for(int y = yMin; y <= yMax; y++){
            for(int x = xMin; x <= xMax; x++){
                u = ((p12Ydif * (x - p2x)) + (p21Xdif * (y - p2y))) * oneOverD;
                if(u < 0 || u > 1){
                    continue;
                }
                v = ((p20Ydif * (x - p2x)) + (p02Xdif * (y - p2y))) * oneOverD;
                if(v < 0 || v > 1){
                    continue;
                }
                w = 1 - u - v;
                if(w < 0 || w > 1){
                    continue;
                }
                z = u * (p02Zdif) + v * (p12Zdif) + p2z;
                if(isVisible(x, y, z)){
                    a     = (int) ((c0a * u + c1a * v + c2a * w) * 255);
                    r     = (int) ((c0r * u + c1r * v + c2r * w) * 255);
                    g     = (int) ((c0g * u + c1g * v + c2g * w) * 255);
                    b     = (int) ((c0b * u + c1b * v + c2b * w) * 255);
                    color = (a << 24) | (r << 16) | (g << 8) | b;
                    setPixel(x, y, color);
                    setDepth(x, y, z);
                }
            }
        }
    }

    public void clear(int color){
        fill(color);
        clearDepthBuffer();
    }

    public void fill(int color){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                setPixel(x, y, color);
            }
        }
    }

    public Image toImage(){
        pixelWriter.setArgb(0, 0, 0xFF00FF00);
        pixelWriter.setPixels(0, 0, width, height, pixelFormat, frameBuffer, 0, width);
        return writableImage;
    }
}
