package Geometrics;

/**
 * Created by Wiktor on 08.11.2016.
 */
public class Edge {

    private Point first;
    private Point second;
    private int type;

    public Edge(Point first, Point second, int type){
        this.first = first;
        this.second = second;
        this.type = type;
    }

    public Point getFirst(){
        return this.first;
    }

    public Point getSecond(){ return this.second; }

    public int getType(){ return this.type; }

    public boolean smallerThan(Edge otherEdge){
        return (this.getFirst().getX() < otherEdge.getFirst().getX())||
                (this.getFirst().getX() == otherEdge.getFirst().getX() && this.type == 1 && otherEdge.getType() == 0)||
                (this.getFirst().getX() == otherEdge.getFirst().getX() && this.type == otherEdge.getType()
                        && this.getFirst().getY() < otherEdge.getFirst().getY());
    }
}
