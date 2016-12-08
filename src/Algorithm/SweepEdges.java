package Algorithm;

import Geometrics.Edge;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Wiktor on 08.11.2016.
 */
public class SweepEdges {

    private Edge[] edges;
    private int B;
    private int U;
    private int L;
    private int R;

    public SweepEdges(Edge[] edges, int B, int U, int L, int R){
        this.edges = edges;
        this.B = B;
        this.U = U;
        this.L = L;
        this.R = R;
    }

    public boolean performSweep(){
        LinkedList<Edge> interval = new LinkedList<>();
        int valueOfXCoordinate = edges[0].getFirst().getX();

        for (int i = 0; i < edges.length; i++){
            Edge edge = edges[i];

            if(edge.getFirst().getX() != valueOfXCoordinate) {
                Collections.sort(interval);
                if(!checkCoverage(interval))
                    return false;

                if(interval.size() == 0 && i != edges.length - 1)
                    return false;
            }

            if(edge.getType() == 0)
                interval.add(edge);
            else
                interval.remove(edge);

            if(i == edges.length - 1){
                Collections.sort(interval);
                if(!checkCoverage(interval))
                    return false;
            }

            valueOfXCoordinate = edge.getFirst().getX();
        }

        return true;
    }

    private boolean checkCoverage(LinkedList<Edge> interval) {

        int nextYcoordinate = B;

        for(int i = 0; i < interval.size(); i++){
            Edge edge = interval.get(i);
            if(edge.getFirst().getY() != nextYcoordinate)
                return false;

            nextYcoordinate = edge.getSecond().getY();

            if (i == interval.size() - 1 && edge.getSecond().getY() != U)
                return false;

        }

        return true;
    }

}
