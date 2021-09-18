package drawers;

import generators.IColorGenerator;

import java.awt.*;

public class Hyperbola implements IDrawer{
    IColorGenerator colorGenerator;

    public Hyperbola(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }

    private static final int penWidth = 5;

    private static final float step = 0.01f;

    private static final float size = 500;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        for (double x = -width/2; x<=width/2; x+=step) {
            g.setColor(colorGenerator.getColor());
            var y = (int)(size/x)+height/2;
            g.fillRect((int)(x+width/2),y,penWidth,penWidth);
        }
    }
}
