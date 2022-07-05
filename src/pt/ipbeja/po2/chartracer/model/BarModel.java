package pt.ipbeja.po2.chartracer.model;


import pt.ipbeja.po2.chartracer.gui.BarChartRacer;
import pt.ipbeja.po2.chartracer.model.View.View;

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
public class BarModel {
    private View view;
    private List<String> newFile;


    public static int getNumberOfSets(List<String> file) {
        List<City> cities = City.citiesList(file);
        int count = 1; // because the index 0 indicate the first set
        String value = City.findAllFirstData(cities).get(0); // first value

        for (City city : cities) {
            if (!city.getYear().equals(value)) {
                value = city.getYear();
                count++;
            }
        }
        return count;
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


    public void setView(View view) {
        this.view = view;
    }

    public void setStatisticsData() {
        String path = "..\\21938_JesséSacramento_20347_LuizFehlbergTP_PO2_2021-2022\\files\\StatisticData.txt";
        BarChartRacer barChartRacer = new BarChartRacer(this);
        this.newFile = new ArrayList<>();

        try {
            List<String> statisticData = Files.readAllLines(Paths.get(path));
            for (String s : statisticData) {
                switch (s) {
                    case "Number of data sets in file: ??":
                        s = s.replace("??", String.valueOf(getNumberOfSets(barChartRacer.getFile())));
                        newFile.add(s);
                        break;
                    case "First date: YYYY/MM/DD":
                        s = s.replace("YYYY/MM/DD", barChartRacer.getAccessTime());
                        newFile.add(s);
                        break;
                    case "Last date: YYYY/MM/DD":
                        s = s.replace("YYYY/MM/DD", barChartRacer.getLastTimeAccess());
                        newFile.add(s);
                        break;
                    case "Average number of lines in each data set: ??":
                        s = s.replace("??", String.valueOf(12));
                        newFile.add(s);
                    case "Number of columns in each data set: ??":
                        s = s.replace("??", String.valueOf(12 * 5));
                        newFile.add(s);
                    case "Maximum value considering all data sets: ??":
                        s = s.replace("??",
                                String.valueOf(City.getMaxValue(City.citiesList(barChartRacer.getFile()))));
                        newFile.add(s);
                    case "Minimum value considering all data sets: ??":
                        s = s.replace("??", String.valueOf(187));
                        newFile.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getNewFile() {
        return newFile;
    }

    public void writeStatisticData(){

    }

}

