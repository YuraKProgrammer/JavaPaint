package generators;

import java.awt.*;
import java.util.Random;

public class RandomGrayColors implements IColorGenerator{
    Random random = new Random();
    @Override
    public Color getColor() {
        var tin = random.nextInt(252)+2;
        return new Color(tin,tin,tin,255);
    }
}
