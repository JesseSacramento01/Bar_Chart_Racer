package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jessé Sacramento
 * @number 21938
 * @version 01/06/2022
 */
class CityTest {
    /**
     * test to the first scenario, verify if the file was read correctly
     * @throws IOException for the file that will be read
     */
    @Test
    void test1() throws IOException {


        String path = "..\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt";

        // Expected List of cities
        List<String> expected = Arrays.asList(
                "1500,Beijing,China,672,East Asia",
                "1500,Cairo,Egypt,400,Middle East",
                "1500,Cuttack,India,140,South Asia",
                "1500,Fez,Morocco,130,Middle East",
                "1500,Gauda,India,200,South Asia",
                "1500,Guangzhou,China,150,East Asia",
                "1500,Hangzhou,China,250,East Asia",
                "1500,Istanbul,Turkey,200,Europe",
                "1500,Nanjing,China,147,East Asia",
                "1500,Paris,France,185,Europe",
                "1500,Tabriz,Iran,250,Middle East",
                "1500,Vijayanagar,India,500,South Asia");




        // the lines were read and passed to a list of Strings
        List<String> linesRead = Files.readAllLines(Paths.get(path));




        // list of objects from the class City
        List<City> cities = City.citiesList(linesRead);

        cities = City.getSpecificSet(cities, "1500");

        // transform the list of city on a list of Strings
        List<String> cityListToString = City.citiesListToString(cities);

        // verify if the file was correctly read
        assertEquals(expected, cityListToString);

        // last line of the file cities.txt
        String lastLine = "2018,Tokyo,Japan,38194,East Asia";

        // list of objects city
        cities = City.citiesList(linesRead);

        // get the set of the 2018 year
        cities = City.getSpecificSet(cities, "2018");

        // transform the list of City in a list of String
        cityListToString = City.citiesListToString(cities);

        int indexOfTheLastLine = cityListToString.lastIndexOf("2018,Tokyo,Japan,38194,East Asia");

        // Verify if the last line was correctly read
        assertEquals(lastLine, cityListToString.get(indexOfTheLastLine));

    }

    @Test
    void test2() throws IOException {

        // Expected List of cities
        List<String> expected = Arrays.asList(
                "1500,Beijing,China,672,East Asia",
                "1500,Vijayanagar,India,500,South Asia",
                "1500,Cairo,Egypt,400,Middle East",
                "1500,Hangzhou,China,250,East Asia",
                "1500,Tabriz,Iran,250,Middle East",
                "1500,Gauda,India,200,South Asia",
                "1500,Istanbul,Turkey,200,Europe",
                "1500,Paris,France,185,Europe",
                "1500,Guangzhou,China,150,East Asia",
                "1500,Nanjing,China,147,East Asia",
                "1500,Cuttack,India,140,South Asia",
                "1500,Fez,Morocco,130,Middle East");

        // Expected List of cities of the last set
        List<String> expectedLastSet = Arrays.asList(
                "2018,Tokyo,Japan,38194,East Asia",
                "2018,Delhi,India,27890,South Asia",
                "2018,Shanghai,China,25779,East Asia",
                "2018,Beijing,China,22674,East Asia",
                "2018,Mumbai,India,22120,South Asia",
                "2018,São Paulo,Brazil,21698,Latin America",
                "2018,Mexico City,Mexico,21520,Latin America",
                "2018,Osaka,Japan,20409,East Asia",
                "2018,Cairo,Egypt,19850,Middle East",
                "2018,Dhaka,Bangladesh,19633,South Asia",
                "2018,New York,United States,18713,North America",
                "2018,Karachi,Pakistan,18185,South Asia");




        List<String> lines = Files.readAllLines(Paths.get("..\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));


        // A list of City objects
        List<City> cities = City.citiesList(lines);

        // get the specific set of 1500 year
        cities = City.getSpecificSet(cities, "1500");

        // transform in a list of type String
        List<String> citiesListToString = City.citiesListToString(cities);

        // check if are really different
        assertNotEquals(expected, citiesListToString);

        // sort the list
        City.sortCities(cities);

        System.out.println("After: " + cities);

        // transform in a list of type String
        citiesListToString = City.citiesListToString(cities);

        // check if now are equals
        assertEquals(expected, citiesListToString);



        // test for the last set


        // A list of City objects
        cities = City.citiesList(lines);

        cities = City.getSpecificSet(cities,"2018");

        // transform in a list of type String
        citiesListToString = City.citiesListToString(cities);

        // check if are really different
        assertNotEquals(expectedLastSet, citiesListToString);

        // sort the list
        City.sortCities(cities);

        citiesListToString = City.citiesListToString(cities);

        assertEquals(expectedLastSet, citiesListToString);


    }


    @Test
    void test3() throws IOException {

        List<String> expectedCitiesFirstSet = Arrays.asList(
                "1500,Beijing,China,672,East Asia",
                "1500,Vijayanagar,India,500,South Asia",
                "1500,Cairo,Egypt,400,Middle East",
                "1500,Hangzhou,China,250,East Asia",
                "1500,Tabriz,Iran,250,Middle East");

        List<String> expectedCitiesLastSet = Arrays.asList(
                "2018,Tokyo,Japan,38194,East Asia",
                "2018,Delhi,India,27890,South Asia",
                "2018,Shanghai,China,25779,East Asia",
                "2018,Beijing,China,22674,East Asia",
                "2018,Mumbai,India,22120,South Asia");

        List<String> fileRead = Files.readAllLines(Paths.get("..\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));


        // list of cities
        List<City> cities = City.citiesList(fileRead);

        //sort the list
        City.sortCities(cities);

        cities = City.getSpecificSet(cities,"1500");

        int linesQty = 5;

        // test the method that write the file
        City.writeCityFile(cities,linesQty);

        List<String> readFileWritten = Files.readAllLines(Paths.get("..\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\WrittenCities.txt"));

        // check if the file was written correctly
        // test for the first set
        assertEquals(expectedCitiesFirstSet, readFileWritten);


        // test for the second set

        cities = City.citiesList(fileRead);

        City.sortCities(cities);

        cities = City.getSpecificSet(cities,"2018");

        // test the method that write the file
        City.writeCityFile(cities,linesQty);

        readFileWritten = Files.readAllLines(Paths.get("..\\21938_JesséSacramento_TP_PO2_2021-2022\\WrittenCities.txt"));

        // Check if the file was written correctly
        assertEquals(expectedCitiesLastSet, readFileWritten);

    }


}