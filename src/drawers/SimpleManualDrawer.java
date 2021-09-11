package drawers;

import java.awt.*;

public class SimpleManualDrawer implements IManualDrawer{

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.fillRect(x,y,4,4);
    }
}
