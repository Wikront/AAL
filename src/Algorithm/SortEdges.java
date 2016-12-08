package Algorithm;

import Geometrics.Edge;

/**
 * Created by Wiktor on 08.11.2016.
 */
public class SortEdges {

    private Edge[] edges;
    private Edge[] helper;

    private int size;

    public SortEdges(Edge[] edges){
        this.edges = edges;
        size = edges.length;
        this.helper = new Edge[size];
        mergeSort(0, size - 1);
    }

    private void mergeSort(int low, int high){
        if(low < high){
            int middle = low + (high - low)/2;
            mergeSort(low, middle);
            mergeSort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high){
        for(int i = low; i <= high; i++){
            helper[i] = edges[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high){
            if(helper[i].smallerThan(helper[j])){
                edges[k] = helper[i];
                i++;
            } else {
                edges[k] = helper[j];
                j++;
            }
            k++;
        }

        while (i <= middle){
            edges[k] = helper[i];
            k++;
            i++;
        }

    }

    public Edge[] getEdges(){
        return edges;
    }
}
