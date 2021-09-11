package drawers;

import generators.IColorGenerator;

import java.awt.*;
import java.util.Random;

public class RandomCircles implements IDrawer{
    public RandomCircles(IColorGenerator colorGenerator){
            this.colorGenerator = colorGenerator;
        }
        IColorGenerator colorGenerator;
        Random random = new Random();

        @Override
        public void draw(Graphics2D g, int width, int height) {
            for (var i = 0; i < 100+random.nextInt(100); i++) {
                var r = 2 + random.nextInt(width/10);
                var x = random.nextInt(width);
                var y = random.nextInt(height);
                g.setColor(colorGenerator.getColor());
                g.fillOval(x,y,r,r);
            }
        }
    }
