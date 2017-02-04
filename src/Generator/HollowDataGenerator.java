package Generator;

import Geometrics.Point;
import Geometrics.Rectangle;

import java.util.Random;

/**
 * Created by Wiktor on 06.11.2016.
 */
public class HollowDataGenerator extends ValidDataGenerator {

    /**
     * Use valid data and create hole deleting one of rectangles,
     * but only if generated rectangle will be hollow
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     */
    public HollowDataGenerator(Rectangle basicRectangle) {
        super(basicRectangle);
        Random generator = new Random();
        // calculate all points of basic rectangle
        Point lb = basicRectangle.getFirst();
        Point lu = new Point(basicRectangle.getFirst().getX(), basicRectangle.getFirst().getY());
        Point rb = new Point(basicRectangle.getSecond().getX(), basicRectangle.getFirst().getY());
        Point ru = basicRectangle.getSecond();

        while (true){
            int index = generator.nextInt(rectangles.size());
            Rectangle rectangle = rectangles.get(index);
            //calculate all points of selected rectangle
            Point rlb = rectangle.getFirst();
            Point rlu = new Point(rectangle.getFirst().getX(), rectangle.getFirst().getY());
            Point rrb = new Point(rectangle.getSecond().getX(), rectangle.getFirst().getY());
            Point rru = rectangle.getSecond();

            boolean leftUp = lu.equals(rlu);
            boolean rightUp = ru.equals(rru);
            boolean rightBottom = rb.equals(rrb);
            boolean leftBottom = lb.equals(rlb);

            if(!((leftUp&&leftBottom)||(leftUp&&rightUp)||(rightUp&&rightBottom)||(rightBottom&&leftBottom))){
                rectangles.remove(index);
                break;
            }
        }

    }

    /**
     * Constructor which let generate problem instance with given number of rectangles
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     * @param size number of rectangle
     */
    public HollowDataGenerator(Rectangle basicRectangle, int size) {
        super(basicRectangle, size + 1);
        Random generator = new Random();
        // calculate all points of basic rectangle
        Point lb = basicRectangle.getFirst();
        Point lu = new Point(basicRectangle.getFirst().getX(), basicRectangle.getFirst().getY());
        Point rb = new Point(basicRectangle.getSecond().getX(), basicRectangle.getFirst().getY());
        Point ru = basicRectangle.getSecond();

        while (true){
            int index = generator.nextInt(rectangles.size());
            Rectangle rectangle = rectangles.get(index);
            //calculate all points of selected rectangle
            Point rlb = rectangle.getFirst();
            Point rlu = new Point(rectangle.getFirst().getX(), rectangle.getFirst().getY());
            Point rrb = new Point(rectangle.getSecond().getX(), rectangle.getFirst().getY());
            Point rru = rectangle.getSecond();

            boolean leftUp = lu.equals(rlu);
            boolean rightUp = ru.equals(rru);
            boolean rightBottom = rb.equals(rrb);
            boolean leftBottom = lb.equals(rlb);

            if(!((leftUp&&leftBottom)||(leftUp&&rightUp)||(rightUp&&rightBottom)||(rightBottom&&leftBottom))){
                rectangles.remove(index);
                break;
            }
        }

    }
}
