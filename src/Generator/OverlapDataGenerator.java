package Generator;

import Geometrics.Point;
import Geometrics.Rectangle;

import java.util.Random;

/**
 * Created by Wiktor on 06.11.2016.
 */
public class OverlapDataGenerator extends ValidDataGenerator {

    /**
     * Use valid data and create hollow set by enlarging one of rectangles
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     */
    public OverlapDataGenerator(Rectangle basicRectangle) {
        super(basicRectangle);
        Random generator = new Random();
        while (true) {
            int index = generator.nextInt(rectangles.size());
            //check if selected point has different x coordinate than basic rectangle to provide overlapping 
            if(rectangles.get(index).getFirst().getX() != basicRectangle.getFirst().getX()){
                rectangles.get(index).setFirst(new Point(basicRectangle.getFirst().getX(), rectangles.get(index).getFirst().getY()));
                break;
            }
        }


    }
}
