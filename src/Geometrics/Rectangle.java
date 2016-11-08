package Geometrics;

/**
 * Created by Wiktor on 29.10.2016.
 */
public class Rectangle {

    private Point first;
    private Point second;

    public Rectangle(Point first, Point second){
        this.first = first;
        this.second = second;
    }

    public Point getFirst(){
        return this.first;
    }

    public Point getSecond(){ return this.second; }

    public void setFirst(Point newFirst){
        this.first = newFirst;
    }

}
