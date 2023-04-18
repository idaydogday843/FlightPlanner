// --== CS400 P3W3 BackendDeveloper Tests Official ==--
// Name: <Thomas Sun>
// CSL Username: <tsun>
// Email: <twsun@wisc.edu>
// Team: CL
// TA: Abhinav Agarwal
// Lecture #: <002>
// Notes to Grader: <any optional extra notes to your grader>
import java.util.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PathFinderBackend implements IFlightPlanBackend {
	private ArrayList<IPath> listOfPaths;
	private P3AlgorithmEngineerInterface<String, Double> graph;
	private IPath pathBetweenTwo;
	private Filter r;

	
	/**
	 * The FlightPlanBackend constructor will help implement the backend code taking in the algorithm
	 * engineer an data wrangler's code.
	 */
	public PathFinderBackend(P3AlgorithmEngineerInterface<String, Double> AEObject, IPath DWObject) {
		this.graph = AEObject;
		this.pathBetweenTwo = DWObject;
		this.r = new Filter();
	}

	/**
	 * This method adds a path to the graph of nodes.
	 */
	@Override
	public void addPath(IPath path) {
		String origin = path.getOrigin();
		String destination = path.getDestination();
		double distance = path.getDistance();
		
		graph.insertRoute(origin, destination, distance);
	}

	/**
	 * This method returns the shortest path given between two points <br>
	 * </br>
	 *
	 * @param originName      the name of the airport of origin
	 * @param destinationName the name of the destination airport
	 * @return List<String> the path list of the shortest path that connects the two airports, including
	 *         any midway transfer points, or null if none found.
	 */
	public List<String> findShortestPath(String originName, String destinationName) {
		return graph.shortestPath(originName, destinationName);
	}

	/**
	 * This method returns the distance of the shortest path given between two points
	 * 
	 * @param originName      the name of the airport of origin
	 * @param destinationName the name of the destination airport
	 * @return the distance of the shortest path given between two points
	 */
	public double findDistance(String originName, String destinationName) {
		return graph.getPathCost(originName, destinationName);
	}

	/**
	 * This method finds all the neighboring airports of the given airport.
	 *
	 * @param airport the name of the airport
	 * @return List<String> the list of the neighboring airports
	 */
	public List<String> findAllNeighbors(String airport) {
		return ((AirportGraph<String, Double>) graph).neighboringAirports(airport);
	}

	/**
	 * This method returns a list of all the airports in the region.
	 *
	 * @return List<String> the list of all the airports
	 */
	public List<String> getAllAirports() {
		return ((AirportGraph<String, Double>) graph).allAirports();
	}

	/**
	 * Airport objects with the 3-letter codename of the airport and the flights the airport has
	 */
	protected class Airport {
		public String code;
//		public LinkedList<Route> flightLine;

		public Airport(String code) {
			this.code = code;
//			this.flightLine = new LinkedList<>();
		}
	}

	protected class Filter {
		private Airport origin;
		private Airport destination;
		private LinkedList<Airport> layovers;

		public Filter() {
			layovers = new LinkedList<Airport>();
		}		

		
	}
	
	@Override
	public boolean hasLayovers() {
		return !this.r.layovers.isEmpty();
	}
	
	/**
	 * Added to this placeholder because it is missing in the backend. This sets the destination city.
	 * 
	 * @param destinationCity the starting city
	 */
	public void setDestinationCity(String destinationCity) {		
		r.destination = new Airport(destinationCity);
		// set destination city
	}

	/**
	 * Added to this placeholder because it is missing in the backend. This sets the departure city.
	 * 
	 * @param departureCity the ending city
	 */
	public void setDepartureCity(String departureCity) {
		r.origin = new Airport(departureCity);
		// set departure city
	}

	/**
	 * Added to this placeholder because it is missing in the backend. This sets the layover city.
	 * 
	 * @param layoverCity the starting city
	 */
	public void setLayoverCity(String layoverCity) {
		Airport layover = new Airport(layoverCity);
		r.layovers.add(layover);
		// set layover city
	}
	
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
	@Override
	public List<String> findShortestPathWithLayover(String originName, String destinationName, LinkedList<String> layoverName) {
		if (layoverName.isEmpty()) {
			throw new NoSuchElementException("Error. The list of layover airports is empty.");
		}
		Airport origin = new Airport(originName);
		List<String> temporary = new LinkedList<>();
		List<String> flightList = new LinkedList<>();
		
		for (int i = 0; i < layoverName.size(); i++) {
			if (i == 0) {
				temporary = findShortestPath(originName, layoverName.get(i));
				flightList = findShortestPath(originName, layoverName.get(i));
			}
			else {
				temporary = findShortestPath(layoverName.get(i - 1), layoverName.get(i));
				for (int j = 0; j < temporary.size(); j++) {
					if (!flightList.contains(temporary.get(j))) {
						flightList.add(temporary.get(j));
					}
				}
			}
		}
		temporary = findShortestPath(layoverName.get(layoverName.size() - 1), destinationName);
		for (int j = 0; j < temporary.size(); j++) {
			if (!flightList.contains(temporary.get(j))) {
				flightList.add(temporary.get(j));
			}
		}
		return flightList;
	}

	/**
	 * This method returns the distance of the shortest path given between two points with a lay-over
	 * 
	 * @param originName      the name of the airport of origin
	 * @param destinationName the name of the destination airport
	 * @return the distance of the shortest path given between two points
	 * @throws NoSuchElementException if the list of lay-over airports is empty
	 */
	@Override
	public double findDistanceWithLayover(String originName, String destinationName, LinkedList<String> layoverName) {
		if (layoverName.isEmpty()) {
			throw new NoSuchElementException("Error. The list of layover airports is empty.");
		}
		double totalDistance = 0.0;	
		
		for (int i = 0; i < layoverName.size(); i++) {
			if (i == 0) {
				totalDistance += findDistance(originName, layoverName.get(i));
			} else {
				totalDistance += findDistance(layoverName.get(i-1), layoverName.get(i));
			}
		}	
		
		totalDistance += findDistance(layoverName.get(layoverName.size() - 1), destinationName);
		return totalDistance;
	}
	
	@Override
	public void clearData() { 
		this.r = new Filter();
	}

	@Override
	public LinkedList<String> getLayovers() {
		if (!hasLayovers()) {
			throw new NoSuchElementException("No Layovers");
		}
		LinkedList<String> layovers = new LinkedList<String>();
		for (int i = 0; i < this.r.layovers.size(); i++) {
			layovers.add(this.r.layovers.get(i).code);
		}
		return layovers;
	}
}
