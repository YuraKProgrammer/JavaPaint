package drawers;

import java.awt.*;

public class Frame implements IDrawer{
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color = Color.WHITE;

    public void setColorIn(Color colorIn) {
        this.colorIn = colorIn;
    }

    private Color colorIn = Color.WHITE;

    private static final int nxy = 12;

    public void draw(Graphics2D g, int width, int height) {
        g.setColor(color);
        g.fillRect(0,0,width, height);
        g.setColor(colorIn);
        g.fillRect(nxy,nxy,width-2*nxy,height-2*nxy);
    }
}
