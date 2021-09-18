package utils;

import java.awt.*;

public interface IPixelImage {
    Color getPixel(int x, int y);

    int getWidth();

    int getHeight();

    void setPixel(int x, int y, Color color);
}
