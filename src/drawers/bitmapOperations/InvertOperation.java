package drawers.bitmapOperations;

import utils.IPixelImage;

import java.awt.*;

public class InvertOperation implements IBitmapOperation {
    @Override
    public void process(IPixelImage pixelImage) {
        for (var x=0; x<pixelImage.getWidth(); x++){
            for (var y=0; y<pixelImage.getHeight(); y++){
                var color = pixelImage.getPixel(x,y); // берём цвет исходного изображения
                pixelImage.setPixel(x, y, getNewColor(color)); // сохраняем изменённый цвет
            }
        }
    }

    private Color getNewColor(Color c) {
        return new Color( // переворачиваем цвета
                255- c.getRed(),
                255- c.getGreen(),
                255- c.getBlue(),
                c.getAlpha());
    }
}
