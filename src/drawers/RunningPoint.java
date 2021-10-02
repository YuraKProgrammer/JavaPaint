package drawers;

import generators.IColorGenerator;

import java.awt.*;
import java.util.Random;

public class RunningPoint implements IDrawer{
    Random random = new Random();
    IColorGenerator colorGenerator;

    private final int numberOfSteps = 100000;

    private final float startCoordinate = 0.5f;

    public RunningPoint(IColorGenerator colorGenerator){
        this.colorGenerator = colorGenerator;
    }

    @Override
    public void draw(Graphics2D g, int width, int height) {
        var x = (int)(width*startCoordinate);
        var y = (int)(height*startCoordinate);
        for (var i = 0; i < numberOfSteps; i++) {
            g.setColor(colorGenerator.getColor());
            g.fillRect(x,y,1,1);
            /*
            var randDirection = random.nextInt(5);
            switch (randDirection){
                case 0: x--; break;
                case 1: y--; break;
                case 2: x++; y++; break;
                case 3: x--; y++; break;
                case 4: x++; y--; break;
                case 5: x--; y--; break;
                default: x=x; y=y;
            }
             */
            switch (random.nextInt(2)){
                case 0:
                    switch (random.nextInt(4)){
                        case 0: y--; break;
                        case 1: x++; break;
                        case 2: y++; break;
                        case 3: x--; break;
                        default: break;
                    }
                case 1:
                    switch (random.nextInt(4)){
                        case 0: x++; y--; break;
                        case 1: x++; y++; break;
                        case 2: x--; y++; break;
                        case 3: x--; y--; break;
                        default: break;
                    }
            }
            if (x>width-1)
                x=width-1;
            if (y>height-1)
                y=height-1;
            if (x<0)
                x=0;
            if (y<0)
                y=0;
        }
    }
}
