package Geometrics;

import java.util.ArrayList;

/**
 * Created by Wiktor on 30.10.2016.
 */
public class RectanglesSet {
    private Rectangle[] rectangles;
    int size;

    public RectanglesSet(int size){
        this.size = size;
        rectangles = new Rectangle[size];
    }

    public Rectangle getRectangle(int index){return rectangles[index];}

    public void setRectangle(int index, Rectangle rectangle){
        rectangles[index] = rectangle;
    }

    public int size(){
        return size;
    }

    public Rectangle[] getRectangles(){return rectangles;}
}
