package pt.ipbeja.po2.chartracer.gui;/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @number 21938 & 20347
 * @version 21/06/2022/
 */

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
import java.util.Timer;
import java.util.TimerTask;

public class Start extends Application {

    BarChartRacer barChartRacer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        double WIDTH = 900;
        double HEIGHT = WIDTH / 1.5;
        pane.setPrefSize(WIDTH, HEIGHT);

        Scene scene = new Scene(pane, Color.AZURE);
        primaryStage.setScene(scene);



        /**/
        List<String> info = this.chooseTheFile(primaryStage);
        this.barChartRacer = new BarChartRacer(pane, info);
//        this.barChartRacer.barRectangle();
        this.barChartRacer.reqE4();

        primaryStage.show();

    }

    /**
     *
     * @param stage the stage to show the file
     * @return return a List<String> of the chosen file
     */
    private List<String> chooseTheFile(Stage stage) {
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
