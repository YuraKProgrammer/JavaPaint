package drawers;

import java.awt.*;

public class SymmetryManualDrawer implements IManualDrawer{
    private final IManualDrawer drawer;
    private final int width;
    private final int height;
    private final int n=12;

    public SymmetryManualDrawer(IManualDrawer drawer, int width, int height){

        this.drawer = drawer;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        drawer.draw(g,x,y,size);
        drawer.draw(g,width-x,height-y,size);
        drawer.draw(g,width-x,y,size);
        drawer.draw(g,x,height-y,size);
        /*
        var centerX = width/2f;
        var centerY = height/2f;
        var radius = Math.min(centerX,centerY)*0.9f;
        for(var i=0; i<n; i++){
            var angle = 2*Math.PI*i/n;
            var dx  = radius*Math.cos(angle);
            var dy  = radius*Math.sin(angle);
            drawer.draw(g,x+(int)(centerX+dx),y+(int)(centerY+dy),size);

        }
         */
    }
}
