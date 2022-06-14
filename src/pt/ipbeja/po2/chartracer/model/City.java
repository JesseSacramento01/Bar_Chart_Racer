package pt.ipbeja.po2.chartracer.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


/**
 * @author Jessé Sacramento
 * @version 11/05/2022
 */

public class City implements Comparable<City> {
    static Scanner scanner = new Scanner(System.in);
    private String year;
    private String cityName;
    private String countryName;
    private int qty;
    private String continentName;


    /**
     * @param year          the year of the city
     * @param cityName      the name of the city
     * @param countryName   the name of the country
     * @param qty           the population quantity of the city
     * @param continentName the name of the continent
     */
    public City(String year, String cityName, String countryName, int qty, String continentName) {
        this.year = year;
        this.cityName = cityName;
        this.countryName = countryName;
        this.qty = qty;
        this.continentName = continentName;
    }


    /**
     * @param o object of th class City
     * @return return qty - o.getQty()
     */
    @Override
    public int compareTo(City o) {
        return o.getQty() - qty;
    }


    /**
     * @param lines is a list of the file read
     * @return cityList List of the objects from the class city
     */
    public static List<City> citiesList(List<String> lines) {
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
    public static void sortCities(List<City> cities) {
        Collections.sort(cities); // Sort a list of objects from the class city
    }


    /**
     * Method who returns the populations quantity
     *
     * @return qty the quantity of population
     */
    public int getQty() {
        return qty;
    }


    /**
     * @param citiesList the list of objects of the type City
     * @return return the list of city in a format of a String
     */
    public static List<String> citiesListToString(List<City> citiesList) {
        List<String> cities = new ArrayList<>();


        // transform the list of cities in a list of Strings
        for (City city : citiesList) {
            String singleLineCity = city.year + "," + city.cityName + "," + city.countryName + "," +
                    city.qty + "," + city.continentName;
            cities.add(singleLineCity);
        }
        return cities;
    }

    /**
     * @param cities     list of City objects
     * @param chosenYear the year chosen by the user
     * @return return the list of a specific set
     */
    public static List<City> getSpecificSet(List<City> cities, String chosenYear) {
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
    public static void writeCityFile(List<City> cities, int linesQty) throws IOException {
        // list of the cities
        List<String> citiesToString = citiesListToString(cities);

        // stream to filter the information
        Stream<String> filterStream = citiesToString.stream().limit(linesQty);

        // list of the cities filtered
        List<String> citiesToWrite = filterStream.toList();


        FileWriter fileWriter = new FileWriter("..\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\WrittenCities.txt");


        for (String list : citiesToWrite) {
            fileWriter.write(list + "\n");
        }
        fileWriter.close();
    }


    @Override
    public String toString() {
        return "City{" +
                "year=" + year +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", qty=" + qty +
                ", continentName='" + continentName + '\'' +
                '}';
    }

    /**
     * @param line the lines read
     * @return return a list with the number of the cities in a specific set
     */
    public static Map<String, Integer> getNumberOfCities(List<String> line) {
        int count;
        String key;

        Map<String, Integer> numberOfCity = new HashMap<>();

        for (count = 0; count < line.size() - 1; count++) {
            if (line.get(count).isEmpty()) {
                // the next line has the number of cities --> count + 1

                // count + 2 because two lines next we have the first line of the information from the cities
                // in a specific set
                int index = line.get(count + 2).indexOf(","); // index of the first comma
                key = line.get(count + 2).substring(0, index);
                numberOfCity.put(key, Integer.parseInt(line.get(count + 1)));

            }
        }
        return numberOfCity;
    }

    // getters
    public String getYear() {
        return year;
    }

    public String getCityName() {
        return cityName;
    }

    // Main
    public static void main(String[] args) {
        try {
            List<String> file = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                    "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));


            //int chosenYear = 2018;
            Map<String, Integer> numberOfCities = getNumberOfCities(file);

            System.out.println("1500 index: " + numberOfCities.get("1500"));

            //System.out.println(citiesList(file));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}





