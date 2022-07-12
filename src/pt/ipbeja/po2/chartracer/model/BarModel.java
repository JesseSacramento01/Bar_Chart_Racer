package pt.ipbeja.po2.chartracer.model;


import pt.ipbeja.po2.chartracer.gui.BarChartRacer;
import pt.ipbeja.po2.chartracer.model.View.View;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @version 21/06/2022
 * @number 21938 & 20347
 */
public class BarModel {
    private View view;

    public int getNumberOfSets(List<String> file) {
        List<City> cities = citiesList(file);
        int count = 1; // because the index 0 indicate the first set
        String value = findAllFirstData(cities).get(0); // first value

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

    /**
     *
     * @param view set the View
     */
    public void setView(View view) {
        this.view = view;
    }


    public void statisticData() {
        String path = "..\\21938_JesséSacramento_20347_LuizFehlbergTP_PO2_2021-2022\\files\\BaseData.txt";
        List<String> statisticData = null;
        try {
            statisticData = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view.setStatisticData(statisticData);
    }


    public void writeStatisticData() {
        try {
            FileWriter fileWriter = new FileWriter("StatisticData.txt");

            for (String s : BarChartRacer.getNewFile()) {
                fileWriter.write(s + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param lines is a list of the file read
     * @return cityList List of the objects from the class city
     */
    public List<City> citiesList(List<String> lines) {
        //List that will store the cities to be sorted
        List<City> cityList = new ArrayList<>();


        Stream<String> linesStream = lines.stream();

        // filter in order to get just the information we need about the cities
        Stream<String> linesFileFilter = linesStream.filter(s -> s.contains(","));

        // transform the stream in a list
        List<String> citiesLines = linesFileFilter.toList();

        for (String s : citiesLines) {

            //split a line of the file through the comma
            String[] info = s.split(",");


            // The object containing all the constructor attributes that will be the cities information is created
            City city = new City(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);

            // will be inserted in a list to be sort
            cityList.add(city);
        }
        return cityList;
    }


    /**
     * Sort a list of objects from the class city
     *
     * @param cities is a List of the lines Read int the file of the cities
     */
    public void sortCities(List<City> cities) {
        Collections.sort(cities); // Sort a list of objects from the class city
    }


    /**
     * @param citiesList the list of objects of the type City
     * @return return the list of city in a format of a String
     */
    public List<String> citiesListToString(List<City> citiesList) {
        List<String> cities = new ArrayList<>();


        // transform the list of cities in a list of Strings
        for (City city : citiesList) {
            String singleLineCity = city.getYear() + "," + city.getCityName() +
                    "," + city.getCountryName() + "," +
                    city.getQty() + "," + city.getContinentName();
            cities.add(singleLineCity);
        }
        return cities;
    }

    /**
     * @param cities     list of City objects
     * @param chosenYear the year chosen by the user
     * @return return the list of a specific set
     */
    public List<City> getSpecificSet(List<City> cities, String chosenYear) {
        List<City> specificList = new ArrayList<>();
        for (City city : cities) {
            if (city.getYear().equals(chosenYear)) {
                specificList.add(city);
            }
        }
        return specificList;
    }

    /**
     * @param cities   list of cities to be sorted and written
     * @param linesQty quantity of lines to be written
     * @throws IOException Exception to the file that will be written
     */
    public void writeCityFile(List<City> cities, int linesQty) throws IOException {
        // list of the cities
        List<String> citiesToString = citiesListToString(cities);

        // stream to filter the information
        Stream<String> filterStream = citiesToString.stream().limit(linesQty);

        // list of the cities filtered
        List<String> citiesToWrite = filterStream.toList();


        FileWriter fileWriter = new FileWriter("..\\21938_JesséSacramento_20347_LuizFehlbergTP_PO2_2021-2022\\files\\WrittenCities.txt");


        for (String list : citiesToWrite) {
            fileWriter.write(list + "\n");
        }
        fileWriter.close();
    }

    /**
     * @param line the lines read
     * @return return a list with the number of the cities in a specific set
     */
    public Map<String, Integer> getNumberOfCities(List<String> line) {
        Map<String, Integer> numberOfCity = new HashMap<>();

        for (int count = 0; count < line.size() - 1; count++) {
            if (line.get(count).isEmpty()) {
                // the next line has the number of cities --> count + 1

                // count + 2 because two lines next we have the first line of the information from the cities
                // in a specific set
                int index = line.get(count + 2).indexOf(","); // index of the first comma
                String key = line.get(count + 2).substring(0, index);
                numberOfCity.put(key, Integer.parseInt(line.get(count + 1)));

            }
        }
        return numberOfCity;
    }

    /**
     * @param cities list of objects from class City
     * @return return the max value in the file
     */
    public double getMaxValue(List<City> cities) {
        long max = Integer.MIN_VALUE;
        for (City city : cities) {
            if (city.getQty() > max) {
                max = city.getQty();
            }
        }
        return max;
    }

    /**
     * @param cities list of objects from class City
     * @return return the min value in the file
     */
    public double getMinValue(List<City> cities) {
        double min = Integer.MAX_VALUE;
        for (City city : cities) {
            if (city.getQty() < min) {
                min = city.getQty();
            }
        }
        return min;
    }


    /**
     * @param cities a list of objects from class City
     * @return return a list of Strings containing the first information of each line
     */
    public List<String> findAllFirstData(List<City> cities) {
        Optional<City> cityStream = cities.stream().findFirst();
        String firstLineInformation = cityStream.stream().toList().get(0).getYear();
        List<String> files = new ArrayList<>();

        for (City city : cities) {
            if (!(city.getYear().equals(firstLineInformation))) {
                files.add(firstLineInformation);
                firstLineInformation = city.getYear();
            }
        }
        files.add(firstLineInformation);
        return files; // return all the first information of all lines
    }

    /**
     *
     * @param cities a list of objects from calss City
     * @return return the number of columns in each set
     */
    public int getColumns(List<City> cities) {
        int count = 0;

        List<String> c = citiesListToString(cities);

        for (int i = 0; i < cities.size(); i++) {
           count =  c.get(i).split(",").length;
        }
        return count;
    }

    /**
     *
     * @param cities return a list of objects from class City
     * @param barChartRacer the instance of class BarchartRacer
     * @return return the average of lines in each set
     */
    public int averageOfLines(List<City> cities, BarChartRacer barChartRacer) {
        int count = 0;
        List<String> firstData = findAllFirstData(cities);
        int sets = getNumberOfSets(barChartRacer.getFile());

        for (int i = 0; i < sets; i++) {
            List<City> c = getSpecificSet(cities, firstData.get(i));
            count += c.size();
        }

        return count / sets;
    }

    public void callSkins() {
        this.view.generateSkins();
    }


}

