// == CS400 Project Three File Header ==
// Name: Tianrui Yang
// CSL Username: tianruiy
// Email: tyang325@wisc.edu
// Lecture: 002
// Notes to Grader: none

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that reads and parses the JSON file, returning a list of Path objects
 */
public class loadFile implements IloadFile {

    /**
     * Parses the JSON file and returns a list of the path objects.
     */
    public AirportGraph<String, Double> loadFilePath(String filepath) throws FileNotFoundException {
    	AirportGraph<String,Double> paths = new AirportGraph<>(); // List to return
        File flightPaths = new File(filepath); // File to load data in
        Scanner fileReader = new Scanner(flightPaths); // Scans the file to look in.
        String line = ""; // Temporary storing variable for each line.

        if (!flightPaths.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }

        String origin = "";
        String destination = "";
        double distance = 0.0;

        // Iterates through each line of the file.
        while (fileReader.hasNext()) {

            line = fileReader.nextLine();

            // Edits out the useless beginning and ending lines
            if (line.equals("digraph G {")){
                line = fileReader.nextLine();
            }
            if (line.equals("}")){
                System.out.println("get end");
                return paths;}

            int i=0; //used to count the number of items in one line

            for (String arg : line.split(" ")) {
                //the format of line will be like"ANC -> SEA [weight = "1448"];",
                // which means first item is origin, third item is destination, and last item contains distance

                //get the first item in this line
                if(i==0) {
                    origin = arg;
                }
              //get third item
                if(i==2) {
                    destination = arg;
                }

                //get last item
                if(i == 5){
                    distance = Integer.parseInt(arg.substring(1,arg.length()-3));

                    paths.insertAirport(origin);
                    paths.insertAirport(destination);
                    paths.insertRoute(origin,destination,distance);
                    //reset the count number
                    i=0;
                    break;
                }
                i++;
            }

            // Creates a path object with the temporary variables and adds it to the list.


            //System.out.println("from: "+origin+"\n to: "+destination+"\n with "+distance);
        }
        return paths;
    }
}