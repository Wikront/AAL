package Generator;

import Geometrics.Point;
import Geometrics.Rectangle;

import java.util.Random;

/**
 * Created by Wiktor on 01.11.2016.
 */
public class InvalidDataGenerator extends DataGenerator{

    /**
     * Create random number of rectangles on random positions in given rectangle
     * TODO: Generated rectangles set may be valid for algorithm - this should be fixed
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     */
    public InvalidDataGenerator(Rectangle basicRectangle){
        super(basicRectangle);
        Random generator = new Random();
        int noOfRectangles = generator.nextInt(9) + 2;
        for(int i = 0; i < noOfRectangles; i++){
            doGenerate(basicRectangle);
        }
    }

    protected void doGenerate(Rectangle basicRectangle){
        Random generator = new Random();

        int x1 = generator.nextInt((basicRectangle.getSecond().getX() ) - basicRectangle.getFirst().getX()) + basicRectangle.getFirst().getX();
        int y1 = generator.nextInt((basicRectangle.getSecond().getY() ) - basicRectangle.getFirst().getY()) + basicRectangle.getFirst().getY();

        int x2 = generator.nextInt((basicRectangle.getSecond().getX() +1) - x1 - 1) + x1 + 1;
        int y2 = generator.nextInt((basicRectangle.getSecond().getY() +1) - y1 - 1) + y1 + 1;

        Rectangle rectangle = new Rectangle(new Point(x1, y1), new Point(x2, y2));

        rectangles.add(rectangle);
    }
}
