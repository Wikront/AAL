package Generator;

import Geometrics.Point;
import Geometrics.Rectangle;

import java.util.Random;

/**
 * Created by Wiktor on 01.11.2016.
 */
public class ValidDataGenerator extends DataGenerator{

    /**
     * Create data which is valid for algorithm, divide given rectangle in smaller parts randomly
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     */
    public ValidDataGenerator(Rectangle basicRectangle){
        super(basicRectangle);
        doGenerate(basicRectangle);
        Random generator = new Random();
        int noOfDivides = generator.nextInt(2) + 1;
        for(int i = 0; i < noOfDivides; i++){
            if(rectangles.size() > 0) {
                int index = findBiggestRectangle();
                doGenerate(rectangles.get(index));
                rectangles.remove(index);
            }
        }
    }

    /**
     * Another constructor which create data set with given size by dividing rectangles only on two parts
     * @param basicRectangle rectangles in which all generated rectangles will be placed
     * @param numberOfRectangles size of problem instance
     */
    public ValidDataGenerator(Rectangle basicRectangle, int numberOfRectangles){
        super(basicRectangle);
        //doGenerate(basicRectangle);
        rectangles.add(basicRectangle);
        Random generator = new Random();
        int noOfDivides = numberOfRectangles - 1;
        for(int i = 0; i < noOfDivides; i++){
            if(rectangles.size() > 0) {
                int index = findBiggestRectangle();
                System.out.println(index);
                try {
                    doGenerateOnTwo(rectangles.get(index));
                }catch (IllegalArgumentException e){

                }
                rectangles.remove(index);
            }
        }
    }

    /**
     * Method which is used to find biggest rectangle in all so far generated set
     * @return index of biggest rectangle
     */
    private int findBiggestRectangle(){
        int maxArea = 0;
        int index = 0;
        for (int i = 0; i < rectangles.size(); i++){
            Rectangle rectangle = rectangles.get(i);
            int area = Math.abs(rectangle.getFirst().getX() - rectangle.getSecond().getX()) * Math.abs(rectangle.getFirst().getY() - rectangle.getSecond().getY());
            if(area > maxArea) {
                maxArea = area;
                index = i;
            }
        }

        return index;
    }

    /**
     * Method generate next rectangles by performing dividing already generated rectangles
     * @param basicRectangle rectangle which will be divided
     */
    @Override
    protected void doGenerate(Rectangle basicRectangle){
        //select point of cuts
        Random generator = new Random();
        int x = generator.nextInt(basicRectangle.getSecond().getX()-basicRectangle.getFirst().getX() - 1) + basicRectangle.getFirst().getX()+1;
        int y = generator.nextInt(basicRectangle.getSecond().getY()-basicRectangle.getFirst().getY() - 1) + basicRectangle.getFirst().getY()+1;

        Point insidePoint = new Point(x, y);

        int noOfParts = generator.nextInt(3);

        switch(noOfParts){
            case 0:
                divideOnTwo(insidePoint, basicRectangle);
                break;
            case 1:
                divideOnThree(insidePoint, basicRectangle);
                break;
            case 2:
                divideOnFour(insidePoint, basicRectangle);
                break;
        }


    }

    /**
     * Method perform dividing rectangle only on two parts - used in mode with specified size of generated set
     * @param basicRectangle rectangle which will be divided
     */
    protected void doGenerateOnTwo(Rectangle basicRectangle){
        //select point of cuts
        Random generator = new Random();
        int x = generator.nextInt(basicRectangle.getSecond().getX()-basicRectangle.getFirst().getX() - 1) + basicRectangle.getFirst().getX()+1;
        int y = generator.nextInt(basicRectangle.getSecond().getY()-basicRectangle.getFirst().getY() - 1) + basicRectangle.getFirst().getY()+1;

        Point insidePoint = new Point(x, y);

        divideOnTwo(insidePoint, basicRectangle);

    }

    /**
     * Method perform dividing rectangle on four parts
     * @param insidePoint common point for all rectangles
     * @param basicRectangle rectangle which will be divided
     */
    private void divideOnFour(Point insidePoint, Rectangle basicRectangle){
        Point helpPoint1 = new Point(basicRectangle.getFirst().getX(), insidePoint.getY());
        Point helpPoint2 = new Point(insidePoint.getX(), basicRectangle.getSecond().getY());

        rectangles.add(new Rectangle(helpPoint1, helpPoint2));

        rectangles.add(new Rectangle(insidePoint, basicRectangle.getSecond()));

        rectangles.add(new Rectangle(basicRectangle.getFirst(), insidePoint));

        helpPoint1 = new Point(insidePoint.getX(), basicRectangle.getFirst().getY());
        helpPoint2 = new Point(basicRectangle.getSecond().getX(), insidePoint.getY());

        rectangles.add(new Rectangle(helpPoint1,helpPoint2));
    }

    /**
     * Method perform dividing rectangle on three parts
     * @param insidePoint common point for all rectangles
     * @param basicRectangle rectangle which will be divided
     */
    private void divideOnThree(Point insidePoint, Rectangle basicRectangle){
        Random generator = new Random();
        int choosePart = generator.nextInt(4);

        Point helpPoint1 = new Point(basicRectangle.getFirst().getX(), insidePoint.getY());
        Point helpPoint2 = new Point(insidePoint.getX(), basicRectangle.getSecond().getY());
        Point helpPoint3 = new Point(insidePoint.getX(), basicRectangle.getFirst().getY());
        Point helpPoint4 = new Point(basicRectangle.getSecond().getX(), insidePoint.getY());

        switch (choosePart){
            case 0:
                //top part connected
                rectangles.add(new Rectangle(helpPoint1, basicRectangle.getSecond()));
                rectangles.add(new Rectangle(basicRectangle.getFirst(), insidePoint));
                rectangles.add(new Rectangle(helpPoint3, helpPoint4));
                break;
            case 1:
                //left part connected
                rectangles.add(new Rectangle(basicRectangle.getFirst(), helpPoint2));
                rectangles.add(new Rectangle(insidePoint, basicRectangle.getSecond()));
                rectangles.add(new Rectangle(helpPoint3, helpPoint4));
                break;
            case 2:
                //right part connected
                rectangles.add(new Rectangle(helpPoint3, basicRectangle.getSecond()));
                rectangles.add(new Rectangle(basicRectangle.getFirst(), insidePoint));
                rectangles.add(new Rectangle(helpPoint1, helpPoint2));
                break;
            case 3:
                //bottom part connected
                rectangles.add(new Rectangle(basicRectangle.getFirst(), helpPoint4));
                rectangles.add(new Rectangle(helpPoint1, helpPoint2));
                rectangles.add(new Rectangle(insidePoint, basicRectangle.getSecond()));
                break;
        }

    }

    /**
     * Method perform dividing rectangle on two parts
     * @param insidePoint common point for all rectangles
     * @param basicRectangle rectangle which will be divided
     */
    public void divideOnTwo(Point insidePoint, Rectangle basicRectangle){
        Random generator = new Random();
        int choosePart = generator.nextInt(2);

        Point helpPoint1 = new Point(basicRectangle.getFirst().getX(), insidePoint.getY());
        Point helpPoint2 = new Point(insidePoint.getX(), basicRectangle.getSecond().getY());
        Point helpPoint3 = new Point(insidePoint.getX(), basicRectangle.getFirst().getY());
        Point helpPoint4 = new Point(basicRectangle.getSecond().getX(), insidePoint.getY());

        switch (choosePart){
            case 0:
                //bottom up
                rectangles.add(new Rectangle(helpPoint1, basicRectangle.getSecond()));
                rectangles.add(new Rectangle(basicRectangle.getFirst(), helpPoint4));
                break;
            case 1:
                //left right
                rectangles.add(new Rectangle(basicRectangle.getFirst(), helpPoint2));
                rectangles.add(new Rectangle(helpPoint3, basicRectangle.getSecond()));
                break;
        }
    }
}
