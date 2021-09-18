package drawers.bitmapOperations;

import drawers.IBitmapOperation;
import javafx.scene.image.PixelWriter;

import java.awt.image.BufferedImage;

public class InvertOperation implements IBitmapOperation {
    @Override
    public void process(BufferedImage image, PixelWriter writer) {
        for (var x=0; x<image.getWidth(); x++){
            for (var y=0; y<image.getHeight(); y++){
                var color = image.getRGB(x,y);
                writer.setArgb(x,y,color/2);
            }
        }
    }
}
