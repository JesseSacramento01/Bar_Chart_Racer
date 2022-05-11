package OrderTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 11/05/2022
 */

public class Order implements Comparable<Order>{
        int id;
        String city;
        String country;
        int qty;
        String continent;



        public Order(int id, String city, String country, int qty, String continent){
            this.id = id;
            this.city = city;
            this.country = country;
            this.qty = qty;
            this.continent = continent;
        }


        public static void main(String[] args) throws IOException {
            List<Order> orders = setOrders(); // Lista das cidades a ordenar

            Collections.sort(orders); // usamos o metodo sort que vem de Collections, para ordenar

            FileWriter fileWriter = new FileWriter("OrderCities");
            fileWriter.write(orders.get(3).city+" , "+orders.get(3).qty);
            fileWriter.close();


            for (Order order: orders){ // para percorrer sobre cada Item da lista
                System.out.println(order);
            }

        }


        @Override
        public int compareTo(Order o) {
            return  qty - o.getQty();
        }

        public static List<Order> setOrders() throws IOException {
            List<Order> orderList = new ArrayList<>(); // Lista que gaurdará todas as cidades para serem ordenadas

            // Vai ler os arquivos no dado documento e vai guardar na variável citiesaFile
            List<String> citiesFile = Files.readAllLines(Paths.get("C:\\Users\\JesseSacramento\\IdeaProjects\\Bar Chart Racer\\src\\Cities"));

            for (int i = 0; i< citiesFile.size() -1; i++) {
                // separa a linha das informações a cada vírgula
                // Transforma em um array de Strings
                String[] info = citiesFile.get(i).split(",");

                // criamos o objecto que contém todos os atributos do contrutor que serão as informações das cidades
                Order order = new Order(Integer.parseInt(info[0]),info[1],info[2],Integer.parseInt(info[3]),info[4]);
                // Coloca-se na lista das cidades para ordenar
                orderList.add(order);
            }
            return orderList; // retorna a lista
        }


        public int getQty() {
            return qty;
        }


        /*@Override
        public String toString() { // para as informações serem apresentadas em forma de String.
            return "Order{" +
                    "id=" + id +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", qty=" + qty +
                    ", continent='" + continent + '\'' +
                    '}';
        }*/
    }





