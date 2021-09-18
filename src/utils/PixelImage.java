package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelImage implements IPixelImage {

    BufferedImage image;

    public PixelImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public Color getPixel(int x, int y) {
        int rgb = image.getRGB(x, y);
        return new Color(rgb, true);
    }

    @Override
    public void setPixel(int x, int y, Color color) {
        image.setRGB(x, y, color.getRGB());
    }
}
