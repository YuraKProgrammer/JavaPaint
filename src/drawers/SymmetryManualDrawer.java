package drawers;

import java.awt.*;

public class SymmetryManualDrawer implements IManualDrawer{
    private final IManualDrawer drawer;
    private final int width;
    private final int height;
    private final int n=12;

    public SymmetryManualDrawer(IManualDrawer drawer, int width, int height){

        this.drawer = drawer;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        var centerX = width / 2f;
        var centerY = height / 2f;

        // определяем угол и расстояние до (x, y), если смотреть из центра
        var dx = x - centerX;
        var dy = y - centerY;
        var radius = Math.sqrt(dx*dx + dy*dy);
        var angle = Math.atan2(dy, dx);

        // поворачиваем точку N раз (вокруг центра)
        for (int i = 0; i < n; i++) {
            var a = angle + 2*Math.PI * i/n;
            var copyX = centerX + radius * Math.cos(a);
            var copyY = centerY + radius * Math.sin(a);
            drawer.draw(g,(int)copyX,(int)copyY,size);
        }
    }
}
