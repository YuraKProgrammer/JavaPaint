package drawers;

import java.awt.*;

public class SimpleManualDrawer2 implements IManualDrawer{
    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.fillOval(x,y,size,size);
    }
}
