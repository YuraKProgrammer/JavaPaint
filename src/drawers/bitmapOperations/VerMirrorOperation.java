package drawers.bitmapOperations;

import utils.IPixelImage;

public class VerMirrorOperation implements IBitmapOperation{
    @Override
    public void process(IPixelImage pixelImage) {
        for (var x=0; x<pixelImage.getWidth(); x++){
            for (var y=0; y<pixelImage.getHeight()/2; y++){
                var color = pixelImage.getPixel(x,y);
                var color2 = pixelImage.getPixel(x,pixelImage.getHeight()-y-1);
                pixelImage.setPixel(x, pixelImage.getHeight()-y-1, color);
                pixelImage.setPixel(x, y, color2);
            }
        }
    }
}
