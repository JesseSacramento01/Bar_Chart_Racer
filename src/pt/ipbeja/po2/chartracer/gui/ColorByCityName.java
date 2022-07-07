package pt.ipbeja.po2.chartracer.gui;


import javafx.scene.paint.Color;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;

import java.util.Map;


/**
 * @author Jessé Sacramento
 * @version 07/07/2022
 */
public class ColorByCityName extends Colors{

    private static final int RGB_COLOR = 255;


    public ColorByCityName(BarModel barModel, BarChartRacer barChartRacer,City city) {
        super(barModel,barChartRacer,city);
    }


    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public Color colorType(int j) {
        Map<String, Integer> cityNumber = super.barModel.getNumberOfCities(super.barChartRacer.getFile());
        String key = super.barModel.findAllFirstData(super.cities).get(j);
        int number = cityNumber.get(key);

        return Color.rgb(((super.city.getNumbers().get(j) * number) % RGB_COLOR)
                , (super.city.getNumbers().get(j) * number) % RGB_COLOR, super.city.getNumbers().get(j));
    }

}
