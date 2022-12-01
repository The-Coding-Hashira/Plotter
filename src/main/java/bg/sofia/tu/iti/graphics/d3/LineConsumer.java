package bg.sofia.tu.iti.graphics.d3;

import bg.sofia.tu.iti.graphics.d3.geometry.Point4D;

public interface LineConsumer{
    void accept(Point4D p0, Point4D p1);
}
