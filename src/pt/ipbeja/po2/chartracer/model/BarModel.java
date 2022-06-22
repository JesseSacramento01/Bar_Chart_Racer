package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 11/06/2022
 */
public class BarModel {

    public static int counter = 0;
    public static String yearToPrint = "2018";

    public List<String> allYearsFound = new ArrayList<>();

    public List<Color> getColors(){
        List<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        return colors;
    }


    /*****/
    public int generateRandomNumber(int limit){
        return (int) (Math.random() * limit);
    }

    public int getNumberOfYears(List<String> file) {
        for (int i = 5; i < file.size(); i++) {
            if (!file.get(i).equals("") && !file.get(i).matches("[+-]?\\d*(\\.\\d+)?")) {
                if (!allYearsFound.contains(file.get(i).split(",")[0])) {
                    allYearsFound.add(file.get(i).split(",")[0]);
                }
            }
        }
        //System.out.println(Arrays.toString(allYearsFound.toArray()));
        return allYearsFound.size();
    }


}
