package Geometrics;

/**
 * Created by Wiktor on 29.10.2016.
 */
public class Point {

    private int x;
    private int y;

    /**
     * Constructor of class point represents single point on a grid
     * @param x x-axis coordinate of point
     * @param y y-axis coordinate of point
     */
    public Point(int x,  int y){
        this.x = x;
        this.y = y;
    }

    public Point(Point point){
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Point
                && x == ((Point) obj).getX() &&
                    y == ((Point) obj).getY();

    }

}
