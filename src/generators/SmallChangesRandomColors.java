package generators;

import java.awt.*;
import java.util.Random;

public class SmallChangesRandomColors implements IColorGenerator{
    Random random = new Random();
    int r = random.nextInt(256);
    int g = random.nextInt(256);
    int b = random.nextInt(256);
    int a = random.nextInt(256);
    int speed = 16;

    @Override
    public Color getColor() {
        r=change(r);
        g=change(g);
        b=change(b);
        a=change(a);
        return new Color(r, g, b, a);
    }

    private int change(int i){
        int j = i + random.nextInt(2 * speed) - speed;
        if (j<0)
            j=0;
        if (j>255)
            j=255;
        return j;
    }
}
