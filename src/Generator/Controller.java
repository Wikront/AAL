package Generator;

import Geometrics.Rectangle;
import Geometrics.RectanglesSet;

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
    LinkedList<RectanglesSet> rectanglesSets;

    /**
     * Constructor of class controller which generate problem instances using 4 types of generators
     * Used to generate small <5000 sets of problem instances
     * @param noOfInstances number of sets to generate
     * @param filename name of file to save generated rectangles
     * @param basicRectangle rectangle in which all rectangles will be generated
     * @param mode type of generator which can be used to generate instances
     */
    public Controller(int noOfInstances, String filename, Rectangle basicRectangle, int mode){
        this.noOfInstances = noOfInstances;
        this.filename = filename;

        if(mode == 5){
            invalidgeneration(basicRectangle);
        }

        if(mode == 0){
            validGeneration(basicRectangle);
        }

        if(mode == 1){
            invalidGeneration2(basicRectangle);
        }

        if(mode == 2){
            overlapedGneration(basicRectangle);
        }

        if(mode == 3){
            hollowGeneration(basicRectangle);
        }

        if(mode == 4){
            allGenerations(basicRectangle);
        }

        //parseToTxtFile();
    }

    /**
     * Another Constructor used in benchmark mode generate only one set with InvalidDataGenerator
     * @param basicRectangle rectangle in which all rectangles will be generated
     * @param numberOfGenerations number of rectangles in set
     */
    public Controller(Rectangle basicRectangle, int numberOfGenerations){
        InvalidDataGenerator idg;
        idg = new InvalidDataGenerator(basicRectangle, numberOfGenerations );
        generatedInstances.add(idg.getRectangles());
    }

    /**
     * Function create list of rectangles sets form generated data
     * @return created list
     */
    public LinkedList<RectanglesSet> prepareRectanglesSet(){
        rectanglesSets = new LinkedList<>();
        for(LinkedList<Rectangle> linkedList: generatedInstances) {
            RectanglesSet rectanglesSet = new RectanglesSet(linkedList);
            rectanglesSets.add(rectanglesSet);
        }

        return rectanglesSets;
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

    private void invalidgeneration(Rectangle basicRectangle){
        int instancesCounter = 0;

        InvalidDataGenerator idg;
        while(instancesCounter < noOfInstances) {
            idg = new InvalidDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(idg.getRectangles());
            instancesCounter++;
        }
    }

    private void validGeneration(Rectangle basicRectangle){
        int instancesCounter = 0;

        while(instancesCounter < noOfInstances) {
            ValidDataGenerator vdg = new ValidDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(vdg.getRectangles());
            instancesCounter++;
        }
    }

    private void invalidGeneration2(Rectangle basicRectangle){
        int instancesCounter = 0;

        while(instancesCounter < noOfInstances) {
            InvalidDataGenerator idg = new InvalidDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(idg.getRectangles());
            instancesCounter++;
        }
    }

    private void overlapedGneration(Rectangle basicRectangle){
        int instancesCounter = 0;

        while(instancesCounter < noOfInstances) {
            OverlapDataGenerator odg = new OverlapDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(odg.getRectangles());
            instancesCounter++;
        }

    }

    private void hollowGeneration(Rectangle basicRectangle){
        int instancesCounter = 0;

        while(instancesCounter < noOfInstances) {
            HollowDataGenerator hdg = new HollowDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(hdg.getRectangles());
            instancesCounter++;
        }
    }

    private void allGenerations(Rectangle basicRectangle){
        int instancesCounter = 0;

        while(instancesCounter < noOfInstances) {
            ValidDataGenerator vdg = new ValidDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(vdg.getRectangles());
            instancesCounter++;
            if(instancesCounter >= noOfInstances)
                break;
            InvalidDataGenerator idg = new InvalidDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(idg.getRectangles());
            instancesCounter++;
            if(instancesCounter >= noOfInstances)
                break;
            OverlapDataGenerator odg = new OverlapDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(odg.getRectangles());
            instancesCounter++;
            if(instancesCounter >= noOfInstances)
                break;
            HollowDataGenerator hdg = new HollowDataGenerator(basicRectangle, (instancesCounter +1) );
            generatedInstances.add(hdg.getRectangles());
            instancesCounter++;
            if(instancesCounter >= noOfInstances)
                break;
        }
    }
}
