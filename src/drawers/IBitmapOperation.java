package drawers;

import javafx.scene.image.PixelWriter;

import java.awt.image.BufferedImage;

public interface IBitmapOperation {
    void process(BufferedImage image, PixelWriter writer);
}
