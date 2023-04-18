// --== CS400 File Header Information ==--
// Name: John Tylka
// CSL Username: tylka
// Email: tylka2@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the frontend developer to be a placeholder for the backend tasks
 */
public class BackendPlaceholder implements IFlightPlanBackend {
  IFlightPlanBackend backend;

  /**
   * Creates a pathPlaceholder for the list
   */
  private class PathPlaceholder1 implements IPath {
    @Override
    public String getOrigin() {
      return "ANC";
    }

    @Override
    public String getDestination() {
      return "SEA";
    }

    @Override
    public double getDistance() {
      return 1448;
    }
  }
  private class PathPlaceholder2 implements IPath {
	    @Override
	    public String getOrigin() {
	      return "LAX";
	    }

	    @Override
	    public String getDestination() {
	      return "PBI";
	    }

	    @Override
	    public double getDistance() {
	      return 2330;
	    }
	  }
	  private class PathPlaceholder3 implements IPath {
	    @Override
	    public String getOrigin() {
	      return "SFO";
	    }

	    @Override
	    public String getDestination() {
	      return "CLT";
	    }

	    @Override
	    public double getDistance() {
	      return 2296;
	    }
	  }
	  
	  private class PathPlaceholder4 implements IPath {
		    @Override
		    public String getOrigin() {
		      return "LAX";
		    }

		    @Override
		    public String getDestination() {
		      return "MIA";
		    }

		    @Override
		    public double getDistance() {
		      return 2342;
		    }
		  }
		  private class PathPlaceholder5 implements IPath {
		    @Override
		    public String getOrigin() {
		      return "SEA";
		    }

		    @Override
		    public String getDestination() {
		      return "ANC";
		    }

		    @Override
		    public double getDistance() {
		      return 1448;
		    }
		  }

		  @Override
		  public void addPath(IPath path) {
		  }
		  @Override
		  public List<String> findShortestPath(String originName, String destinationName) {
		    ArrayList<String> list = new ArrayList<>();
		    IPath path1 = new PathPlaceholder1();
		    IPath path2 = new PathPlaceholder2();
		    IPath path3 = new PathPlaceholder3();
		    IPath path4 = new PathPlaceholder4();
		    IPath path5 = new PathPlaceholder5();
		    list.add(path1);
		    list.add(path2);
		    list.add(path3);
		    list.add(path4);
		    list.add(path5);
		    return list;
		  }

		  @Override
		  public List<String> findAllNeighbors(String airport) {
		    ArrayList<String> list = new ArrayList<>();
		    list.add("SEA");
		    list.add("ANC");
		    list.add("LAX");
		    list.add("PBI");
		    list.add("SFO");
		    return list;
		  }
		  
		  @Override
		  public List<String> getAllAirports() {
		    ArrayList<String> list = new ArrayList<>();
		    list.add("SEA");
		    list.add("ANC");
		    list.add("LAX");
		    list.add("PBI");
		    list.add("SFO");
		    return list;
		  }
		  
		  /**
		   * Added to this placeholder because it is missing in the backend.
		   * This sets the destination city.
		   * @param destinationCity the starting city
		   */
		  public void setDestinationCity(String destinationCity){
		    //set destination city
		  }

		  /**
		   * Added to this placeholder because it is missing in the backend.
		   * This sets the departure city.
		   * @param departureCity the ending city
		   */
		  public void setDepartureCity(String departureCity){
		    //set departure city
		  }

		  /**
		   * Added to this placeholder because it is missing in the backend.
		   * This sets the layover city.
		   * @param layoverCity the starting city
		   */
		  public void setLayoverCity(String layoverCity){
		    //set layover city
		  }
		@Override
		public double findDistance(String originName, String destinationName) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void clearData() {
			// TODO Auto-generated method stub
			
		}
		}