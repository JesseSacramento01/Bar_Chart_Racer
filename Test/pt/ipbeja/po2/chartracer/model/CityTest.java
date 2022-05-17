package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jessé Sacramento
 * @version 17/05/2022
 */
class CityTest {
    @Test
    void test1() throws IOException {
        String path = "C:\\Users\\JesseSacramento\\IdeaProjects\\Bar Chart Racer\\src\\Cities";
        City city = new City();

        List<String> citiesFile = city.readFile(path); // lista das cidades lidas

        // Expected
        List<String> expected = Files.readAllLines(Paths.get(path)); // o que se espera do arquivo lido

        // verificar se o ficheiro foi lido correctamente
        assertEquals(expected, citiesFile);

    }

    @Test
    void test2() throws IOException {
        String path ="C:\\Users\\JesseSacramento\\IdeaProjects\\Bar Chart Racer\\src\\Cities";
        City city = new City();


        List<City> cities = city.citiesList(path);
        city.setCities(cities);

        assertEquals(130, cities.get(0).qty);
    }

}