package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jessé Sacramento
 * @number 21938
 * @version 17/05/2022
 */
class CityTest {
    /**
     * test to the first scenario, verify if the file was read correctly
     * @throws IOException for the file that will be read
     */
    @Test
    void test1() throws IOException {
        String path = "C:\\Users\\JesseSacramento" +
                      "\\IdeaProjects\\21938_JesséSacramento_TP_PO2_2021-2022" +
                       "\\src\\Cities";

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

        // Verify if the file was read correctly
        assertEquals(expected, linesRead);

        // Verify if the last line was correctly read
        assertEquals(expected.get(11), linesRead.get(11));

    }

    @Test
    void test2() throws IOException {
        //String path ="C:\\Users\\JesseSacramento\\IdeaProjects\\Bar Chart Racer\\src\\Cities";
        //City city = new City();


        //List<City> cities = city.citiesList(path);
        //city.setCities(cities);

        //assertEquals(130, cities.get(0).getQty());

        //List<City> cities = City.citiesList(lines);
        //List<String> citiesFile = city.readFile(path); // lista das cidades lidas
    }

}