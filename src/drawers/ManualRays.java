package drawers;

import java.awt.*;
import java.util.Random;

public class ManualRays implements IManualDrawer{
    Random random = new Random();
    @Override
    public void draw(Graphics2D g, int x, int y) {
        for (var i=0; i<10; i++) {
            var r = random.nextDouble() * 100;
            var a = random.nextDouble() * 2 * Math.PI;
            var dx = r * Math.cos(a);
            var dy = r * Math.sin(a);
            g.drawLine(x, y, (int) (x + dx), (int) (y + dy));
        }
    }
}
