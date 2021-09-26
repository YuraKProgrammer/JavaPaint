package drawers.bitmapOperations;

import utils.IPixelImage;

import java.awt.*;

public class DimmingOperation implements IBitmapOperation{
    private final float ratio = 1/2f;
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
        return new Color((int)(c.getRed()*ratio),(int)(c.getGreen()*ratio),(int)(c.getBlue()*ratio), c.getAlpha());
    }
}
