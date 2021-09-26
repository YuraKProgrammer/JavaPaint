package drawers.bitmapOperations;

import utils.IPixelImage;


public class HorMirrorOperation implements IBitmapOperation{
    @Override
    public void process(IPixelImage pixelImage) {
        for (var x=0; x<pixelImage.getWidth()/2; x++){
            for (var y=0; y<pixelImage.getHeight(); y++){
                var color = pixelImage.getPixel(x,y);
                var color2 = pixelImage.getPixel(pixelImage.getWidth()-x-1,y);
                pixelImage.setPixel(pixelImage.getWidth()-x-1, y, color);
                pixelImage.setPixel(x, y, color2);
            }
        }
    }
}
