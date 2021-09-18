package drawers;

import generators.IColorGenerator;

import java.awt.*;

public class Circle implements IDrawer{
    IColorGenerator colorGenerator;

    IManualDrawer manualDrawer;

    public Circle(IColorGenerator colorGenerator, IManualDrawer manualDrawer){
        this.colorGenerator = colorGenerator;
        this.manualDrawer = manualDrawer;
    }

    private static final int penWidth = 15;

    private static final float step = 0.1f;

    private static final int n = 100;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        var centerX = width/2f;
        var centerY = height/2f;
        var radius = Math.min(centerX,centerY)*0.9f;
        for(var i=0; i<n; i++){
            var angle = 2*Math.PI*i/n;
            var dx  = radius*Math.cos(angle);
            var dy  = radius*Math.sin(angle);
            var x = (int)(centerX+dx-penWidth/2);
            var y = (int)(centerY+dy-penWidth/2);
            g.setColor(colorGenerator.getColor());
            //g.fillRect(x,y,penWidth,penWidth);
            manualDrawer.draw(g,x,y,penWidth);
        }
    }
}
