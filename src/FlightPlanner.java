// --== CS400 Fall 2022 File Header Information ==--
// Name: Yuchen Wang
// Email: wang2673@wisc.edu
// Team: CL
// TA: ABHINAV AGARWAL
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightPlanner<NodeType, EdgeType extends Number> {

	private IPath pathObject;
    private IloadFile fileLoader;
    private P3AlgorithmEngineerInterface<String, Double> airportGraph;
    private IFlightPlanBackend backend;
    private FlightPlannerFrontend frontend;
    private Scanner s;

    public void startApp() throws IllegalArgumentException, FileNotFoundException {
        fileLoader = new loadFile();
        try {
            airportGraph = fileLoader.loadFilePath("src/Flight.dot");//Users/angus/eclipse-workspace/Project3/src/Flight.dot
        } catch (FileNotFoundException e1) {
        	System.out.println("File Not Found");
        	return;
        }
        //airportGraph = new AirportGraph<String, Double>();
        s = new Scanner(System.in);
        backend = new PathFinderBackend(airportGraph, pathObject);
        frontend = new UserWindow(backend, s);
        frontend.displayLoop();
        //frontend.runCommandLoop();
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        FlightPlanner flightPlanner = new FlightPlanner();
        flightPlanner.startApp();
    }

}