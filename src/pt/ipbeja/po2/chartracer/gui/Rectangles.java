package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Jessé Sacramento
 * @version 07/07/2022
 */
public class Rectangles extends Group {

    Rectangle rectangle;

    public Rectangles(double x, double y, double width, double height, Color color){
        this.rectangle = drawRectangle(x,y,width,height,color);
    }

    public Rectangle drawRectangle(double x, double y, double width, double height, Color color){


        Rectangle rectangle = new Rectangle(x,y,width,height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(color);
        return rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
