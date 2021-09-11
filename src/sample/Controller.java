package sample;

import drawers.*;
import drawers.Frame;
import generators.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private MenuItem _miFillGray;
    @FXML
    private MenuItem _miFillRed;
    @FXML
    private MenuItem _miFillGreen;
    @FXML
    private MenuItem _miFillBlue;
    @FXML
    private MenuItem _miFillYellow;
    @FXML
    private MenuItem _miRandRects;
    @FXML
    private MenuItem _miRandCircs;
    @FXML
    private MenuItem _miRandLines;
    @FXML
    private MenuItem _miSin;
    @FXML
    private CheckMenuItem _miRandomColors;
    @FXML
    private CheckMenuItem _miSmallChangesRandom;
    @FXML
    private CheckMenuItem _miBlackColor;
    @FXML
    private CheckMenuItem _miWhiteColor;
    @FXML
    private CheckMenuItem _miRedColor;
    @FXML
    private CheckMenuItem _miGreenColor;
    @FXML
    private CheckMenuItem _miSimpleManualDrawer;
    @FXML
    private CheckMenuItem _miSimpleManualDrawer2;
    @FXML
    private CheckMenuItem _miManualRays;
    @FXML
    private CheckMenuItem _miSize5;
    @FXML
    private CheckMenuItem _miSize10;
    @FXML
    private CheckMenuItem _miSize30;
    @FXML
    private CheckMenuItem _miSize50;
    @FXML
    private CheckMenuItem _miSize100;
    @FXML
    private CheckMenuItem _miSizeGrow;
    @FXML
    private CheckMenuItem _miSizeDecrease;
    @FXML
    private MenuItem _miBlackFrame;

    private BufferedImage _image;

    private IColorGenerator colorGenerator = new RandomColors();

    private double size = 5;

    private boolean sizeGrow = false;

    private boolean sizeDecrease = false;

    private Color backgroundColor;

    private IManualDrawer manualDrawer = new SimpleManualDrawer();

    private Graphics2D mouseMoveGraphics;

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

        _miRandomColors.setSelected(true);

        _miSimpleManualDrawer.setSelected(true);

        _miSize5.setSelected(true);

        _imageView.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()){
                mouseMoveGraphics = _image.createGraphics();
            }
        });

        _imageView.setOnMouseReleased(e -> {
            if (mouseMoveGraphics!=null){
                mouseMoveGraphics.dispose();
                mouseMoveGraphics = null;
            }
        });


        _imageView.setOnMouseDragged(e -> {
            if (mouseMoveGraphics!=null){
                var x = e.getX();
                var y = e.getY();
                mouseMoveGraphics.setColor(colorGenerator.getColor());
                if (sizeGrow)
                    size=size+0.3;
                if (sizeDecrease)
                    size=size-0.3;
                if (size>300)
                    size=300;
                if (size<1)
                    size=1;
                manualDrawer.draw(mouseMoveGraphics,(int)x,(int)y, (int)size);
                _imageView.setImage(SwingFXUtils.toFXImage(_image, null));
            }
        });

        _miFillBlack.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.BLACK);
            backgroundColor=Color.BLACK;
            drawImage(drawer);
        });
        _miFillWhite.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.WHITE);
            backgroundColor=Color.WHITE;
            drawImage(drawer);
        });

        _miFillGray.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.GRAY);
            backgroundColor=Color.GRAY;
            drawImage(drawer);
        });
        _miFillRed.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.RED);
            backgroundColor=Color.RED;
            drawImage(drawer);
        });
        _miFillGreen.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.GREEN);
            backgroundColor=Color.GREEN;
            drawImage(drawer);
        });
        _miFillBlue.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.BLUE);
            backgroundColor=Color.BLUE;
            drawImage(drawer);
        });
        _miFillYellow.setOnAction(actionEvent -> {
            var drawer = new Filler();
            drawer.setColor(Color.YELLOW);
            backgroundColor=Color.YELLOW;
            drawImage(drawer);
        });

        _miRandomColors.setOnAction(actionEvent -> {
            colorGenerator=new RandomColors();
            _miRandomColors.setSelected(true);
            _miSmallChangesRandom.setSelected(false);
            _miBlackColor.setSelected(false);
            _miWhiteColor.setSelected(false);
            _miRedColor.setSelected(false);
            _miGreenColor.setSelected(false);
        });
        _miSmallChangesRandom.setOnAction(actionEvent -> {
            colorGenerator=new SmallChangesRandomColors();
            _miSmallChangesRandom.setSelected(true);
            _miRandomColors.setSelected(false);
            _miBlackColor.setSelected(false);
            _miWhiteColor.setSelected(false);
            _miRedColor.setSelected(false);
            _miGreenColor.setSelected(false);
        });
        _miBlackColor.setOnAction(actionEvent -> {
            colorGenerator=new BlackColor();
            _miSmallChangesRandom.setSelected(false);
            _miRandomColors.setSelected(false);
            _miBlackColor.setSelected(true);
            _miWhiteColor.setSelected(false);
            _miRedColor.setSelected(false);
            _miGreenColor.setSelected(false);
        });
        _miWhiteColor.setOnAction(actionEvent -> {
            colorGenerator=new WhiteColor();
            _miSmallChangesRandom.setSelected(false);
            _miRandomColors.setSelected(false);
            _miBlackColor.setSelected(false);
            _miWhiteColor.setSelected(true);
            _miRedColor.setSelected(false);
            _miGreenColor.setSelected(false);
        });
        _miRedColor.setOnAction(actionEvent -> {
            colorGenerator=new RedColor();
            _miSmallChangesRandom.setSelected(false);
            _miRandomColors.setSelected(false);
            _miBlackColor.setSelected(false);
            _miWhiteColor.setSelected(false);
            _miRedColor.setSelected(true);
            _miGreenColor.setSelected(false);
        });
        _miGreenColor.setOnAction(actionEvent -> {
            colorGenerator=new GreenColor();
            _miSmallChangesRandom.setSelected(false);
            _miRandomColors.setSelected(false);
            _miBlackColor.setSelected(false);
            _miWhiteColor.setSelected(false);
            _miRedColor.setSelected(false);
            _miGreenColor.setSelected(true);
        });
        _miRandRects.setOnAction(actionEvent -> {
            var drawer = new RandomRectangels(colorGenerator);
            drawImage(drawer);
        });
        _miRandCircs.setOnAction(actionEvent -> {
            var drawer = new RandomCircles(colorGenerator);
            drawImage(drawer);
        });
        _miRandLines.setOnAction(actionEvent -> {
            var drawer = new Rays(colorGenerator);
            drawImage(drawer);
        });
        _miSin.setOnAction(actionEvent -> {
            var drawer = new Sinus(colorGenerator);
            drawImage(drawer);
        });
        _miSimpleManualDrawer.setOnAction(actionEvent -> {
            manualDrawer=new SimpleManualDrawer();
            _miSimpleManualDrawer.setSelected(true);
            _miManualRays.setSelected(false);
            _miSimpleManualDrawer2.setSelected(false);
        });
        _miSimpleManualDrawer2.setOnAction(actionEvent -> {
            manualDrawer=new SimpleManualDrawer2();
            _miSimpleManualDrawer.setSelected(false);
            _miManualRays.setSelected(false);
            _miSimpleManualDrawer2.setSelected(true);
        });
        _miManualRays.setOnAction(actionEvent -> {
            manualDrawer=new ManualRays();
            _miSimpleManualDrawer.setSelected(false);
            _miSimpleManualDrawer2.setSelected(false);
            _miManualRays.setSelected(true);
        });
        _miSize5.setOnAction(actionEvent -> {
            size=5;
            sizeGrow=false;
            sizeDecrease=false;
            _miSize5.setSelected(true);
            _miSize10.setSelected(false);
            _miSize30.setSelected(false);
            _miSize50.setSelected(false);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(false);
        });
        _miSize10.setOnAction(actionEvent -> {
            size=10;
            sizeGrow=false;
            sizeDecrease=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(true);
            _miSize30.setSelected(false);
            _miSize50.setSelected(false);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(false);
        });
        _miSize30.setOnAction(actionEvent -> {
            size=30;
            sizeGrow=false;
            sizeDecrease=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(false);
            _miSize30.setSelected(true);
            _miSize50.setSelected(false);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(false);
        });
        _miSize50.setOnAction(actionEvent -> {
            size=50;
            sizeGrow=false;
            sizeDecrease=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(false);
            _miSize30.setSelected(false);
            _miSize50.setSelected(true);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(false);
        });
        _miSize100.setOnAction(actionEvent -> {
            size=100;
            sizeGrow=false;
            sizeDecrease=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(false);
            _miSize30.setSelected(false);
            _miSize50.setSelected(false);
            _miSize100.setSelected(true);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(false);
        });
        _miSizeGrow.setOnAction(actionEvent -> {
            size=1;
            sizeGrow=true;
            sizeDecrease=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(false);
            _miSize30.setSelected(false);
            _miSize50.setSelected(false);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(true);
            _miSizeDecrease.setSelected(false);
        });
        _miSizeDecrease.setOnAction(actionEvent -> {
            size=300;
            sizeDecrease=true;
            sizeGrow=false;
            _miSize5.setSelected(false);
            _miSize10.setSelected(false);
            _miSize30.setSelected(false);
            _miSize50.setSelected(false);
            _miSize100.setSelected(false);
            _miSizeGrow.setSelected(false);
            _miSizeDecrease.setSelected(true);
        });
        _miBlackFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.BLACK);
            drawer.setColorIn(backgroundColor);
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
