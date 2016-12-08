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

    /**
     * Class provide algorithm which in details is described on attached pdf file
     * @param edges sorted edges set
     * @param B First point of "B - U interval"
     * @param U Second point of "B - U interval"
     * @param L First point of "L - R interval"
     * @param R Second point of "L - R interval"
     */
    public SweepEdges(Edge[] edges, int B, int U, int L, int R){
        this.edges = edges;
        this.B = B;
        this.U = U;
        this.L = L;
        this.R = R;
    }

    /**
     * Function realize algorithm
     * @return true when space is covered anf false when it is not
     */
    public boolean performSweep(){
        LinkedList<Edge> interval = new LinkedList<>();
        int valueOfXCoordinate = edges[0].getFirst().getX();

        // Iterate on all egdes
        for (int i = 0; i < edges.length; i++){
            Edge edge = edges[i];

            // On all x coordinates in L - R interval
            if(edge.getFirst().getX() != valueOfXCoordinate) {
                Collections.sort(interval);
                if(!checkCoverage(interval))
                    return false;

                // Set is separated on few parts
                if(interval.size() == 0 && i != edges.length - 1)
                    return false;
            }

            // Add opening edges and remove closing edges from interval
            if(edge.getType() == 0)
                interval.add(edge);
            else
                interval.remove(edge);

            // On last x coordinate
            if(i == edges.length - 1){
                Collections.sort(interval);
                if(!checkCoverage(interval))
                    return false;
            }

            valueOfXCoordinate = edge.getFirst().getX();
        }

        return true;
    }

    /**
     *
     * @param interval current B - U interval
     * @return true when whole interval is covered false when is not
     */
    private boolean checkCoverage(LinkedList<Edge> interval) {

        int nextYcoordinate = B;

        // Iterate on all edges in interval
        for(int i = 0; i < interval.size(); i++){
            Edge edge = interval.get(i);
            if(edge.getFirst().getY() != nextYcoordinate)
                return false;

            nextYcoordinate = edge.getSecond().getY();

            // Interval is not fitted in B - U algorithm
            if (i == interval.size() - 1 && edge.getSecond().getY() != U)
                return false;

        }

        return true;
    }

}
