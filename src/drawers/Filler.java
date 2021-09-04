package drawers;

import java.awt.*;

public class Filler implements IDrawer{
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color = Color.BLACK;

    public void draw(Graphics2D g, int width, int height) {
        g.setColor(color);
        g.fillRect(0, 0, width, height);
    }
}
