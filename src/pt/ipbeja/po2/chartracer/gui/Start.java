package pt.ipbeja.po2.chartracer.gui;/**
 * @author Jessé Sacramento
 * @version 15/06/2022
 */

import javafx.application.Application;
import javafx.application.Platform;
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


        List<String> info = chooseTheFile(primaryStage);

        this.barChartRacer = new BarChartRacer(pane, info);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest((e) -> {
            System.exit(0);
        });

        primaryStage.show();
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
