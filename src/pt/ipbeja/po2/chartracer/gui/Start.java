package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @version 21/06/2022
 * @number 21938 & 20347
 */

public class Start extends Application {

    BarChartRacer barChartRacer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setMaxHeight(BarChartRacer.getHEIGHT());
        pane.setMaxWidth(BarChartRacer.getWIDTH());
        Scene scene = new Scene(pane, Color.AZURE);

        BarModel barModel = new BarModel();
        this.barChartRacer = new BarChartRacer(barModel);
        this.barChartRacer.chooseTheFile(primaryStage);

        this.barChartRacer.dynamicBarChart(pane, this.barChartRacer.getFile()); // dynamicBarChart
        //this.barChartRacer.staticBarChart(pane,this.barChartRacer.getFile()); // staticBarChart
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest((e) -> System.exit(0));
        primaryStage.show();
    }
}
