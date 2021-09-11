package sample;

import drawers.*;
import generators.IColorGenerator;
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
    @FXML
    private MenuItem _miRandLines;
    @FXML
    private MenuItem _miSin;

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
        scene.widthProperty().addListener((obs, oldVal, newVal) -> resize());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> resize());
        resize();

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
        _miRandLines.setOnAction(actionEvent -> {
            var drawer = new Rays(new RandomColors());
            drawImage(drawer);
        });
        _miSin.setOnAction(actionEvent -> {
            var drawer = new Sinus(new SmallChangesRandomColors());
            drawImage(drawer);
        });
    }

    private void resize() {
        _image = createImage((int) scene.getWidth(), (int) scene.getHeight(), Color.black);
        _imageView.setImage(SwingFXUtils.toFXImage(_image, null));
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
