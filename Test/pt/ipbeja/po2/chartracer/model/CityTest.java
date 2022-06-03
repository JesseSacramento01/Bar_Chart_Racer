package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


        String path = "C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt";

        // the lines were read and passed to a list of Strings
        List<String> linesRead = Files.readAllLines(Paths.get(path));


        List<City> cities = City.citiesList(linesRead);

        List<String> cityListToString = City.citiesListToString(cities);


        // index of the set of cities
        int firstYear = cities.get(0).getYear();

        // year chosen by the user
        int chosenYear = 1500;

        // index to get the quantity of the cities
        int qtyCitiesIndex = chosenYear - firstYear;

        // get one line before the number that say how many cities are in a set
        int indexOfTheEmptyLine = City.getNumberOfCities(linesRead).get(qtyCitiesIndex);

        // transform the String in a number to use as the number of the cities
        int numberOfCities = Integer.parseInt(linesRead.get(indexOfTheEmptyLine+1));





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

        // last line of the file cities.txt
        String lastLine = "2018,Tokyo,Japan,38194,East Asia";

        // Verify if the file was read correctly
        assertEquals(expected, cityListToString.subList(0,numberOfCities));

        int lastIndex = linesRead.size() - (linesRead.size() - cityListToString.size());
        System.out.println(lastIndex);

        // Verify if the last line was correctly read
        assertEquals(lastLine, cityListToString.get(lastIndex-1));

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




        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento" +
                "\\IdeaProjects\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));


        // A list of City objects
        List<City> cities = City.citiesList(lines);

        // index of the set of cities
        int firstYear = cities.get(0).getYear();

        // year chosen by the user
        int chosenYear = 1500;

        // index to get the quantity of the cities
        int qtyCitiesIndex = chosenYear - firstYear;

        // get one line before the number that say how many cities are in a set
        int indexOfTheEmptyLine = City.getNumberOfCities(lines).get(qtyCitiesIndex);

        // transform the String in a number to use as the number of the cities
        int numberOfCities = Integer.parseInt(lines.get(indexOfTheEmptyLine+1));


        List<String> firstYearCities = City.citiesListToString(cities.subList(0,numberOfCities));
        System.out.println("before:" + firstYearCities);

        // shuffle the List to sort next
        Collections.shuffle(firstYearCities.subList(0, numberOfCities));

        System.out.println("after:" + firstYearCities);

        //show if the results are different for real
        assertNotEquals(expected, firstYearCities.subList(0, numberOfCities));


        // sort of the list
        City.sortCities(cities.subList(0,numberOfCities));


        firstYearCities = City.citiesListToString(cities.subList(0,numberOfCities));

        System.out.println("After sort:" + firstYearCities);

        // check if the results are now equals
        assertEquals(expected, firstYearCities.subList(0, numberOfCities));



        // tests for the last set

        chosenYear = 2018;

        // index to get the quantity of the cities
        qtyCitiesIndex = chosenYear - firstYear;

        // get one line before the number that say how many cities are in a set
        indexOfTheEmptyLine = City.getNumberOfCities(lines).get(qtyCitiesIndex);

        // transform the String in a number to use as the number of the cities
        numberOfCities = Integer.parseInt(lines.get(indexOfTheEmptyLine+1));


        // index of the first line of the set of cities
        int indexOfTheLastSet = cities.size() - numberOfCities;


        // shuffle the List to sort next
        Collections.shuffle(cities.subList(indexOfTheLastSet,cities.size()));

        List<String> lastYearCities = City.citiesListToString(cities.subList(indexOfTheLastSet,cities.size()));

        //show if the results are different for real
        assertNotEquals(expectedLastSet, lastYearCities.subList(0, numberOfCities));


        // sort of the list
        City.sortCities(cities.subList(indexOfTheLastSet,cities.size()));

        // transform the list of cities in a list of Strings
        lastYearCities = City.citiesListToString(cities.subList(indexOfTheLastSet,cities.size()));


        // check if the results are now equals
        assertEquals(expectedLastSet, lastYearCities.subList(0, numberOfCities));

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

        List<String> fileRead = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                                                "\\21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt"));


        // list of cities
        List<City> cities = City.citiesList(fileRead);

        // get the number of the city in a specific set
        List<Integer> numberOfCities = City.getNumberOfCities(fileRead);



        int chosenYear = 1500;
        int linesQty = 5;

        // test the method that write the file
        City.writeCityFile(cities,linesQty,chosenYear,numberOfCities);

        List<String> readFileWritten = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                                               "\\21938_JesséSacramento_TP_PO2_2021-2022\\WrittenCities.txt"));

        // check if the file was written correctly
        // test for the first set
        assertEquals(expectedCitiesFirstSet, readFileWritten);


        // test for the second set
        chosenYear = 2018;

        // test the method that write the file
        City.writeCityFile(cities,linesQty,chosenYear,numberOfCities);

        readFileWritten = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects" +
                "\\21938_JesséSacramento_TP_PO2_2021-2022\\WrittenCities.txt"));

        // Check if the file was written correctly
        assertEquals(expectedCitiesLastSet, readFileWritten);




    }


}