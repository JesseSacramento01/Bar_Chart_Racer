
package pt.ipbeja.po2.chartracer.gui;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @version 21/06/2022
 * @number 21938 & 20347
 */

public class BarChartRacer {
    private final double WIDTH = 900;
    private final double HEIGHT = WIDTH / 1.5;
    private final static int RGB_NUMBER = 255;
    City city;
    Thread thread;
    List<City> cities; // list of cities
    List<City> specificSet; // list of specific set
    List<String> infoOfFirstColumn; // info about the the first data in the first column of each line
    Map<String, Integer> numberOfCities; // get the number of cities
    private int number; // store the number of cities
    private double height;
    private double x;
    private double y;
    private int space;
    private double maxValue;
    private double width;
    private int numberOfSets;
    private Label label;
    private Label yearLabel;
    private int counterLabel;
    private VBox vBox;
    private BarModel barModel;


    public void dynamicBarChart(Pane pane, List<String> file) {
        barModel = new BarModel();

        this.thread = new Thread(() -> {
            numberOfSets = barModel.getNumberOfYears(file);

            for (int i = 0; i < numberOfSets; i++) {
                // set the data

                counterLabel = i;
                setDataFile(file, i);

                // set the values to rectangle
                setValues();

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
            pane.getChildren().clear();
            pane.getChildren().addAll(rectangleList); // Add Vboxes
            pane.getChildren().addAll(vBoxes);
            pane.getChildren().addAll(labels);
            pane.getChildren().addAll(yearLabel);

        });
    }

    public void setValues() {
        this.height = HEIGHT / number;
        this.x = 20; // position x
        this.y = 20; // y start at zero
        this.space = (int) ((HEIGHT / number) / number); // the space between the rectangles
        this.maxValue = City.getMaxValue(specificSet); // get the max value of all data
    }

    public void setDataFile(List<String> file, int i) {
        cities = City.citiesList(file); // list of the file
        infoOfFirstColumn = City.findAllFirstData(cities); // info of the first information in each line
        numberOfCities = City.getNumberOfCities(file); // get the number of cities
        City.sortCities(cities); // sort the list of cities
        specificSet = City.getSpecificSet(cities, infoOfFirstColumn.get(i)); // specific set
        number = numberOfCities.get(infoOfFirstColumn.get(i));
    }


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

    public void setLabels(int j) {
        // Label
        String l = Integer.toString(specificSet.get(j).getQty());
        label = new Label(l);
        label.setLayoutX(width + height / 2);
        label.setLayoutY((y + height / 4) - space);
        label.setFont(Font.font(18));

        yearLabel = new Label(infoOfFirstColumn.get(counterLabel));
        yearLabel.setLayoutY(WIDTH - HEIGHT);
        yearLabel.setLayoutX(WIDTH);
        yearLabel.setFont(Font.font(100));

    }

    /**
     * @param j the index to get the specific city
     * @return return a rectangle
     */
    public Rectangle rectangle(int j) {
        width = this.barModel.getTheSpecificSpace(maxValue, HEIGHT, (specificSet.get(j).getQty()));

        this.city = new City(specificSet); // create a new object from class City
        List<Integer> numbersColor = city.getNumbers(); // hashcode

        Color color = Color.rgb(((numbersColor.get(j) * number) % RGB_NUMBER)
                , (numbersColor.get(j) * number) % RGB_NUMBER, numbersColor.get(j)); // generate The color to the barChart


        Rectangle rectangle = new Rectangle(x, y, width, height - space);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(color);

        return rectangle;
    }

    public void staticBarChart(Pane pane, List<String> info){ // req 3
        this.barModel = new BarModel(); // instance of the class barModel
        setDataFile(info,0); // to get the first set of the file
        setValues(); // to set the values to the rectangle
        createRectangleWithText(pane); // create the rectangle and put it in pane
    }


}
