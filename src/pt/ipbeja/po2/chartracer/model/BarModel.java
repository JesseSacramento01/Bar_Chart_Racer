package pt.ipbeja.po2.chartracer.model;



import java.util.ArrayList;
import java.util.List;

/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @number 21938 & 20347
 * @version 21/06/2022
 */
public class BarModel {



    public List<String> allYearsFound = new ArrayList<>();


    public int getNumberOfYears(List<String> file) {
        for (int i = 5; i < file.size(); i++) {
            if (!file.get(i).equals("") && !file.get(i).matches("[+-]?\\d*(\\.\\d+)?")) {
                if (!allYearsFound.contains(file.get(i).split(",")[0])) {
                    allYearsFound.add(file.get(i).split(",")[0]);
                }
            }
        }
        return allYearsFound.size();
    }


    /**
     * @param maxValue the max value that is present in the file
     * @param height   the height of the pane
     * @param value    the qty that we get from the class City
     * @return return the
     */
    public double getTheSpecificSpace(double maxValue, double height, double value) {
        return (height * value) / maxValue;
    }




}
