
package pt.ipbeja.po2.chartracer.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;
import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 20/05/2022
 */
public class BarChartRacer extends Application {
    private final double WIDTH  = 900;
    private final double HEIGHT = WIDTH / 1.5;
    private final double X = 0;
    BarModel barModel;



    public static void main(String[] args) {

        launch(args);

    }




    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setOnCloseRequest(
                e -> Platform.runLater( () -> {Platform.exit(); System.exit(0);} ) );

        Label label = new Label("Beijing");
        Label label1 = new Label("Tokyo");
        VBox vBox = new VBox(label,label1);
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);
        pane.getChildren().add(vBox);
        vBox.setSpacing(100);
        vBox.setLayoutX(800);
        vBox.setLayoutY(200);
        vBox.setAlignment(Pos.TOP_RIGHT);



        Scene scene = new Scene(pane,Color.AZURE);
        primaryStage.setScene(scene);
        barRectangle(pane);
        primaryStage.show();



    }

    public void barRectangle(Pane pane) throws IOException {


        List<String> linesRead = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                                            "\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));

        List<String> str = new ArrayList<>();

        List<City> cities = City.citiesList(linesRead);

        String referenceYear = cities.get(0).getYear();

        //List<Integer> numberOfCity = City.getNumberOfCities(linesRead);

        //City.writeCityFile(cities,numberOfCity.get(2018-referenceYear),2018,numberOfCity);

        //linesRead = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                                  //"\\21938_JesséSacramento_TP_PO2_2021-2022\\WrittenCities.txt"));


             double numberOfCities = 12;



             double maxValue = 38194;

             double width = HEIGHT / numberOfCities;
             double y = 0;


            List<Rectangle> rectangles = new ArrayList<>();

            for (int i =0; i < numberOfCities; i++){
                cities = City.citiesList(linesRead);

                System.out.println(cities);

                City.sortCities(cities);

                List<City> specificSet = City.getSpecificSet(cities,"1500");

                System.out.println(specificSet);

                double height = getTheSpecificSpace(maxValue,HEIGHT,(specificSet.get(i).getQty()));

                barModel = new BarModel();
                List<Color> colors = barModel.getColors();
                int randomNumber = barModel.generateRandomNumber(colors.size());

                // the space between the bars used in the sub width-space = to reduce the width and get some
                //space
                int space = (int) ((HEIGHT / numberOfCities) / numberOfCities);
                rectangles.add(createRectangle(X, y ,height,width-space,colors.get(randomNumber)));
                y += (HEIGHT / numberOfCities);
            }



            pane.getChildren().addAll(rectangles);
        }


    /**
     *
     * @param x position in the axis x on pane
     * @param y position in the axis y on pane
     * @param height the height of rectangle
     * @param width the width of rectangle
     * @param color the color to fill the rectangle
     * @return return an object of the type rectangle
     */
    public Rectangle createRectangle(double x, double y, double height, double width, Color color){
        Rectangle rectangle = new Rectangle(x,y,height,width);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(color);
        return rectangle;
    }

    /**
     *
     * @param maxValue the max value that is present in the file
     * @param height the height of the pane
     * @param v the qty that we get from the class City
     * @return return the
     */
    public static double getTheSpecificSpace(double maxValue, double height, double v){
        return  (height * v) / maxValue;
    }




}
