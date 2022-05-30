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
 * @version 17/05/2022
 */
class CityTest {
    private int first;
    private int last;
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
        assertEquals(expected, linesRead.subList(5,17));

        // Verify if the last line was correctly read
        assertEquals(lastLine, linesRead.get(7268));

    }

    @Test
    void test2() throws IOException {
        String path ="C:\\Users\\JesseSacramento\\IdeaProjects\\" +
                "21938_JesséSacramento_TP_PO2_2021-2022\\files\\Cities.txt";

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

        List<String> firstYearCities = City.citiesListToString();

        first = 0; // index to search in a sublist
        last = 12; // index to search in a sublist

        // shuffle the List to sort next
        Collections.shuffle(firstYearCities.subList(first, last));

        // show if the results are different for real
        assertNotEquals(expected, firstYearCities.subList(first,last));


        // sort of the list
        City.setCities(firstYearCities);

        // check if the results are now equals
        assertEquals(expected, firstYearCities.subList(first,last));

    }

}