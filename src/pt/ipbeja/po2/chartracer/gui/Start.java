package pt.ipbeja.po2.chartracer.gui;/**
 * @author Jessé Sacramento
 * @version 15/06/2022
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Start extends Application {
    private final double WIDTH = 900;
    private final double HEIGHT = WIDTH / 1.5;

    BarChartRacer barChartRacer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        Scene scene = new Scene(pane, Color.AZURE);
        primaryStage.setScene(scene);

        this.barChartRacer = new BarChartRacer();
        List<String> info = this.barChartRacer.chooseTheFile(primaryStage);
        this.barChartRacer.barRectangle(pane,info);

        primaryStage.show();

    }
}
