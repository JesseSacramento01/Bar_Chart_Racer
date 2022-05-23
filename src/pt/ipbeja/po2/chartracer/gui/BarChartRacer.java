package pt.ipbeja.po2.chartracer.gui;/**
 * @author Jessé Sacramento
 * @version 20/05/2022
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jdk.jfr.Label;
import pt.ipbeja.po2.chartracer.model.City;

import java.util.Arrays;
import java.util.List;

public class BarChartRacer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final double WIDTH  = 900;
    private final double HEIGHT = WIDTH / 1.5;
    private final double X = 0;
    private final double RECTANGLE_WIDTH = 40;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(
                e -> Platform.runLater( () -> {Platform.exit(); System.exit(0);} ) );

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);

        Scene scene = new Scene(pane,Color.AZURE);
        primaryStage.setScene(scene);
        barRectangle(pane);
        primaryStage.show();

    }

    public void barRectangle(Pane pane) {
        List<Rectangle> rectangles = Arrays.asList(createRectangle(X,0,700,RECTANGLE_WIDTH,Color.BLUE),
                createRectangle(X,50,620,RECTANGLE_WIDTH,Color.ORANGERED),
                createRectangle(X,100,580,RECTANGLE_WIDTH,Color.YELLOW),
                createRectangle(X,150,320,RECTANGLE_WIDTH,Color.GREEN),
                createRectangle(X,200,200,RECTANGLE_WIDTH,Color.PURPLE),
                createRectangle(X,250,139,RECTANGLE_WIDTH,Color.BLUE),
                createRectangle(X,300,128,RECTANGLE_WIDTH,Color.BROWN),
                createRectangle(X,350,111,RECTANGLE_WIDTH,Color.PINK),
                createRectangle(X,400,98,RECTANGLE_WIDTH,Color.DEEPPINK));

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
