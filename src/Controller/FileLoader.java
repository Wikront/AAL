package Controller;

import Geometrics.Point;
import Geometrics.Rectangle;
import Geometrics.RectanglesSet;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Wiktor on 29.10.2016.
 */
public class FileLoader {

    private BufferedReader bufferedReader;
    private String filename;
    public LinkedList<RectanglesSet> problemInstances = new LinkedList<>();

    /**
     * Constructor of class loading file and create rectangles set
     * @param filename name of file to load
     * @throws NullPointerException file may not exist
     */
    public FileLoader(String filename) throws NullPointerException{
        this.filename = filename;
        String nextLine;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            int lineCounter = 1;
            while((nextLine = bufferedReader.readLine()) != null){
                if(nextLine.charAt(0) != '[') {
                    System.out.println("Wrong data format at line " + lineCounter + ", make sure that correct file is selected");
                    continue;
                }

                nextLine = nextLine.substring(1, nextLine.length());

                try {
                    parseToRectangles(nextLine);
                } catch (Exception e) {
                    System.out.println("Wrong data format at line " + lineCounter + ", make sure that correct file is selected");
                    return;
                }
                //System.out.println(nextLine);

                lineCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseToRectangles(String textLine) throws Exception{
        LinkedList<String> substrings = new LinkedList<>();
        int startSubstringIndex = 0;

        for(int i = 0; i < textLine.length(); i++){

            char currentCharacter = textLine.charAt(i);

            switch (currentCharacter){
                case '[':
                    startSubstringIndex = i;
                    break;

                case ']':
                    if(startSubstringIndex == 0)
                        break;
                    substrings.add(textLine.substring(startSubstringIndex, i+1));
                    startSubstringIndex = 0;
                    break;

                default:
                    continue;
            }

        }

        problemInstances.add(new RectanglesSet(substrings.size()));

        int rectanglesCounter = 0;
        for(String string: substrings){
            startSubstringIndex = 0;
            int[] coordinates = new int[4];
            int coordinatesCounter = 0;
            for(int i = 0; i < string.length(); i++ ){
                char currentCharacter = string.charAt(i);

                switch (currentCharacter){
                    case '[':
                        startSubstringIndex = i+1;
                        break;

                    case ',':
                        coordinates[coordinatesCounter] = Integer.parseInt(string.substring(startSubstringIndex, i));
                        startSubstringIndex = i+1;
                        coordinatesCounter++;
                        break;

                    case ']':
                        coordinates[coordinatesCounter] = Integer.parseInt(string.substring(startSubstringIndex, i));
                        Point first = new Point(coordinates[0], coordinates[1]);
                        Point second = new Point(coordinates[2], coordinates[3]);

                        Rectangle rectangle = new Rectangle(first, second);

                        problemInstances.get(problemInstances.size()-1).setRectangle(rectanglesCounter, rectangle);
                        rectanglesCounter++;

                    default:
                        continue;

                }
            }
        }

    }
}
