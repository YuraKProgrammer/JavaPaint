package drawers;

import generators.IColorGenerator;
import javafx.scene.Scene;

import java.awt.*;
import java.util.Random;

public class Rays implements IDrawer{
    Random random = new Random();

    public Rays(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }

    IColorGenerator colorGenerator;

    public void draw(Graphics2D g, int width, int height) {
        var x1 = width/2;
        var y1 = height/2;
        for (var i = 0; i < 100+random.nextInt(100); i++) {
            g.setColor(colorGenerator.getColor());
            var x2 = random.nextInt(width);
            var y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
