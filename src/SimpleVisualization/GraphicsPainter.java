package SimpleVisualization;

import Geometrics.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Wiktor on 31.10.2016.
 */
public class GraphicsPainter extends JPanel {

    private int windowX;
    private int windowY;
    private RectanglesSet rectanglesSet;
    private int multiplyIndex;

    /**
     * Constructor of class GraphicsPainter painting RectanglesSet on screen
     * @param windowX window width
     * @param windowY window height
     * @param rectanglesSet RectanglesSet to paint
     * @param multiplyIndex value which is used to scale rectangles
     */
    public GraphicsPainter(int windowX, int windowY, RectanglesSet rectanglesSet, int multiplyIndex){
        this.windowX = windowX;
        this.windowY = windowY;
        this.rectanglesSet = rectanglesSet;
        this.multiplyIndex = multiplyIndex;
    }

    /**
     * Paints all Rectangles from RectanglesSet
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        //g.fillRect(230,80,10,10);
        for (int i = 0; i < rectanglesSet.size(); i++){
            paintRect(g, rectanglesSet.getRectangle(i).getFirst().getX()*multiplyIndex, rectanglesSet.getRectangle(i).getFirst().getY()*multiplyIndex,
                    rectanglesSet.getRectangle(i).getSecond().getX()*multiplyIndex, rectanglesSet.getRectangle(i).getSecond().getY()*multiplyIndex);
        }

    }

    /**
     * Paints single Rectangle form RectanglesSet
     * @param g Graphics
     * @param x1 x-axis coordinate of bottom left point
     * @param y1 y-axis coordinate of bottom left point
     * @param x2 x-axis coordinate of top right point
     * @param y2 y-axis coordinate of top right point
     */
    private void paintRect(Graphics g, int x1, int y1, int x2, int y2){
        int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B= (int)(Math.random( )*256);
        Color randomColor = new Color(R, G, B);
        g.setColor(randomColor);
        g.fillRect(x1, windowY - y2 -100, Math.abs(x2-x1), Math.abs(y1-y2) );
    }
}
