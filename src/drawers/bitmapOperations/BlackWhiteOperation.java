package drawers.bitmapOperations;

import utils.IPixelImage;

import java.awt.*;

public class BlackWhiteOperation implements IBitmapOperation{
    private final double q = Math.sqrt(255*255*3)/255;
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
        var brightness = (int)((Math.sqrt(c.getRed()*c.getRed()+c.getGreen()*c.getGreen()+c.getBlue()*c.getBlue())/q));
        return new Color(brightness,brightness,brightness, c.getAlpha());
    }
}
