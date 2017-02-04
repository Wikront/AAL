import Algorithm.AlgorithmController;
import Controller.FileLoader;
import Generator.Controller;
import Geometrics.Edge;
import Geometrics.Point;
import Geometrics.Rectangle;
import Geometrics.RectanglesSet;
import SimpleVisualization.Visualizator;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Wiktor on 23.10.2016.
 */
public class Main {
    public static void main(String [ ] args){

        try {
            if (args[0].equals("-f")) {
                String file = args[1];
                String visualisation = args[2];
                int visualisationIndex;
                if (visualisation.equals("-v"))
                    visualisationIndex = Integer.parseInt(args[3]);
                else
                    visualisationIndex = -1;
                doFileLoadingMode(file, visualisationIndex);
            } else if (args[0].equals("-d")) {
                String data = "";
                for (int i = 1; i < args.length; i++) {
                    data += args[i];
                }
                doInsertDataMode(data);
            } else if (args[0].equals("-g")) {
                int numberOfgenerations = Integer.parseInt(args[1]);
                String generationType = args[2];
                String sizeOfRectangle = args[3];
                int x1 = Integer.parseInt(args[4]);
                int y1 = Integer.parseInt(args[5]);
                int x2 = Integer.parseInt(args[6]);
                int y2 = Integer.parseInt(args[7]);
                String visualisation = args[8];
                int visualisationIndex;
                if (visualisation.equals("-v"))
                    visualisationIndex = Integer.parseInt(args[9]);
                else
                    visualisationIndex = -1;
                int typeOfGeneration;
                if (generationType.equals("-a"))
                    typeOfGeneration = 4;
                else
                    typeOfGeneration = Integer.parseInt(generationType.substring(1, 1));

                if (sizeOfRectangle.equals("-s"))
                    doGenerationMode(numberOfgenerations, new Rectangle(new Point(x1, y1), new Point(x2, y2)), typeOfGeneration, visualisationIndex);

            } else if (args[0].equals("-t")) {
                int numberOfgenerations = Integer.parseInt(args[1]);
                String sizeOfRectangle = args[2];
                int x1 = Integer.parseInt(args[3]);
                int y1 = Integer.parseInt(args[4]);
                int x2 = Integer.parseInt(args[5]);
                int y2 = Integer.parseInt(args[6]);
                if (sizeOfRectangle.equals("-s"))
                    doTestMode(numberOfgenerations, new Rectangle(new Point(x1, y1), new Point(x2, y2)));
            }
        }catch (Exception e){
            printMessage();
        }
    }

    /**
     * Function perform execution with loading data from file
     * @param filePath path to file
     * @param visualisationIndex index of rectangles set which will be visualised
     */
    static void doFileLoadingMode(String filePath, int visualisationIndex){
        FileLoader fileLoader = new FileLoader(filePath);
        LinkedList<RectanglesSet> problem = fileLoader.problemInstances;

        if(visualisationIndex >= 0) {
            Visualizator visualizator = new Visualizator(problem.get(visualisationIndex));
        }

        for(int i = 0; i < problem.size(); i++) {
            AlgorithmController algorithmController = new AlgorithmController(problem.get(i));
            if(algorithmController.getResult()){
                System.out.println("spójny");
            }else {
                System.out.println("nie spójny");
            }
        }
    }

    /**
     * Function which perform execition with data set inserted by user
     * @param data rectengles set
     */
    static void doInsertDataMode(String data){
        FileLoader fileLoader = new FileLoader();
        try {
            fileLoader.parseToRectangles(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinkedList<RectanglesSet> problem = fileLoader.problemInstances;
        Visualizator visualizator = new Visualizator(problem.get(0));
        AlgorithmController algorithmController = new AlgorithmController(problem.get(0));
        if(algorithmController.getResult()){
            System.out.println("spójny");
        }else {
            System.out.println("nie spójny");
        }
    }

    /**
     * Function which perform execution with gerating data
     * @param numberOfGenerations number of instances
     * @param rectangle area of generations
     * @param generationType type of generator used
     * @param visualisationIndex index of rectangles set which will be visualised
     */
    static void doGenerationMode(int numberOfGenerations, Rectangle rectangle, int generationType, int visualisationIndex){
        Controller controller = new Controller(numberOfGenerations, "file.txt", rectangle, generationType);
        LinkedList<RectanglesSet> problem = controller.prepareRectanglesSet();

        if(visualisationIndex >= 0) {
            Visualizator visualizator = new Visualizator(problem.get(visualisationIndex));
        }

        for(int i = 0; i < problem.size(); i++) {
            AlgorithmController algorithmController = new AlgorithmController(problem.get(i));
            if(algorithmController.getResult()){
                System.out.println("spójny");
            }else {
                System.out.println("nie spójny");
            }

        }
    }

    /**
     * Function which perform execution with benchmarking
     * @param numberOfGenerations number of rectangles set
     * @param rectangle generation area
     */
    static void doTestMode(int numberOfGenerations, Rectangle rectangle){
        long elapsedTimeMillisMid = 0;
        for(int i = 0; i < 10; i++) {
            Controller controllerMid = new Controller(rectangle, (numberOfGenerations+1)/2);
            LinkedList<RectanglesSet> problemMid = controllerMid.prepareRectanglesSet();
            long startMid = System.currentTimeMillis();
            AlgorithmController algorithmControllerMid = new AlgorithmController(problemMid.get(0));
            long time = System.currentTimeMillis() - startMid;
            if(time > elapsedTimeMillisMid)
                elapsedTimeMillisMid = time;
        }
        double logMid = Math.log((numberOfGenerations+1)/2);

        for(int j = 0; j < numberOfGenerations; j++) {
            ArrayList<Long> times = new ArrayList<>();
            long longestTime = 0;
            for (int i = 0; i < 10; i++) {
                Controller controller = new Controller(rectangle, j + 1);
                LinkedList<RectanglesSet> problem = controller.prepareRectanglesSet();
                long start = System.currentTimeMillis();
                AlgorithmController algorithmController = new AlgorithmController(problem.get(0));
                long elapsedTimeMillis = System.currentTimeMillis() - start;
                // times.add(elapsedTimeMillis);
                times.add(elapsedTimeMillis);
            }
            /*for(Long time: times){
                longestTime += time;
            }*/
            longestTime = times.get(6);
            System.out.println((j+1) +"     " + longestTime + "         " + (longestTime * ((numberOfGenerations+1)/2)*logMid)/((j+1)*Math.log(j+1)*elapsedTimeMillisMid));

        }

    }

    public static void printMessage(){
        System.out.println("Niepoprawny format danych.");
        System.out.println("Aby uruchomić program użyj jednego z dostępnych trybów:");
        System.out.println("$ java Main -f [ścieżka do pliku] -v [index]");
        System.out.println("$ java Main -d [dane_testowe] -v [index]");
        System.out.println("$java Main -g [liczba instancji] -a -s [x1] [y1] [x2] [y2] -v [index]");
        System.out.println("$java -Djava.compiler=NONE Main -t  [liczba instancji] -s  [x1] [y1] [x2] [y2]");
    }
}
