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
    private  int year;
    private  String cityName;
    private  String countryName;
    private  int qty;
    private  String continentName;


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

        return  o.getQty() - qty;
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
     *
     * @param citiesList the list of objects of the type City
     * @return return the list of city in a format of a String
     */
    public static List<String> citiesListToString(List<City> citiesList)  {
        List<String> cities = new ArrayList<>();


        // transform the list of cities in a list of Strings
        for (City city: citiesList){
            String singleLineCity = city.year+","+city.cityName+","+city.countryName+","+city.qty+","
                    +city.continentName;
            cities.add(singleLineCity);
        }
        return cities;
    }

    /**
     *
     * @param cities list of cities to be sorted and written
     * @param linesQty quantity of lines to be written
     * @param chosenYear the year chosen by the user
     * @param numberOfCities number of cities in a specific set
     * @throws IOException Exception to the file that will be written
     */
    public static void writeCityFile(List<City> cities, int linesQty,int chosenYear,List<Integer> numberOfCities) throws IOException {
        // referenceYear
        int referenceYear = cities.get(0).getYear();

        //number of cities on the set
        int numOfCities = numberOfCities.get(chosenYear - referenceYear);

        // number of the set
        int numberOfSet = chosenYear - referenceYear;

        // get the Index of the first line
        int indexOfFirstLine = getTheIndexOfFirstLine(cities,referenceYear).get(numberOfSet);

        // sort the list
        // sort only the sublist from the first line to the last line of the set
        sortCities(cities.subList(indexOfFirstLine, (indexOfFirstLine + numOfCities)));

        // list of the cities
        List<String> citiesToString = citiesListToString(cities);


        // stream to filter the information
        Stream<String> filterStream = citiesToString.stream().filter(s -> s.startsWith("" + chosenYear))
                .limit(linesQty);

        // list of the cities filtered
        List<String> citiesToWrite = filterStream.toList();


        FileWriter fileWriter = new FileWriter("WrittenCities.txt");

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
     *
     * @param line the lines read
     * @return return a list with the number of the cities in a specific set
     */
    public static List<Integer> getNumberOfCities(List<String> line){
        int count;
        List<Integer> numberOfCities = new ArrayList<>();

        for (count = 0; count < line.size() - 1; count++){
            if (line.get(count).isEmpty()){
                numberOfCities.add(Integer.parseInt(line.get(count+1))); // the next line has the number of cities --> count + 1
            }
        }
      return numberOfCities;
    }

    public int getYear() {
        return year;
    }

    /**
     *
     * @param cities the list of objects of the type City
     * @param referenceYear the first year of the first set
     * @return return the a list with the index of the first lines of a specific set
     */
    public static List<Integer> getTheIndexOfFirstLine(List<City> cities, int referenceYear){
        // list of the index of the first line in a specific set
        List<Integer> indexOfTheFirstLines = new ArrayList<>();

        int numberOfSets = 0;
        indexOfTheFirstLines.add(numberOfSets); // put the first index of the set of the first year

        for (City city : cities){
            numberOfSets += 1;
            if (city.getYear() != referenceYear){
                referenceYear++; // increase the year to continue to count others sets
                indexOfTheFirstLines.add(numberOfSets - 1); // numberOfSets - 1 because the count start at index 0
            }
        }
        return indexOfTheFirstLines;
    }

    // Main
    public static void main(String[] args)  {
        try {
            List<String> file = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                    "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));




            int chosenYear = 2018;
            List<Integer> numberOfCities = getNumberOfCities(file);

            List<City> cities = citiesList(file);

             writeCityFile(cities,12,chosenYear,numberOfCities);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





