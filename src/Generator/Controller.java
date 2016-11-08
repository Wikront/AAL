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
     * Controller is a class that generate test data using two classes InvalidDataGenerator,
     * and ValidDataGenerator, and save it in text file in proper format
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
