import Algorithm.AlgorithmController;
import Controller.FileLoader;
import Generator.Controller;
import Geometrics.Point;
import Geometrics.Rectangle;
import Geometrics.RectanglesSet;
import SimpleVisualization.Visualizator;

import java.util.LinkedList;

/**
 * Created by Wiktor on 23.10.2016.
 */
public class Main {
    public static void main(String [ ] args){

        int x = 200;
        int y = 200;

        Controller controller = new Controller(1, "file.txt", new Rectangle(new Point(2,2), new Point(x,y)));

        //FileLoader fileLoader = new FileLoader("C:\\Users\\Wiktor\\IdeaProjects\\AAL\\src\\testdata.txt");
        FileLoader fileLoader = new FileLoader("C:\\Users\\Wiktor\\IdeaProjects\\AAL\\file.txt");
        LinkedList<RectanglesSet> problem = fileLoader.problemInstances;

        for(int i = 0; i < problem.size(); i++){
            RectanglesSet rectanglesSet = problem.get(i);
            for(int j = 0; j < rectanglesSet.size(); j++){
                System.out.println(rectanglesSet.getRectangle(j).getFirst().getX() + " " + rectanglesSet.getRectangle(j).getFirst().getY()
                        + " " +  rectanglesSet.getRectangle(j).getSecond().getX() + " " +  rectanglesSet.getRectangle(j).getSecond().getY());
            }
        }

        int problemInstance = 0;
        Point maxCoordinates = new Point(x,y);

        //AlgorithmController algorithmController = new AlgorithmController(problem.get(problemInstance));

        Visualizator visualizator = new Visualizator(problem.get(problemInstance), maxCoordinates);

        System.out.println("---------------------------------");

    }
}
