package sample;

import drawers.Filler;
import drawers.IDrawer;
import drawers.RandomRectangels;
import generators.RandomColors;
import generators.SmallChangesRandomColors;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller {
    @FXML
    private ImageView _imageView;
    @FXML
    private MenuItem _miFillBlack;
    @FXML
    private MenuItem _miFillWhite;
    @FXML
    private MenuItem _miRandRects;

    private BufferedImage _image;

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
        this.scene = scene;
        _image = createImage(800, 600, Color.BLUE);
        var image = SwingFXUtils.toFXImage(_image, null);
        _imageView.setImage(image);
        _miFillBlack.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.BLACK);
            drawImage(drawer);
        });
        _miFillWhite.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.WHITE);
            drawImage(drawer);
        });
        _miRandRects.setOnAction(actionEvent -> {
            var drawer = new RandomRectangels(new SmallChangesRandomColors());
            drawImage(drawer);
        });
    }

    private void drawImage(IDrawer drawer) {
        Graphics2D g = _image.createGraphics();
        try {
            drawer.draw(g, _image.getWidth(), _image.getHeight());
            _imageView.setImage(SwingFXUtils.toFXImage(_image, null));
        } catch (Exception e) {
            Main.showError(e);
        } finally {
            g.dispose();
        }
    }

}
