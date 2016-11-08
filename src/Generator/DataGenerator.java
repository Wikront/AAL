package Generator;

import Geometrics.Rectangle;

import java.util.LinkedList;

/**
 * Created by Wiktor on 06.11.2016.
 */
public abstract class DataGenerator {

    protected Rectangle basicRectangle;
    protected LinkedList<Rectangle> rectangles;

    public DataGenerator(Rectangle basicRectangle){
        this.basicRectangle = basicRectangle;
        rectangles = new LinkedList<>();
    }

    protected abstract void doGenerate(Rectangle basicRectangle);

    public LinkedList<Rectangle> getRectangles() {return rectangles;}
}
