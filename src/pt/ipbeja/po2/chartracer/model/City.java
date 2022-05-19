package pt.ipbeja.po2.chartracer.model;

import java.util.*;

/**
 * @author Jessé Sacramento
 * @version 11/05/2022
 */

public class City implements Comparable<City> {
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
        List<City> cityList = new ArrayList<>();
        //List that will store the cities to be sorted

        for (String s : lines) {
            //split a line of the file through the comma
            // Transform in an array of Strings
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
    public void setCities(List<City> cities) {
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
    }
}





