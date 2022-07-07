package pt.ipbeja.po2.chartracer.model;


import pt.ipbeja.po2.chartracer.gui.ColorType;

import java.util.*;


/**
 * @author Jessé Sacramento & Luiz Carlos Morais
 * @version 21/05/2022
 * @number 21938 & 20347
 */

public class City implements Comparable<City>, ColorType {
    private static final int DEGREE = 255;
    private String year;
    private String cityName;
    private String countryName;
    private int qty;
    private String continentName;
    private List<Integer> numbers;


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
     * @param cities the list of cities
     */
    public City(List<City> cities) {
        int number;
        this.numbers = new ArrayList<>();
        for (City city : cities) {
            number = city.cityName.hashCode() % DEGREE;
            if (number < 0) { // transform the negative number in positive
                number = number * -1;
            }
            numbers.add(number);
        }
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
     * Method who returns the populations quantity
     *
     * @return qty the quantity of population
     */
    public int getQty() {
        return qty;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return qty == city.qty && year.equals(city.year) && cityName.equals(city.cityName) && countryName.equals(city.countryName) && Objects.equals(continentName, city.continentName);
    }


    // getters

    /**
     * @return the Year
     */
    public String getYear() {
        return year;
    }


    /**
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }


    /**
     * @return return the hashcode number
     */
    @Override
    public int hashCode() {
        return Objects.hash(cityName);
    }

    /**
     * @return numbers the hashcode to use in colors
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @return continentName
     */
    public String getContinentName() {
        return continentName;
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
     * @param cities a list of objects from class City
     */
    public void continentHashcode(List<City> cities) {
        int number;
        this.numbers.clear();
        for (City city : cities) {
            number = city.getContinentName().hashCode() % DEGREE;
            if (number < 0) { // transform the negative number in positive
                number = number * -1;
            }
            numbers.add(number);
        }
    }

    /**
     * @param cities a list of objects from class City
     */
    public void yearHashcode(List<City> cities) {
        int number;
        this.numbers.clear();
        for (City city : cities) {
            number = city.getContinentName().hashCode() % DEGREE;
            if (number < 0) { // transform the negative number in positive
                number = number * -1;
            }
            numbers.add(number);
        }
    }


    @Override
    public void colorsByContinent(List<City> cities) {
        int number;
        this.numbers.clear();
        for (City city : cities) {
            number = city.getContinentName().hashCode() % DEGREE;
            if (number < 0) { // transform the negative number in positive
                number = number * -1;
            }
            numbers.add(number);
        }
    }

    @Override
    public void colorsByYear(List<City> cities) {
        int number;
        this.numbers.clear();
        for (City city : cities) {
            number = city.getContinentName().hashCode() % DEGREE;
            if (number < 0) { // transform the negative number in positive
                number = number * -1;
            }
            numbers.add(number);
        }
    }

}





