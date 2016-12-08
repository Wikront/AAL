package Geometrics;

/**
 * Created by Wiktor on 08.11.2016.
 */
public class Edge implements Comparable{

    private Point first;
    private Point second;
    private int type;

    /**
     * Constructor of class Edge represent single edge in algorithm
     * which is line parallel to y-axis
     * @param first bottom Point of Edge
     * @param second top Point of Edge
     * @param type type Of edge: value 0 for opening and 1 for closing Edge
     */
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

    @Override
    public boolean equals(Object obj){
        return obj != null && obj instanceof Edge
                && this.getFirst().getY() == ((Edge) obj).getFirst().getY()
                && this.getSecond().getY() == ((Edge) obj).getSecond().getY();
    }

    @Override
    public int compareTo(Object obj) {
        if (this.getFirst().getY() > ((Edge)obj).getFirst().getY())
            return 1;
        if (this.getFirst().getY() < ((Edge)obj).getFirst().getY())
            return -1;

        return 0;
    }
}
