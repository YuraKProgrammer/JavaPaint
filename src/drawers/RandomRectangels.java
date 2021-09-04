package drawers;

import generators.IColorGenerator;

import java.awt.*;
import java.util.Random;

public class RandomRectangels implements IDrawer{
    Random random = new Random();
    IColorGenerator colorGenerator;

    public RandomRectangels(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }

    @Override
    public void draw(Graphics2D g, int width, int height) {
        for (var i = 0; i < 100+random.nextInt(100); i++) {
            var w = 2 + random.nextInt(width/10);
            var h = 2 + random.nextInt(height/10);
            var x = random.nextInt(width);
            var y = random.nextInt(height);
            g.setColor(colorGenerator.getColor());
            g.fillRect(x,y,w,h);
        }
    }
}
