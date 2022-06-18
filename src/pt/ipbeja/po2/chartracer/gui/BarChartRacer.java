
package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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


    public void barRectangle(Pane pane, List<String> file) {


        List<City> cities; // list of cities
        List<City> specificSet; // list of specific set

        cities = City.citiesList(file); // list of the file

        List<String> infoOfFirstColumn = City.findAllFirstData(cities); // info of the first information in each line

        Map<String, Integer> numberOfCities = City.getNumberOfCities(file); // get the number of cities
        int number = numberOfCities.get(infoOfFirstColumn.get(0));

        double height = HEIGHT / number;
        double x = 2;
        double y = 0; // y start at zero
        int space = (int) ((HEIGHT / number) / number);

        City.sortCities(cities); // sort the list of cities

        specificSet = City.getSpecificSet(cities, "2018"); // specific set

        double maxValue = City.getMaxValue(specificSet);


        for (int i = 0; i < number; i++) {

            double width = getTheSpecificSpace(maxValue, HEIGHT, (specificSet.get(i).getQty()));

            this.city = new City(specificSet);
            List<Integer> numbersColor = city.getNumbers();

            Color color = Color.rgb(((numbersColor.get(i) * number) % 255)
                    ,(numbersColor.get(i) * number) % 255,numbersColor.get(i)); // generate The color to the barChart

            // the space between the bars used in the sub width-space = to reduce the width and get some
            //space
            createRectangleWithText(pane, x, y, width, height - space, color,
                    specificSet.get(i).getCityName());
            y += (HEIGHT / number);
        }
    }


    /**
     * @param pane pane to show the draw
     * @param x x position
     * @param y y position
     * @param height the height of the rectangle
     * @param width  the width of the rectangle
     * @param color  color to fill the rectangle
     * @param text   text to show the name
     */
    public void createRectangleWithText(Pane pane, double x, double y,
                                               double width, double height, Color color, String text) {

        Rectangle rectangle = new Rectangle(x, y, width, height );
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
        pane.getChildren().addAll(rectangle, vBox);
    }


    /**
     * @param maxValue the max value that is present in the file
     * @param height   the height of the pane
     * @param value        the qty that we get from the class City
     * @return return the
     */
    public double getTheSpecificSpace(double maxValue, double height, double value) {
        return (height * value) / maxValue;
    }

    /**
     *
     * @param stage the stage to show the file
     * @return return a List<String> of the chosen file
     */
    public List<String> chooseTheFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Escolha O ficheiro");
        List<String> file = new ArrayList<>();

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Ficheiros de texto", "*.txt"));

        File initialDirectory = new File(".");

        chooser.setInitialDirectory(initialDirectory);

        File db = chooser.showOpenDialog(stage);

        try {
            file = Files.readAllLines(Paths.get(db.getAbsolutePath()));

            // transform in a list

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Não foi possível abrir o ficheiro");
            alert.show();
            e.printStackTrace();
        }
        return file;
    }

}
