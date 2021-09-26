package drawers.bitmapOperations;

import utils.IPixelImage;

import java.awt.*;
import java.util.Random;

public class NoiseOperation implements IBitmapOperation{
    private final int strength = 60;
    private final Random random = new Random();

    @Override
    public void process(IPixelImage pixelImage) {
        for (var x=0; x<pixelImage.getWidth(); x++){
            for (var y=0; y<pixelImage.getHeight(); y++){
                var color = pixelImage.getPixel(x,y);
                pixelImage.setPixel(x, y, getNewColor(color));
            }
        }
    }

    private Color getNewColor(Color c) {
        int r = change(c.getRed());
        int g = change(c.getGreen());
        int b = change(c.getBlue());
        return new Color(
                r, g, b, c.getAlpha());
    }

    private int change(int v){
        v=v+random.nextInt(strength)-strength/2;
        if (v>255)
            v=255;
        if (v<0)
            v=0;
        return v;
    }
}
