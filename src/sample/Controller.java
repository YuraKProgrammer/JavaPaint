package sample;

import drawers.*;
import drawers.Frame;
import drawers.bitmapOperations.IBitmapOperation;
import drawers.bitmapOperations.InvertOperation;
import generators.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import utils.PixelImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private MenuItem _miParab;
    @FXML
    private MenuItem _miHyper;
    @FXML
    private MenuItem _miCircle;
    @FXML
    private CheckMenuItem _miRandomColors;
    @FXML
    private CheckMenuItem _miSmallChangesRandom;
    @FXML
    private CheckMenuItem _miAbruptRandomColors;
    @FXML
    private CheckMenuItem _miRandomGrayColors;
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
    private MenuItem _miManualSymmetry;
    @FXML
    private MenuItem _miManualSymmetryFalse;
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
    @FXML
    private MenuItem _miWhiteFrame;
    @FXML
    private MenuItem _miGrayFrame;
    @FXML
    private MenuItem _miRedFrame;
    @FXML
    private MenuItem _miGreenFrame;
    @FXML
    private MenuItem _miBlueFrame;
    @FXML
    private MenuItem _miYellowFrame;
    @FXML
    private CheckMenuItem _miSymmetryDrawer;
    @FXML
    private MenuItem _miLoadImage;
    @FXML
    private MenuItem _miInvert;

    private BufferedImage _image;

    private IColorGenerator colorGenerator = new RandomColors();

    private double size = 5;

    private boolean sizeGrow = false;

    private boolean sizeDecrease = false;

    private int md = 1;

    private boolean symmetry = false;

    private List<CheckMenuItem> colorGroup = new ArrayList<>();

    private List<CheckMenuItem> manualDrawerGroup = new ArrayList<>();

    private List<CheckMenuItem> sizeGroup = new ArrayList<>();

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

    private static BufferedImage loadImage (String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        }
        catch (Exception e){
            return null;
        }
    }

    private Scene scene;

    private void selectItem(List<CheckMenuItem> items, CheckMenuItem itemToSelect){
        for (var m:items) {
            m.setSelected(m==itemToSelect);
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        scene.widthProperty().addListener((obs, oldVal, newVal) -> resize());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> resize());
        resize();

        colorGroup.add(_miRandomColors);
        colorGroup.add(_miSmallChangesRandom);
        colorGroup.add(_miAbruptRandomColors);
        colorGroup.add(_miRandomGrayColors);
        colorGroup.add(_miBlackColor);
        colorGroup.add(_miWhiteColor);
        colorGroup.add(_miRedColor);
        colorGroup.add(_miGreenColor);

        manualDrawerGroup.add(_miManualRays);
        manualDrawerGroup.add(_miSimpleManualDrawer);
        manualDrawerGroup.add(_miSimpleManualDrawer2);
        manualDrawerGroup.add(_miSymmetryDrawer);

        sizeGroup.add(_miSize5);
        sizeGroup.add(_miSize10);
        sizeGroup.add(_miSize30);
        sizeGroup.add(_miSize50);
        sizeGroup.add(_miSize100);
        sizeGroup.add(_miSizeGrow);
        sizeGroup.add(_miSizeDecrease);

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
                if (md==2 && symmetry){
                    var ManSym1 = new ManualSymmetry();
                    ManSym1.setWidth(_image.getWidth());
                    ManSym1.setHeight(_image.getHeight());
                    manualDrawer=ManSym1;
                }
                if (md==1 && symmetry){
                    var ManSym2 = new ManualSymmetry2();
                    ManSym2.setWidth(_image.getWidth());
                    ManSym2.setHeight(_image.getHeight());
                    manualDrawer=ManSym2;
                }
                if (md==3 && symmetry){
                    var ManSym3 = new ManualSymmetry3();
                    ManSym3.setWidth(_image.getWidth());
                    ManSym3.setHeight(_image.getHeight());
                    manualDrawer=ManSym3;
                }
                if (manualDrawer!=_miManualRays)
                    manualDrawer.draw(mouseMoveGraphics,(int)(x-size/2),(int)(y-size/2), (int)size);
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
            selectItem(colorGroup,_miRandomColors);
        });
        _miSmallChangesRandom.setOnAction(actionEvent -> {
            colorGenerator=new SmallChangesRandomColors();
            selectItem(colorGroup,_miSmallChangesRandom);
        });
        _miAbruptRandomColors.setOnAction(actionEvent -> {
            colorGenerator=new AbruptRandomColors();
            selectItem(colorGroup,_miAbruptRandomColors);
        });
        _miRandomGrayColors.setOnAction(actionEvent -> {
            colorGenerator=new RandomGrayColors();
            selectItem(colorGroup,_miRandomGrayColors);
        });
        _miBlackColor.setOnAction(actionEvent -> {
            colorGenerator=new BlackColor();
            selectItem(colorGroup,_miBlackColor);
        });
        _miWhiteColor.setOnAction(actionEvent -> {
            colorGenerator=new WhiteColor();
            selectItem(colorGroup,_miWhiteColor);
        });
        _miRedColor.setOnAction(actionEvent -> {
            colorGenerator=new RedColor();
            selectItem(colorGroup,_miRedColor);
        });
        _miGreenColor.setOnAction(actionEvent -> {
            colorGenerator=new GreenColor();
            selectItem(colorGroup,_miGreenColor);
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
        _miParab.setOnAction(actionEvent -> {
            var drawer = new Parabola(colorGenerator);
            drawImage(drawer);
        });
        _miHyper.setOnAction(actionEvent -> {
            var drawer = new Hyperbola(colorGenerator);
            drawImage(drawer);
        });
        _miCircle.setOnAction(actionEvent -> {
            var drawer = new Circle(colorGenerator, manualDrawer);
            drawImage(drawer);
        });
        _miSimpleManualDrawer.setOnAction(actionEvent -> {
            manualDrawer=new SimpleManualDrawer();
            md=1;
            selectItem(manualDrawerGroup, _miSimpleManualDrawer);
        });
        _miSimpleManualDrawer2.setOnAction(actionEvent -> {
            manualDrawer=new SimpleManualDrawer2();
            md=2;
            selectItem(manualDrawerGroup, _miSimpleManualDrawer2);
        });
        _miManualRays.setOnAction(actionEvent -> {
            manualDrawer=new ManualRays();
            md=3;
            selectItem(manualDrawerGroup, _miManualRays);
        });
        _miSymmetryDrawer.setOnAction(actionEvent -> {
            manualDrawer=new SymmetryManualDrawer(new ManualRays(), _image.getWidth(), _image.getHeight());
            selectItem(manualDrawerGroup, _miSymmetryDrawer);
        });
        _miManualSymmetry.setOnAction(actionEvent -> {
                symmetry = true;
        });
        _miManualSymmetryFalse.setOnAction(actionEvent -> {
            symmetry = false;
        });
        _miSize5.setOnAction(actionEvent -> {
            size=5;
            sizeGrow=false;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSize5);
        });
        _miSize10.setOnAction(actionEvent -> {
            size=10;
            sizeGrow=false;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSize10);
        });
        _miSize30.setOnAction(actionEvent -> {
            size=30;
            sizeGrow=false;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSize30);
        });
        _miSize50.setOnAction(actionEvent -> {
            size=50;
            sizeGrow=false;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSize50);
        });
        _miSize100.setOnAction(actionEvent -> {
            size=100;
            sizeGrow=false;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSize100);
        });
        _miSizeGrow.setOnAction(actionEvent -> {
            size=1;
            sizeGrow=true;
            sizeDecrease=false;
            selectItem(sizeGroup,_miSizeGrow);
        });
        _miSizeDecrease.setOnAction(actionEvent -> {
            size=300;
            sizeDecrease=true;
            sizeGrow=false;
            selectItem(sizeGroup,_miSizeDecrease);
        });
        _miBlackFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.BLACK);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miWhiteFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.WHITE);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miGrayFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.GRAY);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miRedFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.RED);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miGreenFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.GREEN);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miBlueFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.BLUE);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miYellowFrame.setOnAction(actionEvent -> {
            var drawer = new Frame();
            drawer.setColor(Color.YELLOW);
            drawer.setColorIn(backgroundColor);
            drawImage(drawer);
        });
        _miLoadImage.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open image");
            var file = fileChooser.showOpenDialog(this.scene.getWindow());
            if (file != null) {
                _image = loadImage(file.getAbsolutePath());
                _imageView.setImage(SwingFXUtils.toFXImage(_image, null));
            }
        });
        _miInvert.setOnAction(actionEvent -> {
            doBitmapOperation(new InvertOperation());
        });
    }

    private void doBitmapOperation(IBitmapOperation operation) {
        operation.process(new PixelImage(_image));
        _imageView.setImage(SwingFXUtils.toFXImage(_image, null));
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
