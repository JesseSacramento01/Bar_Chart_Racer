
package pt.ipbeja.po2.chartracer.gui;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
 * @author Jessé Sacramento
 * @version 20/05/2022
 */
public class BarChartRacer {
    private final double WIDTH = 900;
    private final double HEIGHT = WIDTH / 1.5;
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
    private Color color;
    private String text;
    private int numberOfSets;
    private Label label;
    private Label yearLabel;
    private int counterLabel;
    private VBox vBox;


    public void barRectangle(Pane pane, List<String> file) {
        BarModel barModel = new BarModel();

        this.thread = new Thread(() -> {
            numberOfSets = barModel.getNumberOfYears(file);

        for (int i = 0; i < numberOfSets; i++) {
                // set the data
                 counterLabel = i;
                 setDataFile(file, i);

                // set the values to rectangle
                setValues();


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

                Rectangle rectangle = rectangleWithText(j);

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
            pane.getChildren().clear(); // to clear the last set in the pane
            pane.getChildren().addAll(rectangleList); // Add Vboxes
            pane.getChildren().addAll(vBoxes);
            pane.getChildren().addAll(labels);
            pane.getChildren().addAll(yearLabel);
        });
    }


    /**
     * @param maxValue the max value that is present in the file
     * @param height   the height of the pane
     * @param value    the qty that we get from the class City
     * @return return the
     */
    public double getTheSpecificSpace(double maxValue, double height, double value) {
        return (height * value) / maxValue;
    }


    public void setValues() {
        this.height = HEIGHT / number;
        this.x = 10; // position x
        this.y = 0; // y start at zero
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

    public Line makeLines(){
        Line line = new Line(600, y,600,200);
        return line;
    }


    public void setTextInVbox(int j){
        text = specificSet.get(j).getCityName();
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

    public void setLabels(int j){
        // Label
        String l = Integer.toString(specificSet.get(j).getQty());
        label = new Label(l);
        label.setLayoutX(width + height/2);
        label.setLayoutY((y + height/4) - space);
        label.setFont(Font.font(18));

        yearLabel= new Label(infoOfFirstColumn.get(counterLabel));
        yearLabel.setLayoutY(WIDTH - HEIGHT);
        yearLabel.setLayoutX(WIDTH);
        yearLabel.setFont(Font.font(100));

    }

    public Rectangle rectangleWithText(int j){
        width = getTheSpecificSpace(maxValue, HEIGHT, (specificSet.get(j).getQty()));

        this.city = new City(specificSet);
        List<Integer> numbersColor = city.getNumbers(); // hashcode

        color = Color.rgb(((numbersColor.get(j) * number) % 255)
                , (numbersColor.get(j) * number) % 255, numbersColor.get(j)); // generate The color to the barChart



        Rectangle rectangle = new Rectangle(x, y, width, height - space);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(color);

        return rectangle;
    }


}
