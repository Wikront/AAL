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

    public GraphicsPainter(int windowX, int windowY, RectanglesSet rectanglesSet, int multiplyIndex){
        this.windowX = windowX;
        this.windowY = windowY;
        this.rectanglesSet = rectanglesSet;
        this.multiplyIndex = multiplyIndex;
    }

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

    private void paintRect(Graphics g, int x1, int y1, int x2, int y2){
        int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B= (int)(Math.random( )*256);
        Color randomColor = new Color(R, G, B);
        g.setColor(randomColor);
        g.fillRect(x1, windowY - y2 -100, Math.abs(x2-x1), Math.abs(y1-y2) );
    }
}
