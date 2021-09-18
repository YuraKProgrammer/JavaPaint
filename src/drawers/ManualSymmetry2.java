package drawers;

import java.awt.*;

public class ManualSymmetry2 implements IManualDrawer{
    public void setWidth(int width) {
        this.width = width;
    }

    private int width = 600;


    public void setHeight(int height) {
        this.height = height;
    }

    private int height = 550;
    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.fillRect(x,y,size,size);
        g.fillRect(width-x,y,size,size);
        g.fillRect(x,height-y,size,size);
        g.fillRect(width-x,height-y,size,size);
    }
}
