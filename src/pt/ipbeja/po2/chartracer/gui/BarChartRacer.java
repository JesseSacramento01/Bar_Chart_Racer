
package pt.ipbeja.po2.chartracer.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.City;

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

    public static void main(String[] args) {
        launch(args);
    }

    private final double WIDTH  = 900;
    private final double HEIGHT = WIDTH / 1.5;
    private final double X = 0;
    private final double RECTANGLE_WIDTH = 40;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setOnCloseRequest(
                e -> Platform.runLater( () -> {Platform.exit(); System.exit(0);} ) );

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);

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

        int referenceYear = cities.get(0).getYear();

        List<Integer> numberOfCity = City.getNumberOfCities(linesRead);

        City.writeCityFile(cities,numberOfCity.get(2018-referenceYear),2018,numberOfCity);

       linesRead = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                                  "\\21938_JesséSacramento_TP_PO2_2021-2022\\WrittenCities.txt"));



        for (String s: linesRead){
            str.add(s.split(",")[3]);
        }


            List<Rectangle> rectangles = Arrays.asList(createRectangle(X, 0, Integer.parseInt(str.get(0)), RECTANGLE_WIDTH, Color.BLUE),
                    createRectangle(X, 50, Integer.parseInt(str.get(1)), RECTANGLE_WIDTH, Color.ORANGERED),
                    createRectangle(X, 100, Integer.parseInt(str.get(2)), RECTANGLE_WIDTH, Color.YELLOW),
                    createRectangle(X, 150, Integer.parseInt(str.get(3)), RECTANGLE_WIDTH, Color.GREEN),
                    createRectangle(X, 200, Integer.parseInt(str.get(4)), RECTANGLE_WIDTH, Color.PURPLE),
                    createRectangle(X, 250, Integer.parseInt(str.get(5)), RECTANGLE_WIDTH, Color.BLUE),
                    createRectangle(X, 300, Integer.parseInt(str.get(6)), RECTANGLE_WIDTH, Color.BROWN),
                    createRectangle(X, 350, Integer.parseInt(str.get(7)), RECTANGLE_WIDTH, Color.PINK),
                    createRectangle(X, 400, Integer.parseInt(str.get(8)), RECTANGLE_WIDTH, Color.DEEPPINK));

            pane.getChildren().addAll(rectangles);
        }


    /**
     *
     * @param x position in the axis x on pane
     * @param y position int the axis y on pane
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
}
