package generators;

import java.awt.*;
import java.util.Random;

public class AbruptRandomColors implements IColorGenerator {
    Random random = new Random();
    private final Color Black = new Color(0,0,0,255);
    private final Color Blue = new Color(0,0,255,255);
    private final Color Green = new Color(0,255,0,255);
    private final Color LightBlue = new Color(0,255,255,255);
    private final Color Red = new Color(255,0,0,255);
    private final Color Pink = new Color(255,0,255,255);
    private final Color Yellow = new Color(255,255,0,255);
    private final Color White = new Color(255,255,255,255);
    private final Color Gray = new Color(128,128,128,255);

    @Override
    public Color getColor() {
        var number = random.nextInt(7);
        switch (number){
            case 0: return Black;
            case 1: return Blue;
            case 2: return Green;
            case 3: return LightBlue;
            case 4: return Red;
            case 5: return Pink;
            case 6: return Yellow;
            case 7: return White;
            default: return Gray;
        }
    }
}
