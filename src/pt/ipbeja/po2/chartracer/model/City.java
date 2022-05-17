package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Jessé Sacramento
 * @version 11/05/2022
 */

public class City implements Comparable<City>{
        int id;
        String city;
        String country;
        int qty;
        String continent;



        public City(int id, String city, String country, int qty, String continent){
            this.id = id;
            this.city = city;
            this.country = country;
            this.qty = qty;
            this.continent = continent;
        }

        public City(){

        }


        @Override
        public int compareTo(City o) {
            return  qty - o.getQty();
        }

        public List<City> citiesList(String path) throws IOException {
            List<City> cityList = new ArrayList<>(); // Lista que gaurdará todas as cidades para serem ordenadas
            List<String> citiesFile = readFile(path); // arquivo lido das cidades

            for (int i = 0; i< citiesFile.size(); i++) {
                // separa a linha das informações a cada vírgula
                // Transforma em um array de Strings
                String[] info = citiesFile.get(i).split(",");

                // cria-se o objecto que contém todos os atributos do contrutor que serão as informações das cidades
                City city = new City(Integer.parseInt(info[0]),info[1],info[2],Integer.parseInt(info[3]),info[4]);
                // Coloca-se na lista das cidades para ordenar
                cityList.add(city);
            }
            return cityList; // retorna a lista
        }


        public void setCities(List<City> cities){
            Collections.sort(cities);
        }

        public List<String> readFile(String path) throws IOException {
            // Vai ler os arquivos no dado documento e vai guardar na variável citiesaFile
            return Files.readAllLines(Paths.get(path));
        }


    // Getters
    public int getQty() {
        return qty;
    }


    // Main
    public static void main(String[] args) throws IOException {
//            String path ="C:\\Users\\JesseSacramento\\IdeaProjects\\Bar Chart Racer\\src\\Cities";
//            List<City> cities = citiesList(path);
//
//            setCities(cities);
//        for (City city : cities) {
//            System.out.println(city.getQty());
//        }

        }

    @Override
    public String toString() {
        return  id+","+city+","+country+","+qty+","+continent;

    }
}





