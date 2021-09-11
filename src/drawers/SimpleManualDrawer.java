package drawers;

import java.awt.*;

public class SimpleManualDrawer implements IManualDrawer{

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.fillRect(x,y,size,size);
    }
}
