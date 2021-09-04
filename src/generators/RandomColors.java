package generators;

import java.awt.*;
import java.util.Random;

public class RandomColors implements IColorGenerator{
    Random random = new Random();

    @Override
    public Color getColor() {
        var r = random.nextInt(256);
        var g = random.nextInt(256);
        var b = random.nextInt(256);
        var a = random.nextInt(256);
        return new Color(r, g, b, a);
    }
}
