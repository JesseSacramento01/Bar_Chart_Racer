package pt.ipbeja.po2.chartracer.model;

import java.io.File;
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
    private int year;
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
    public City(int year, String cityName, String countryName, int qty, String continentName) {
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
        return qty - o.getQty();
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
        Stream<String> linesFileFilter = linesStream.filter(s -> s.startsWith("15")
                || s.startsWith("16") || s.startsWith("17") || s.startsWith("18") ||
                s.startsWith("19") || s.startsWith("20"));

        // transform the stream in a list
        List<String> citiesLines = linesFileFilter.toList();


        for (String s : citiesLines) {

            //split a line of the file through the comma
            String[] info = s.split(",");

            // The object containing all the constructor attributes that will be the cities information is created
            City city = new City(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), info[4]);

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
    public static void setCities(List<String> cities) {
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

    // Main
    public static void main(String[] args) {
        try {
           List<String> file = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                   "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));

            System.out.println(citiesListToString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return cities the list of the cities in a String format
     * @throws IOException
     */
    public static List<String> citiesListToString() throws IOException {
        List<String> cities = new ArrayList<>();
        List<String> file = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));

        List<City> citiesList = citiesList(file);

        // transform the list of cities in a list of Strings
        for (City city: citiesList){
            String singleLineCity = city.year+","+city.cityName+","+city.countryName+","+city.qty+","
                    +city.continentName;
            cities.add(singleLineCity);
        }
        return cities;
    }
}





