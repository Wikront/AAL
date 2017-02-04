package Geometrics;

import java.util.LinkedList;

/**
 * Created by Wiktor on 30.10.2016.
 */
public class RectanglesSet {
    private Rectangle[] rectangles;
    int size;

    /**
     * Constructor of class RectanglesSet represents all rectangles in problem instance
     * collected in one set
     * @param size Number of rectangles
     */
    public RectanglesSet(int size){
        this.size = size;
        rectangles = new Rectangle[size];
    }

    public RectanglesSet(LinkedList<Rectangle> rectangles){
        this.size = rectangles.size();
        this.rectangles = new Rectangle[size];
        for(int i = 0; i < size; i++){
            this.rectangles[i] = rectangles.get(i);
        }
    }

    public Rectangle getRectangle(int index){return rectangles[index];}

    public void setRectangle(int index, Rectangle rectangle){
        rectangles[index] = rectangle;
    }

    public int size(){
        return size;
    }

    public Rectangle[] getRectangles(){return rectangles;}

    public Point getMaxCoordinates(){
        int maxX = 0;
        int maxY = 0;

        for(int i = 0; i < rectangles.length; i ++){
            if(rectangles[i].getFirst().getX() > maxX)
                maxX = rectangles[i].getFirst().getX();

            if(rectangles[i].getFirst().getY() > maxY)
                maxY = rectangles[i].getFirst().getY();

            if(rectangles[i].getSecond().getX() > maxX)
                maxX = rectangles[i].getSecond().getX();

            if(rectangles[i].getSecond().getY() > maxY)
                maxX = rectangles[i].getSecond().getY();
        }

        return new Point(maxX, maxY);
    }
}
