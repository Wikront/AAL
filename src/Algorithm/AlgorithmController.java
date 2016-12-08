package Algorithm;

import Geometrics.Edge;
import Geometrics.Point;
import Geometrics.Rectangle;
import Geometrics.RectanglesSet;

/**
 * Created by Wiktor on 07.11.2016.
 */
public class AlgorithmController {

    private RectanglesSet rectanglesSet;
    private Rectangle[] rectangles;
    private Edge[] edgesSet;

    private int B; // y coordinate for bottom point from bottom - up interval
    private int U; // y coordinate for up point from bottom - up interval
    private int L; // x coordinate for left point from left - right interval
    private int R; // x coordinate for right point from left - right interval

    public AlgorithmController(RectanglesSet rectanglesSet){
        this.rectanglesSet = rectanglesSet;
        rectangles = rectanglesSet.getRectangles();
        B = Integer.MAX_VALUE;
        U = 0;
        L = Integer.MAX_VALUE;
        R = 0;
        calculateIntervals();
        createEdgesSet();
        SortEdges sortEdges = new SortEdges(edgesSet);
        edgesSet = sortEdges.getEdges();
        SweepEdges sweepEdges = new SweepEdges(edgesSet, B, U, L, R);
        if(sweepEdges.performSweep())
            System.out.println("spojny");
        else
            System.out.println("nie spojny");
        System.out.println("---------------------------------");
        System.out.println(B + " " + U + " " + L + " " + R);
    }

    private void calculateIntervals(){
        for(int i = 0; i < rectanglesSet.size(); i++){
            Point lb = rectangles[i].getFirst();
            Point ru = rectangles[i].getSecond();

            if(lb.getY() < B)
                B = lb.getY();

            if(lb.getX() < L)
                L = lb.getX();

            if(ru.getY() > U)
                U = ru.getY();

            if(ru.getX() > R)
                R = ru.getX();
        }
    }

    private void createEdgesSet(){
        edgesSet = new Edge[rectanglesSet.size()*2];

        for(int i = 0; i < rectanglesSet.size(); i++){
            Point lu = new Point(rectangles[i].getFirst().getX(), rectangles[i].getSecond().getY());
            Point rb = new Point(rectangles[i].getSecond().getX(), rectangles[i].getFirst().getY());

            edgesSet[i*2] = new Edge(rectangles[i].getFirst(), lu, 0);
            edgesSet[i*2 + 1] = new Edge(rb, rectangles[i].getSecond(), 1);
        }

    }
}
