// --== CS400 File Header Information ==--
// Name: John Tylka
// CSL Username: tylka
// Email: tylka2@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class creates the intractable and viewable applications for the user
 */
public class UserWindow implements FlightPlannerFrontend {
  protected IFlightPlanBackend backendObject;
  Scanner scanner;
  String input = null;
  private String departureCity;
  private String destinationCity;

  public UserWindow(IFlightPlanBackend backendObject, Scanner scanner) {
    this.scanner = scanner;
    this.backendObject = backendObject;

  }
  
  public UserWindow(PathFinderBackend backendObject, Scanner scanner) {
	    this.scanner = scanner;
	    this.backendObject = backendObject;

	  }

  @Override
  public void runCommandLoop() {
    System.out.println("Welcome to Flight Planner"); //prints welcome to user
    System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+");
    displayMainMenu();
  }
  @Override
  public void displayLoop() {
    displayMainMenu();
    input = scanner.nextLine(); //takes user's input
      if (input.equals("1")) {
        listAllCities();
        displayLoop();
      } else if (input.equals("2")) {
        setDepartureCity();
        displayLoop();
      } else if (input.equals("3")) {
        setDestinationCity();
        displayLoop();
      } else if (input.equals("4")) {
        addLayover();
        displayLoop();
      } else if (input.equals("5")) {
        clear();
        displayLoop();
      } else if (input.equals("6")) {
    	search();
    	displayLoop();
      } else if (input.equals("7")) {
        System.out.println("Exit Flight Planner"); //prints to the user if case is true
      } else {
        System.out.println("Please choose between 1-7"); //prints to the user if case is true
        displayLoop();
      }
    }

  @Override
  public void displayMainMenu() {
    System.out.println("Please select one of the following choices by typing in its "
            + "corresponding number to proceed:"); //prints all below in the method to the user

    System.out.println("1) List all cities");
    System.out.println("2) Choose departure city");
    System.out.println("3) Choose destination city");
    System.out.println("4) Choose layover city");
    System.out.println("5) Clear data");
    System.out.println("6) Get shortest path");
    System.out.println("7) Exit Flight Planner");
  }
  @Override
  public void setDepartureCity() {
    System.out.println("Enter city:"); //prints to the user
    departureCity = scanner.nextLine(); //takes user's input
    backendObject.setDepartureCity(departureCity); //this method needs to be within the backend
    System.out.println("Departure city os " + departureCity);
  }

  @Override
  public void setDestinationCity() {
    System.out.println("Enter city:"); //prints to the user
    destinationCity = scanner.nextLine(); //takes user's input
    backendObject.setDestinationCity(destinationCity); //this method needs to be within the backend
    System.out.println("Destination city as:" + destinationCity);
  }

  @Override
  public void listAllCities() {
    int count = 1;
    for (int i = 0; i < backendObject.getAllAirports().size(); i++) {
      System.out.println(count + " " + backendObject.getAllAirports().get(i));
      count++;
    }
  }
  @Override
  public void addLayover() {
    System.out.println("Add a layover city:"); //prints to the user
    String layoverCity = scanner.nextLine(); //takes user's input
    backendObject.setLayoverCity(layoverCity); //this method needs to be within the backend
    System.out.println("Layover city added: " + layoverCity);
  }

  @Override
  public void search() {
	  if (!backendObject.hasLayovers()) {
		  System.out.println("The shortest path for your desired route is:"); //prints to the user
		    List<String> flight = backendObject.findShortestPath(this.departureCity, this.destinationCity);
		    for (int i = 0; i < flight.size(); i++) {
		      System.out.println(flight.get(i));
		    }
		    Double total = backendObject.findDistance(departureCity, destinationCity);;
		    System.out.println("\n" + "Total travel distance is " + total);
	  }
	  else if (backendObject.hasLayovers()) {
		  LinkedList<String> layovers = backendObject.getLayovers();
		  System.out.println("The shortest path for your desired route is:"); //prints to the user
		    List<String> flight = backendObject.findShortestPathWithLayover(departureCity, destinationCity, layovers);
		    for (int i = 0; i < flight.size(); i++) {
		      System.out.println(flight.get(i));
		    }
		    Double total = backendObject.findDistanceWithLayover(departureCity, destinationCity, layovers);
		    System.out.println("\n" + "Total travel distance is " + total);
	  }
	  }

  @Override
  public void clear() {
      backendObject.clearData(); //this method needs to be within the backend
  }
}