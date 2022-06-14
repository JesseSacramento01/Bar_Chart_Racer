package pt.ipbeja.po2.chartracer.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jessé Sacramento
 * @version 11/06/2022
 */
public class BarModel {

    public List<Color> getColors(){
        List<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        return colors;
    }

    public int generateRandomNumber(int limit){
        return (int) (Math.random() * limit);
    }
}
