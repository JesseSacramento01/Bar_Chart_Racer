package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.BarModel;

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
        pane.setPrefSize(BarChartRacer.getWIDTH(), BarChartRacer.getHEIGHT());


        BarModel barModel = new BarModel();
        this.barChartRacer = new BarChartRacer(barModel);


        this.barChartRacer.createMenu();
        // to show the menu
        Scene scene1 = new Scene(this.barChartRacer.getRoot(), 800, 500, Color.AZURE);
        primaryStage.setScene(scene1);
        primaryStage.setOnCloseRequest((e) -> System.exit(0));
        primaryStage.show();


        this.barChartRacer.getChoose().setOnAction(actionEvent -> {
            this.barChartRacer.chooseTheFile(primaryStage);
            this.barChartRacer.dynamicBarChart(pane, this.barChartRacer.getFile()); // dynamicBarChart
            Scene scene = new Scene(pane, Color.AZURE);
            //this.barChartRacer.staticBarChart(pane,this.barChartRacer.getFile()); // staticBarChart

            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest((e) -> System.exit(0));
            primaryStage.show();

        });

    }


}
