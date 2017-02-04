package SimpleVisualization;

import Geometrics.*;
import Geometrics.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor on 23.10.2016.
 */
public class Visualizator extends JFrame{

    private GraphicsPainter graphicsPainter;
    int width = 1024;
    int height = 768;

    /**
     * Provide and manage visualization of RectanglesSet
     * @param rectanglesSet RectanglesSet
     */
    public Visualizator(RectanglesSet rectanglesSet) {

        Point maxCoordinates = rectanglesSet.getMaxCoordinates();
        int x = maxCoordinates.getX();
        int y = maxCoordinates.getY();

        int multiplyIndex = (int)((width/x)*0.8);
        if(multiplyIndex > (int)((height/y)*0.8) )
            multiplyIndex = (int)((height/y)*0.8);

        graphicsPainter = new GraphicsPainter(width, height, rectanglesSet, multiplyIndex);

        //window settings
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,1,0,0));
        this.setSize(new Dimension(width, height));
        //this.setResizable(false);
        this.add(graphicsPainter);
        this.setVisible(true);

    }

}


