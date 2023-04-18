// --== CS400 P3W3 BackendDeveloper: IFlightPlanner interface ==--
// Name: <Thomas Sun>
// CSL Username: <tsun>
// Email: <twsun@wisc.edu>
// Team: CL
// TA: Abhinav Agarwal
// Lecture #: <002>
// Notes to Grader: <any optional extra notes to your grader>
import java.util.LinkedList;
import java.util.List;

/**
 * This interface helps establish the backbone of the Backend Developer's Flight planner class
 * centered around Djikstra's Algorithm.
 */
public interface IFlightPlanBackend {

        /**
         * This method adds a path to the graph of nodes.
         */
        public void addPath(IPath path);

        /**
         * This method returns the shortest path given between two points <br>
         * </br>
         *
         * @param originName      the name of the airport of origin
         * @param destinationName the name of the destination airport
         * @return List<IPath> the path list of the shortest path that connects the two airports, including
         *         any midway transfer points, or null if none found.
         */
        public List<String> findShortestPath(String originName, String destinationName);
        
        /**
         * This method returns the distance of the shortest path given between two points
         * @param originName the name of the airport of origin
         * @param destinationName the name of the destination airport
         * @return the distance of the shortest path given between two points
         */
        public double findDistance(String originName, String destinationName);
        
        /**
         * This method finds all the neighboring airports of the given airport.
         *
         * @param airport the name of the airport
         * @return List<String> the list of the neighboring airports
         */
        public List<String> findAllNeighbors(String airport);

        /**
         * This method returns a list of all the airports in the region.
         *
         * @return List<String> the list of all the airports
         */
        public List<String> getAllAirports();
        
        /**
    	 * Added to this placeholder because it is missing in the backend. This sets the destination city.
    	 * 
    	 * @param destinationCity the starting city
    	 */
    	public void setDestinationCity(String destinationCity);
    	
    	/**
    	 * Added to this placeholder because it is missing in the backend. This sets the departure city.
    	 * 
    	 * @param departureCity the ending city
    	 */
    	public void setDepartureCity(String departureCity);
    	
    	/**
    	 * Added to this placeholder because it is missing in the backend. This sets the layover city.
    	 * 
    	 * @param layoverCity the starting city
    	 */
    	public void setLayoverCity(String layoverCity);

		public void clearData();

		public boolean hasLayovers();
		
		public LinkedList<String> getLayovers();

		/**
		 * This method returns the shortest path given between two points with a lay-over <br>
		 * </br>
		 *
		 * @param originName      the name of the airport of origin
		 * @param destinationName the name of the destination airport
		 * @return List<String> the path list of the shortest path that connects the two airports, including
		 *         any midway transfer points, or null if none found.
		 * @throws NoSuchElementException if the list of layover airports is empty
		 */
		List<String> findShortestPathWithLayover(String originName, String destinationName,
				LinkedList<String> layoverName);

		/**
		 * This method returns the distance of the shortest path given between two points with a lay-over
		 * 
		 * @param originName      the name of the airport of origin
		 * @param destinationName the name of the destination airport
		 * @return the distance of the shortest path given between two points
		 * @throws NoSuchElementException if the list of lay-over airports is empty
		 */
		double findDistanceWithLayover(String originName, String destinationName, LinkedList<String> layoverName);
}