package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.paint.Color;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;

import java.util.List;
import java.util.Map;

/**
 * @author Jessé Sacramento
 * @version 07/07/2022
 */
public class Colors {
    BarModel barModel;
    BarChartRacer barChartRacer;
    List<Integer> numbers;
    List<City> cities;
    City city;
    private static final int RGB_COLOR = 255;

    Color color;

    public Colors(BarModel barModel, BarChartRacer barChartRacer,City city) {
        this.barModel = barModel;
        this.barChartRacer = barChartRacer;
        cities = barModel.citiesList(barChartRacer.getFile());
        this.city = city;
    }


    public Color colorType(int j) {
        Map<String, Integer> cityNumber = barModel.getNumberOfCities(barChartRacer.getFile());
        String key = barModel.findAllFirstData(cities).get(j);
        int number = cityNumber.get(key);

        return Color.rgb(((city.getNumbers().get(j) * number) % RGB_COLOR)
                , (city.getNumbers().get(j) * number) % RGB_COLOR, city.getNumbers().get(j));
    }



    public Color getColor() {
        return color;
    }

}
