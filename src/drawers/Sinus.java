package drawers;

import generators.IColorGenerator;

import java.awt.*;
import java.util.Random;

public class Sinus implements IDrawer{
    Random random = new Random();

    public Sinus(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }

    /**
     * Толщина пера
     */
    private static final int penWidth = 5;

    private static final float amplitude = 50;

    private static final float freq = 1f/16;

    private static final float step = 0.1f;

    IColorGenerator colorGenerator;
    public void draw(Graphics2D g, int width, int height) {
        for (double x = 0; x<=width; x+=step) {
            g.setColor(colorGenerator.getColor());
            var y = (int)(amplitude*Math.sin(x*freq)+height/2);
            g.fillRect((int)x,y,penWidth,penWidth);
        }
    }
}
