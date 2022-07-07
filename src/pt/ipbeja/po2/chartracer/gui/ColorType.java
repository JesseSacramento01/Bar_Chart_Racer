package pt.ipbeja.po2.chartracer.gui;

import pt.ipbeja.po2.chartracer.model.City;

import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 07/07/2022
 */
public interface ColorType {
    void colorsByContinent(List<City> cityList);
    void colorsByYear(List<City> cityList);
}
