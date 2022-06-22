
package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.ipbeja.po2.chartracer.model.City;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jessé Sacramento
 * @version 20/05/2022
 */
public class BarChartRacer extends Thread {
    private final double WIDTH = 900;
    private final double HEIGHT = WIDTH / 1.5;
    City city;
    private List<String> file = new ArrayList<>();
    private Pane pane;
    private int num;
    Thread thread;


    public BarChartRacer(Pane pane, List<String> file) {
        this.pane = pane;
        this.file = file;
        barRectangle(this.pane, this.file);
    }


    public void barRectangle(Pane pane, List<String> file) {

        this.thread = new Thread(() -> {
            List<City> cities; // list of cities
            List<City> specificSet; // list of specific set


            cities = City.citiesList(file); // list of the file

            for (int i = 0; i < 518; i++) {

                List<String> infoOfFirstColumn = City.findAllFirstData(cities); // info of the first information in the first column of each line

                Map<String, Integer> numberOfCities = City.getNumberOfCities(file); // get the number of cities
                int number = numberOfCities.get(infoOfFirstColumn.get(i));

                double height = HEIGHT / number;
                double x = 10;
                double y = 0; // y start at zero
                int space = (int) ((HEIGHT / number) / number);

                City.sortCities(cities); // sort the list of cities

                specificSet = City.getSpecificSet(cities, infoOfFirstColumn.get(i)); // specific set

                double maxValue = City.getMaxValue(specificSet);


                for (int j = 0; j < number; j++) {

                    double width = getTheSpecificSpace(maxValue, HEIGHT, (specificSet.get(j).getQty()));

                    this.city = new City(specificSet);
                    List<Integer> numbersColor = city.getNumbers();

                    Color color = Color.rgb(((numbersColor.get(j) * number) % 255)
                            , (numbersColor.get(j) * number) % 255, numbersColor.get(j)); // generate The color to the barChart

                    // the space between the bars used in the sub width-space = to reduce the width and get some
                    //space
                    createRectangleWithText(pane, x, y, width, height - space, color,
                            specificSet.get(j).getCityName());


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    y += (HEIGHT / number);
                }
            }
        });
        thread.start();

    }


    /**
     * @param pane   pane to show the draw
     * @param x      x position
     * @param y      y position
     * @param height the height of the rectangle
     * @param width  the width of the rectangle
     * @param color  color to fill the rectangle
     * @param text   text to show the name
     */
    public void createRectangleWithText(Pane pane, double x, double y,
                                        double width, double height, Color color, String text) {
        Platform.runLater(() -> {

            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(color);

            // Text
            Text txt = new Text(text);
            txt.prefHeight(height);
            txt.setFont(Font.font(25));
            txt.setTextAlignment(TextAlignment.RIGHT);

            // Vbox to put the text inside
            VBox vBox = new VBox(txt);
            vBox.setLayoutX(x);
            vBox.setLayoutY(y);
            vBox.setPrefHeight(height);
            vBox.setPrefWidth(width);
            vBox.setAlignment(Pos.CENTER_RIGHT);

            // insert in pane
            pane.getChildren().addAll(rectangle,vBox);
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


    // getters

    /**
     * @return return the list file
     */
    public List<String> getFile() {
        return file;
    }

    public Pane getPane() {
        return pane;
    }

}
