package Generator;

import Geometrics.Rectangle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by Wiktor on 01.11.2016.
 */
public class Controller {

    private int noOfInstances;
    private String filename;
    LinkedList<LinkedList<Rectangle>> generatedInstances = new LinkedList<>();

    /**
     * Constructor of class controller which generate problem instances using 4 types of generators
     * @param noOfInstances number of sets to generate
     * @param filename name of file to save generated rectangles
     * @param basicRectangle rectangle in which all rectangles will be generated
     */
    public Controller(int noOfInstances, String filename, Rectangle basicRectangle){
        this.noOfInstances = noOfInstances;
        this.filename = filename;
        ValidDataGenerator vdg = new ValidDataGenerator(basicRectangle);
        InvalidDataGenerator idg = new InvalidDataGenerator(basicRectangle);
        OverlapDataGenerator odg = new OverlapDataGenerator(basicRectangle);
        HollowDataGenerator hdg = new HollowDataGenerator(basicRectangle);
        generatedInstances.add(hdg.getRectangles());
        parseToTxtFile();
    }

    /**
     * Function generate .txt file with generated rectangles with specified format
     */
    private void parseToTxtFile(){
        try {
            PrintWriter writer = new PrintWriter(filename);
            for (LinkedList<Rectangle> list: generatedInstances){
                String row = "[";
                for(Rectangle rectangle: list){
                    row = row + " [" + rectangle.getFirst().getX() + "," + rectangle.getFirst().getY() + ","
                            + rectangle.getSecond().getX() + "," + rectangle.getSecond().getY() + "],";
                }
                row = row.substring(0, row.length()-1) + " ]";

                writer.println(row);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
