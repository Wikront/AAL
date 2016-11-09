package Algorithm;

import Geometrics.Edge;

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
        for (int i = 0; i < edges.length; i++){
            Edge edge = edges[i];

            if(edge.getType() == 0)
                
        }

        return true;
    }

}
