package drawers;

import generators.IColorGenerator;

import java.awt.*;

public class Parabola implements IDrawer{
    IColorGenerator colorGenerator;

    public Parabola(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }
    private static final int penWidth = 5;

    private static final float step = 0.1f;

    private static final float size = 1/10f;

    private static final float shift = 50;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        for (double x = -width/2; x<=width/2; x+=step) {
            g.setColor(colorGenerator.getColor());
            var y = (int)(Math.pow(x*size,2))+shift;
            g.fillRect(width-((int)x+width/2),(int)(height-y),penWidth,penWidth);
        }
    }
}

