// --== CS400 File Header Information ==--
// Name: John Tylka
// CSL Username: tylka
// Email: tylka2@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * This is a class to test the UserWindow.java
 */
public class FrontendDeveloperTests {
  private FlightPlannerFrontend frontendObject;
  private IFlightPlanBackend backendObject;
  private Scanner scanner;

  /**
   * This tests the displayMainMenu method.
   */
  @Test
  public void test1() {
    TextUITester test = new TextUITester("");
    backendObject = new BackendPlaceholder();
    scanner = new Scanner(System.in);
    frontendObject = new UserWindow((BackendPlaceholder) backendObject, scanner);
    frontendObject.displayMainMenu();
    String output = test.checkOutput();
    assertEquals("Please select one of the following choices by typing in its" +
            " corresponding number to proceed:\n1) List all cities\n2) Choose departure city\n" +
            "3) Choose destination city\n4) Choose layover city\n5) Clear data\n" +
            "6) Exit Flight Planner\n", output);
  }
  
  /**
   * This tests the listAllCities method.
   */
  @Test
  public void test2() {
    TextUITester test = new TextUITester("");
    scanner = new Scanner(System.in);
    backendObject = new BackendPlaceholder();
    frontendObject = new UserWindow((BackendPlaceholder) backendObject, scanner);
    frontendObject.listAllCities();
    String output = test.checkOutput();
    assertEquals("1 SEA\n2 ANC\n3 LAX\n4 PBI\n5 SFO\n", output);
  }

  /**
   * This tests the search method.
   */
  @Test
  public void test3() {
    TextUITester test = new TextUITester("");
    scanner = new Scanner(System.in);
    backendObject = new BackendPlaceholder();
    frontendObject = new UserWindow((BackendPlaceholder) backendObject, scanner);
    frontendObject.search();
    String output = test.checkOutput();
    assertEquals("""
                    The shortest path for your desired route is:
                    SEA
                    PBI
                    CLT
                    MIA
                    ANC

                    Total travel distance is 9864
                    """
            , output);
  }
  /**
   * This tests the runCommandLoop method.
   */
  @Test
  public void test4() {
    TextUITester test = new TextUITester("");
    scanner = new Scanner(System.in);
    backendObject = new BackendPlaceholder();
    frontendObject = new UserWindow((BackendPlaceholder) backendObject, scanner);
    frontendObject.runCommandLoop();
    String output = test.checkOutput();
    assertEquals("Welcome to Flight Planner\n" +
            "x+x+x+x+x+x+x+x+x+x+x+x+x+x+\n" +
            "Please select one of the following choices by typing in its corresponding number to proceed:\n" +
            "1) List all cities\n" +
            "2) Choose departure city\n" +
            "3) Choose destination city\n" +
            "4) Choose layover city\n" +
            "5) Clear data\n" +
            "6) Exit Flight Planner\n", output);
  }

  /**
   * This tests the clear method.
   */
  @Test
  public void test5() {
    TextUITester test = new TextUITester("");
    scanner = new Scanner(System.in);
    backendObject = new BackendPlaceholder();
    frontendObject = new UserWindow((BackendPlaceholder) backendObject, scanner);
    frontendObject.clear();
    String output = test.checkOutput();
    assertEquals("", output);
  }
}