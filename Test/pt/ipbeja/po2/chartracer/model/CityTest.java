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

        List<City> citiesList = city.citiesList(path); // lista das cidades lidas
        List<String> readCities = new ArrayList<>();   // lista das cidades lidas em formato String

        // Expected
        List<String> citiesFile = Files.readAllLines(Paths.get(path)); // o que se espera do arquivo lido

        // iteração para adicionar o conteúdo do objecto city na lista em formato de String
        for (City city1 : citiesList) {
            readCities.add(city1.toString());

        }

        // verificar se o ficheiro foi lido correctamente
        assertEquals(citiesFile.toString(), readCities.toString());

        // Verificar se aprimeira cidade é a esperada
        assertEquals("Beijing", citiesList.get(0).city);

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