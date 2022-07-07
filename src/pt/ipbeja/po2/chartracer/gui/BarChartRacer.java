
package pt.ipbeja.po2.chartracer.gui;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;
import pt.ipbeja.po2.chartracer.model.View.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @version 21/06/2022
 * @number 21938 & 20347
 */

public class BarChartRacer implements View {
    private static final double WIDTH = 900;
    private static final double HEIGHT = WIDTH / 1.5;
    City city;
    Thread thread;
    List<City> cities; // list of cities
    List<City> specificSet; // list of specific set
    List<String> infoOfFirstColumn; // info about the the first data in the first column of each line
    Map<String, Integer> numberOfCities; // get the number of cities
    CheckMenuItem sub1;
    MenuItem choose;
    List<String> file = new ArrayList<>();
    private static List<String> newFile;
    private int number; // store the number of cities
    private double height;
    private double x;
    private double y;
    private int space;
    private double maxValue;
    private double width;
    private long numberOfSets;
    private Label label;
    private Label yearLabel;
    private int counterLabel;
    private VBox vBox;
    private BarModel barModel;
    private String accessTime;
    private String lastTimeAccess;
    Group root;
    Colors colorsType;
    CheckMenuItem skinsTypes;
    CheckMenuItem skinsTypes2;
    CheckMenuItem skinsTypes3;
    VBox vBoxTitle;
    VBox vBoxSubTitle;
    VBox vboxSource;


    public BarChartRacer(BarModel barModel) {
        this.barModel = barModel;
        this.barModel.setView(this);
    }


    public void dynamicBarChart(Pane pane, List<String> file) {
        barModel = new BarModel();
        barModel.setView(this);
        this.thread = new Thread(() -> {


            numberOfSets = barModel.getNumberOfSets(file);

            for (int i = 0; i < numberOfSets; i++) {
                // set the data

                counterLabel = i;
                setDataFile(file, i);

                // set the values to rectangle
                setValues();

                this.city = new City(specificSet); // create a new object from class City
                barModel.callSkins();

                // create the rectangles and insert in the pane
                createRectangleWithText(pane);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


    }


    /**
     * @param pane pane to show the draw
     */
    public void createRectangleWithText(Pane pane) {
        List<VBox> vBoxes = new ArrayList<>();
        List<Rectangle> rectangleList = new ArrayList<>();
        List<Label> labels = new ArrayList<>();

        Platform.runLater(() -> {
            for (int j = 0; j < number; j++) {

                Rectangle rectangle = rectangle(j);

                // Labels
                setLabels(j);

                // TextVbox
                setTextInVbox(j);


                // insert in pane
                rectangleList.add(rectangle);
                labels.add(label);
                vBoxes.add(vBox);
                y += HEIGHT / number;


            }
            insertInPane(pane,rectangleList,vBoxes,labels); // to insert in pane
        });
    }


    public void setValues() {
        this.height = HEIGHT / number;
        this.x = 20; // position x
        this.y = 100; // y start at zero
        this.space = (int) ((HEIGHT / number) / number); // the space between the rectangles
        this.maxValue = barModel.getMaxValue(specificSet); // get the max value of all data
    }

    public void setDataFile(List<String> file, int i) {
        cities = barModel.citiesList(file); // list of the file
        infoOfFirstColumn = barModel.findAllFirstData(cities); // info of the first information in each line
        numberOfCities = barModel.getNumberOfCities(file); // get the number of cities
        barModel.sortCities(cities); // sort the list of cities
        specificSet = barModel.getSpecificSet(cities, infoOfFirstColumn.get(i)); // specific set
        number = numberOfCities.get(infoOfFirstColumn.get(i));
    }

    /**
     *
     * @param j the index to generate the different texts
     */
    public void setTextInVbox(int j) {
        String text = specificSet.get(j).getCityName();
        // Text
        Text txt = new Text(text);
        txt.prefHeight(height);
        txt.setFont(Font.font(18));
        txt.setTextAlignment(TextAlignment.RIGHT);

        // Vbox to put the text inside
        vBox = new VBox(txt);
        vBox.setLayoutX(x);
        vBox.setLayoutY(y);
        vBox.setPrefHeight(height);
        vBox.setPrefWidth(width);
        vBox.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     *
     * @param j the index to generate the different labels
     */
    public void setLabels(int j) {
        // Label
        String l = Integer.toString(specificSet.get(j).getQty());
        label = new Label(l);
        label.setLayoutX(width + height);
        label.setLayoutY((y + height / 4) - space);
        label.setFont(Font.font(18));

        yearLabel = new Label(infoOfFirstColumn.get(counterLabel));
        yearLabel.setLayoutY(WIDTH - HEIGHT);
        yearLabel.setLayoutX(WIDTH);
        yearLabel.setFont(Font.font(100));

        Label titleLabel = new Label(getFile().get(0));
        titleLabel.setFont(Font.font(30));
        vBoxTitle = new VBox(titleLabel);
        vBoxTitle.setLayoutY(0);
        vBoxTitle.setLayoutX((WIDTH / 4 ) - space);
        vBoxTitle.setAlignment(Pos.TOP_CENTER);

        Label subTitleLabel = new Label(getFile().get(1));
        subTitleLabel.setFont(Font.font(18));
        vBoxSubTitle = new VBox(subTitleLabel);
        vBoxSubTitle.setLayoutY(WIDTH / (number + space));
        vBoxSubTitle.setLayoutX(x);
        vBoxSubTitle.setAlignment(Pos.BASELINE_LEFT);

        Label sourceLabel = new Label(getFile().get(2));
        sourceLabel.setFont(Font.font(18));
        sourceLabel.setDisable(true);
        vboxSource = new VBox(sourceLabel);
        vboxSource.setLayoutY(WIDTH - 520);
        vboxSource.setLayoutX(WIDTH);
        vboxSource.setAlignment(Pos.CENTER);
    }


    /**
     * @param j the index to get the specific city
     * @return return a rectangle
     */
    public Rectangle rectangle(int j) {
        width = this.barModel.getTheSpecificSpace(maxValue, HEIGHT, (specificSet.get(j).getQty()));

        Color color1 = colorsType.colorType(j);

        Rectangles rectangles = new Rectangles(x, y, width, height, color1);

        return rectangles.getRectangle();
    }

    /**
     *
     * @param pane pane to show the barChart
     * @param info the file to be read and get the the information
     */
    public void staticBarChart(Pane pane, List<String> info) { // req 3
        this.barModel = new BarModel(); // instance of the class barModel
        setDataFile(info, 0); // to get the first set of the file
        setValues(); // to set the values to the rectangle
        createRectangleWithText(pane); // create the rectangle and put it in pane
    }

    /**
     *
     * @return the pane WIDTH
     */
    public static double getWIDTH() {
        return WIDTH;
    }

    /**
     *
     * @return the pane HEIGHT
     */
    public static double getHEIGHT() {
        return HEIGHT;
    }


    /**
     * @param stage the stage to show the scene
     */
    public void chooseTheFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose the file");


        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Texts File", "*.txt"));

        File initialDirectory = new File(".");

        chooser.setInitialDirectory(initialDirectory);

        File db = chooser.showOpenDialog(stage);


        try {
            this.file = Files.readAllLines(Paths.get(db.getAbsolutePath()));
            // transform in a list
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "It's not possible open the file");
            alert.show();
            e.printStackTrace();
        }

        // to get the information about the file
        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(
                    Paths.get(db.getAbsolutePath()), BasicFileAttributes.class);
            this.accessTime = fileAttributes.creationTime().toString().substring(0, 10);
            this.lastTimeAccess = fileAttributes.lastAccessTime().toString().substring(0, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createMenu() {
        //Creating a menu
        Menu fileMenu = new Menu("Data");
        Menu fileMenu2 = new Menu("Skin");
        //Creating menu Items
        Menu view = new Menu("Statistic Data");
        choose = new MenuItem("Choose the File");
        // to skins
        Menu skins = new Menu("Skins");
        //Creating menu items for the sub item edit
        sub1 = new CheckMenuItem("Generate File");
        // skins checks menu
        skinsTypes = new CheckMenuItem("Skin1");
        skinsTypes2 = new CheckMenuItem("Skin2");
        skinsTypes3 = new CheckMenuItem("Skin3");
        //Adding sub items to the edit
        view.getItems().add(sub1);
        skins.getItems().addAll(skinsTypes, skinsTypes2, skinsTypes3);
        //Adding all the menu items to the menu
        fileMenu.getItems().addAll(view, choose);
        fileMenu2.getItems().addAll(skins);
        //Creating a menu bar and adding menu to it.
        MenuBar menuBar = new MenuBar(fileMenu, fileMenu2);
        menuBar.setTranslateX(10);
        menuBar.setTranslateY(20);
        menuBar.setTranslateX(20);
        //Setting the stage
        root = new Group(menuBar);


    }

    /**
     *
     * @return return the time of access of the current file
     */
    public String getAccessTime() {
        return accessTime;
    }
    /**
     *
     * @return return the last time of access of the current file
     */
    public String getLastTimeAccess() {
        return lastTimeAccess;
    }

    @Override
    public void setStatisticData(List<String> statisticData) {
        cities = barModel.citiesList(getFile());
        newFile = new ArrayList<>();
        for (String s : statisticData) {
            switch (s) {
                case "Number of data sets in file: ??" -> {
                    s = s.replace("??", String.valueOf(barModel.getNumberOfSets(getFile())));
                    newFile.add(s);
                }
                case "First date: YYYY/MM/DD" -> {
                    s = s.replace("YYYY/MM/DD", getAccessTime());
                    newFile.add(s);
                }
                case "Last date: YYYY/MM/DD" -> {
                    s = s.replace("YYYY/MM/DD", getLastTimeAccess());
                    newFile.add(s);
                }
                case "Average number of lines in each data set: ??" -> {
                    s = s.replace("??", String.valueOf(barModel.averageOfLines(cities, this)));
                    newFile.add(s);
                }
                case "Number of columns in each data set: ??" -> {
                    s = s.replace("??", String.valueOf(barModel.getColumns(cities)));
                    newFile.add(s);
                }
                case "Maximum value considering all data sets: ??" -> {
                    s = s.replace("??",
                            String.valueOf(barModel.getMaxValue(barModel.citiesList(getFile()))));
                    newFile.add(s);
                }
                case "Minimum value considering all data sets: ??" -> {
                    s = s.replace("??", String.valueOf(barModel.getMinValue(barModel.citiesList(getFile()))));
                    newFile.add(s);
                }
            }
        }
        if (getSub1().isSelected()) {
            this.barModel.writeStatisticData();
        }
    }

    @Override
    public void generateSkins() {
        if (skinsTypes.isSelected()) {
            colorsType = new ColorByCityName(barModel, this, city); // to generate color

        } else if (skinsTypes2.isSelected()) {
            colorsType = new ColorsByContinent(barModel, this, city);

        } else if (skinsTypes3.isSelected()) {
            colorsType = new ColorsByYear(barModel, this, city);
        } else {
            colorsType = new ColorByCityName(barModel, this, city); // to generate color
        }
    }

    /**
     *
     * @return return the read file
     */
    public List<String> getFile() {
        return this.file;
    }

    /**
     *
     * @return the Menus root
     */
    public Group getRoot() {
        return root;
    }

    public CheckMenuItem getSub1() {
        return sub1;
    }

    /**
     *
     * @return a menuItem
     */
    public MenuItem getChoose() {
        return choose;
    }

    /**
     *
     * @return the tha will be generate to pass the statisticData information
     */
    public static List<String> getNewFile() {
        return newFile;
    }


    public void insertInPane(Pane pane, List<Rectangle> rectangleList, List<VBox> vBoxes,
                             List<Label> labels) {

        pane.getChildren().clear();
        pane.getChildren().addAll(rectangleList); // Add Vboxes
        pane.getChildren().addAll(vBoxes);
        pane.getChildren().addAll(labels);
        pane.getChildren().addAll(yearLabel);
        pane.getChildren().addAll(vBoxTitle,vBoxSubTitle,vboxSource);
    }
}
