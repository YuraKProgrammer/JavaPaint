package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller {
    @FXML
    private ImageView _imageView;

    private static BufferedImage createImage (int width, int height, Color color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    private Scene scene;

    public void setScene(Scene scene) {
        var image = SwingFXUtils.toFXImage(createImage(200, 100, Color.BLUE), null);
        this.scene = scene;
        _imageView.setImage(image);
    }
}
