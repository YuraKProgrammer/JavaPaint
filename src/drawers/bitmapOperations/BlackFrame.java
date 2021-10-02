package drawers.bitmapOperations;

import utils.IPixelImage;

import java.awt.*;
public class BlackFrame implements IBitmapOperation{
    private float minRadius;
    private int centerX;
    private int centerY;

    @Override
    public void process(IPixelImage pixelImage) {
        minRadius=Math.min(pixelImage.getWidth(),pixelImage.getHeight())/2;
        centerX=pixelImage.getWidth()/2;
        centerY=pixelImage.getHeight()/2;
        for (var x=0; x<pixelImage.getWidth(); x++){
            for (var y=0; y<pixelImage.getHeight(); y++){
                var color = pixelImage.getPixel(x,y);
                pixelImage.setPixel(x, y, getNewColor(color, pixelImage.getWidth(), pixelImage.getHeight(), x, y));
            }
        }
    }

    private Color getNewColor(Color c, int width, int height, int x, int y) {
        var distance = Math.sqrt((centerX-x)*(centerX-x)+(centerY-y)*(centerY-y));
        distance = Math.min(distance,minRadius);
        var k = 1-distance/minRadius;
        int a = (int) (c.getAlpha() * k);
        return new Color(c.getRed(),c.getGreen(),c.getBlue(), a);
    }
}
