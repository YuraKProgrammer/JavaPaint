package drawers;

import java.awt.*;
import java.util.Random;

public class ManualSymmetry3 implements IManualDrawer{
    private Random random = new Random();

    public void setWidth(int width) {
        this.width = width;
    }

    private int width = 600;


    public void setHeight(int height) {
        this.height = height;
    }

    private int height = 550;
    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        for (var i=0; i<10; i++) {
            var r = random.nextDouble() * size;
            var a = random.nextDouble() * 2 * Math.PI;
            var dx = r * Math.cos(a);
            var dy = r * Math.sin(a);
            g.drawLine(x, y, (int) (x + dx), (int) (y + dy));
            g.drawLine(width-x, y, (int) (width-(x + dx)), (int) (y + dy));
            g.drawLine(x, height-y, (int) (x + dx), (int) (height-(y + dy)));
            g.drawLine(width-x, height-y, (int) (width-(x + dx)), (int) (height-(y + dy)));
        }
    }
}
