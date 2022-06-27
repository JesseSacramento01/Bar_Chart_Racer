package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Scene scene = new Scene(pane, Color.AZURE);

        this.barChartRacer = new BarChartRacer();
        List<String> info = chooseTheFile(primaryStage);

        this.barChartRacer.dynamicBarChart(pane, info); // dynamicBarChart
        //this.barChartRacer.staticBarChart(pane,info); // staticBarChart
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest((e) -> System.exit(0));


        primaryStage.show();

    }

    /**
     * @param stage the stage to show the file
     * @return return a List<String> of the chosen file
     */
    public List<String> chooseTheFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose the file");
        List<String> file = new ArrayList<>();

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Texts File", "*.txt"));

        File initialDirectory = new File(".");

        chooser.setInitialDirectory(initialDirectory);

        File db = chooser.showOpenDialog(stage);

        try {
            file = Files.readAllLines(Paths.get(db.getAbsolutePath()));

            // transform in a list

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "It's not possible open the file");
            alert.show();
            e.printStackTrace();
        }
        return file;
    }
}
