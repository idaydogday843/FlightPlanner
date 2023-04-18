// --== CS400 File Header Information ==--
// Name: John Tylka
// CSL Username: tylka
// Email: tylka2@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This interface is used for various methods required to for the frontend developer's tasks of
 * building code for the user to view and interact with.
 */
public interface FlightPlannerFrontend {

  /**
   * Starts the command loop by calling the respective methods. Terminates when the user
   * exits the application.
   */
  public void runCommandLoop();

  /**
   * Recursively will display the loop depending on the case.
   */
  public void displayLoop();

  /**
   * Initiates the program by bringing opening the main menu for the user to see.
   */
  public void displayMainMenu();

  /**
   * Prompts the user for the departure city in their route.
   */
  void setDepartureCity();


  
  /**
   * Prompts the user for the destination city in their route.
   */
  void setDestinationCity();

  /**
   * Lists all cities available to fly to and from.
   */
  void listAllCities();

  /**
   * Prompts the user for a layover city in their route.
   */
  void addLayover();

  /**
   * Calls a backend developer method to find the shortest air route
   */
  public void search();

  /**
   * Clears all user inputted data
   */
  public void clear();
}