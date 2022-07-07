package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.paint.Color;
import pt.ipbeja.po2.chartracer.model.BarModel;
import pt.ipbeja.po2.chartracer.model.City;


/**
 * @author Jessé Sacramento
 * @version 07/07/2022
 */
public class ColorsByYear extends Colors{
    ColorType colorType;
    private static final int RGB_COLOR = 255;


    public ColorsByYear(BarModel barModel, BarChartRacer barChartRacer, City city) {
        super(barModel,barChartRacer,city);
    }


    @Override
    public Color colorType(int j) {
        setColorType(super.city); // to set the view
        colorType.colorsByYear(super.cities);


        return Color.rgb(((super.city.getNumbers().get(j) * j) % RGB_COLOR)
                , (super.city.getNumbers().get(j)) % RGB_COLOR, super.city.getNumbers().get(j));
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    public void setColorType(ColorType colorType) {
        this.colorType = colorType;
    }
}
