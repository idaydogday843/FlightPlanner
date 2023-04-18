// --== CS400 Fall 2022 File Header Information ==--
// Name: Yuchen Wang
// Email: wang2673@wisc.edu
// Team: CL
// TA: ABHINAV AGARWAL
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class AirportGraph<NodeType, EdgeType extends Number>
		implements P3AlgorithmEngineerInterface<NodeType, EdgeType> {

	/**
	 * Airport objects with the 3-letter codename of the airport and the 
	 * flights the airport has
	 */
	protected class Airport {
		public NodeType code;
		public LinkedList<Route> flightLine;

		public Airport(NodeType code) {
			this.code = code;
			this.flightLine = new LinkedList<>();
		}
	}

	/**
	 * Flight Route objects with the destination airport and the distance from
	 * the origin airport to the destination airport
	 */
	protected class Route {
		public Airport destination;
		public EdgeType distance;

		public Route(Airport target, EdgeType distance) {
			this.destination = target;
			this.distance = distance;
		}
	}

	protected Hashtable<NodeType, Airport> flights;

	public AirportGraph() {
		flights = new Hashtable<>();
	}

	/**
	 * Insert a new airport into the aviation network.
	 * 
	 * @param airport
	 * @return true if the airport can be inserted as a new airport false if it is
	 *         already in the aviation network
	 * @throws NullPointerException if airport is null
	 */
	@Override
	public boolean insertAirport(NodeType airport) {
		if (airport == null) {
			throw new NullPointerException("Cannot add null vertex");
		}
		if (flights.containsKey(airport)) {
			return false;
		}
		flights.put(airport, new Airport(airport));
		return true;
	}

	/**
	 * Insert a new directed route with a positive distance into the aviation
	 * network.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param distance         between departureAirport and arrivalAirport
	 * @return true if the Route could be inserted or its distance updated, false if
	 *         the Route with the same distance, departureAirport, arrivalAirport
	 *         was already in the aviation network
	 * @throws IllegalArgumentException if either departureAirport or arrivalAirport
	 *                                  or both are not in the graph, or distance is
	 *                                  < 0
	 * @throws NullPointerException     if either departureAirport or arrivalAirport
	 *                                  or both are null
	 */
	@Override
	public boolean insertRoute(NodeType departureAirport, NodeType arrivalAirport, EdgeType distance) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Cannot add edge with null source or target");
		}
		Airport origin = this.flights.get(departureAirport);
		Airport destination = this.flights.get(arrivalAirport);
		if (origin == null || destination == null) {
			throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
		}
		if (distance.doubleValue() < 0) {
			throw new IllegalArgumentException("Cannot add edge with negative weight");

		}
		for (Route a : origin.flightLine) {
			if (a.destination == destination) {
				if (a.distance.doubleValue() == distance.doubleValue()) {
					return false;
				} else {
					a.distance = distance;
				}
				return true;
			}
		}
		origin.flightLine.add(new Route(destination, distance));
		return true;
	}

	/**
	 * Check if the aviation network contains an Airport with name *Airport*.
	 * 
	 * @param Airport, the Airport to check check for
	 * @return true if Airport is stored in the aviation network, false otherwise
	 * @throws NullPointerException if *Airport* is null
	 */
	@Override
	public boolean containsAirport(NodeType Airport) {
		if (Airport == null) {
			throw new NullPointerException("Cannot contain null data vertex");
		}
		return flights.containsKey(Airport);
	}

	/**
	 * Check if Route is in the aviation network.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @return true if the route is in the aviation network, false if it is not in
	 *         the aviation network
	 * @throws NullPointerException if either departureAirport or arrivalAirport or
	 *                              both are null
	 */
	@Override
	public boolean containsRoute(NodeType departureAirport, NodeType arrivalAirport) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Cannot contain edge adjacent to null data");
		}
		Airport origin = flights.get(departureAirport);
		Airport destination = flights.get(arrivalAirport);
		if (origin == null) {
			return false;
		}
		for (Route a : origin.flightLine) {
			if (a.destination == destination) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the distance of a route.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @return the distance of the route (0 or positive integer)
	 * @throws IllegalArgumentException if either departureAirport or arrivalAirport
	 *                                  or both are not in the graph
	 * @throws NullPointerException     if either departureAirport or arrivalAirport
	 *                                  or both are null
	 * @throws NoSuchElementException   if route is not in the aviation network
	 */
	@Override
	public EdgeType getDistance(NodeType departureAirport, NodeType arrivalAirport) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
		}
		Airport origin = flights.get(departureAirport);
		Airport destination = flights.get(arrivalAirport);
		if (origin == null || destination == null) {
			throw new IllegalArgumentException("Cannot retrieve weight of edge between vertices that do not exist");
		}
		for (Route a : origin.flightLine) {
			if (a.destination == destination) {
				return a.distance;
			}
		}
		throw new NoSuchElementException("No directed edge found between these vertices");
	}

	/**
     * Return the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for(Airport a : flights.values())
            edgeCount += a.flightLine.size();
        return edgeCount;
    }

    /**
     * Return the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    public int getVertexCount() {
        return flights.size();
    }
    
	/**
	 * Path objects store a discovered path of vertices and the overal distance of
	 * cost of the weighted directed edges along this path. Path objects can be
	 * copied and extended to include new edges and verticies using the extend
	 * constructor. In comparison to a predecessor table which is sometimes used to
	 * implement Dijkstra's algorithm, this eliminates the need for tracing paths
	 * backwards from the destination vertex to the starting vertex at the end of
	 * the algorithm.
	 */
	protected class Flight implements Comparable<Flight> {
		public Airport origin; // first vertex within path
		public double distance; // sumed weight of all edges in path
		public List<NodeType> dataSequence; // ordered sequence of data from vertices in path
		public Airport destination; // last vertex within path

		/**
		 * Creates a new path containing a single vertex. Since this vertex is both the
		 * start and end of the path, it's initial distance is zero.
		 * 
		 * @param start is the first vertex on this path
		 */
		public Flight(Airport origin) {
			this.origin = origin;
			this.distance = 0.0D;
			this.dataSequence = new LinkedList<>();
			this.dataSequence.add(origin.code);
			this.destination = origin;
		}

		/**
		 * This extension constructor makes a copy of the path passed into it as an
		 * argument without affecting the original path object (copyPath). The path is
		 * then extended by the Edge object extendBy. Use the doubleValue() method of
		 * extendBy's weight field to get a double representation of the edge's weight.
		 * 
		 * @param copyPath is the path that is being copied
		 * @param extendBy is the edge the copied path is extended by
		 */
		public Flight(Flight originalFlight, Route extendBy) {
			this.origin = originalFlight.origin;
			this.distance = originalFlight.distance + extendBy.distance.doubleValue();
			this.dataSequence = new LinkedList<>();

			for (int i = 0; i < originalFlight.dataSequence.size(); i++) {
				this.dataSequence.add(originalFlight.dataSequence.get(i));
			}
			this.dataSequence.add(extendBy.destination.code);
			this.destination = extendBy.destination;
		}
		
		/**
		 * Allows the natural ordering of paths to be increasing with path distance.
		 * When path distance is equal, the string comparison of end vertex data is used
		 * to break ties.
		 * 
		 * @param other is the other path that is being compared to this one
		 * @return -1 when this path has a smaller distance than the other, +1 when this
		 *         path has a larger distance that the other, and the comparison of end
		 *         vertex data in string form when these distances are tied
		 */
		public int compareTo(Flight other) {
			int cmp = Double.compare(this.distance, other.distance);
			if (cmp != 0)
				return cmp; // use path distance as the natural ordering
			// when path distances are equal, break ties by comparing the string
			// representation of data in the end vertex of each path
			return this.destination.code.toString().compareTo(other.destination.code.toString());
		}
		
	}
	
	/**
	 * Uses Dijkstra's shortest path algorithm to find and return the shortest flight
	 * between two airports. This path contains an
	 * ordered list of the codename of each airport on this flight, and also the distance
	 * or cost of all routes that are a part of this flight.
	 * 
	 * @param origin the airport to departure from
	 * @param destination the airport to arrive
	 * @return the shortest flight from origin to destination
	 * @throws NoSuchElementException if origin airport or destination airport is not in the flights hashtable, or 
	 * 								  the flight already contains origin
	 */
	protected Flight dijkstrasShortestPath(NodeType origin, NodeType destination) throws NoSuchElementException{
		if (!flights.containsKey(origin)) {
			throw new NoSuchElementException("No vertex containing \"" + origin.toString() + "\" is found");
		}
		if (!flights.containsKey(destination)) {
			throw new NoSuchElementException("No vertex containing \"" + destination.toString() + "\" is found");
		}

		Airport startVertex = flights.get(origin);
		LinkedList<NodeType> passedVertices = new LinkedList<NodeType>();
		PriorityQueue<Flight> allPaths = new PriorityQueue<Flight>();
		PriorityQueue<Flight> finishedPaths = new PriorityQueue<Flight>();

		Airport endVertex = flights.get(destination);
		Flight completePath = new Flight(startVertex);
		Flight possiblePath = new Flight(startVertex);
		Flight finalPath = null;

		Airport nextVertex = startVertex;
		Airport currentVertex = startVertex;

		while (!startVertex.equals(endVertex)) {
			for (int i = 0; i < currentVertex.flightLine.size(); i++) {
				if (currentVertex.flightLine.get(i).destination.code.equals(destination)) {
					finalPath = new Flight(completePath, currentVertex.flightLine.get(i));
					finishedPaths.add(finalPath);
					//break;
				}
				nextVertex = currentVertex.flightLine.get(i).destination;
				possiblePath = new Flight(completePath, currentVertex.flightLine.get(i));
				try {
					if (!passedVertices.contains(nextVertex.code)) {
						allPaths.add(possiblePath);
					}
				} catch (NullPointerException e2) {
					allPaths.add(possiblePath);
				}
			}

			try {
				if (allPaths.isEmpty() && finishedPaths.isEmpty()) {
					throw new NoSuchElementException(
							"No path between \"" + origin.toString() + "\" to \"" + destination.toString() + "\" is found");
				}
//				if () {
//					
//				}
				
				possiblePath = allPaths.poll();
				completePath = possiblePath;
				if (completePath.destination.equals(endVertex)) {
					finishedPaths.add(completePath);
					possiblePath = allPaths.poll();
					currentVertex = possiblePath.destination;
				}
				if (!completePath.destination.equals(endVertex)) {
					passedVertices.clear();
					for (int i = 0; i < completePath.dataSequence.size(); i++) {
						passedVertices.add(completePath.dataSequence.get(i));
					}
					currentVertex = completePath.destination;
				}
				if (finishedPaths.size() > 20) {
					break;
				}
			} catch (NullPointerException e3) {
				break;
			}
		}

		return finishedPaths.peek();
	}
	
	/**
	 * Returns the shortest path between departureAirport and arrivalAirport with multiple
	 * stop points in the way, which are labeled as midway. Uses
	 * Dijkstra's shortest path algorithm to find the shortest path.
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport the airport to arrive
	 * @param midways the array of airports to stop by
	 * @return list of the shortest path between departureAirport and
	 *         arrivalAirport, including departureAirport, arrivalAirport, and
	 *         all the midway airports
	 */
	public List<NodeType> shortestPathWithMidway(NodeType departureAirport, NodeType arrivalAirport, NodeType[] midways) {
		Airport origin = new Airport(departureAirport);
		Flight midwayFlight = new Flight(origin);
		List<NodeType> flightOrder = new LinkedList<NodeType>();
		for (int i = 0; i < midways.length; i++) {
			if (i == 0) {
				midwayFlight = dijkstrasShortestPath(departureAirport, midways[i]);
				flightOrder = midwayFlight.dataSequence;
			}
			else {
				midwayFlight = dijkstrasShortestPath(midways[i - 1], midways[i]);
				for (int j = 0; j < midwayFlight.dataSequence.size(); j++) {
					if (!flightOrder.contains(midwayFlight.dataSequence.get(j))) {
						flightOrder.add(midwayFlight.dataSequence.get(j));
					}
				}
			}
		}
		midwayFlight = dijkstrasShortestPath(midways[midways.length - 1], arrivalAirport);
		for (int j = 0; j < midwayFlight.dataSequence.size(); j++) {
			if (!flightOrder.contains(midwayFlight.dataSequence.get(j))) {
				flightOrder.add(midwayFlight.dataSequence.get(j));
			}
		}
		return flightOrder;
	}
	
	/**
	 * Returns the shortest path between departureAirport and arrivalAirport. Uses
	 * Dijkstra's shortest path algorithm to find the shortest path.
	 * 
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport the airport to arrive
	 * @return list of the shortest path between departureAirport and
	 *         arrivalAirport, including both departureAirport and arrivalAirport
	 */
	@Override
	public List<NodeType> shortestPath(NodeType departureAirport, NodeType arrivalAirport) {
		return dijkstrasShortestPath(departureAirport, arrivalAirport).dataSequence;
	}

	/**
	 * Returns the cost of the path (sum over route distance) between
	 * departureAirport and arrivalAirport with multiple
	 * stop points in the way, which are labeled as midway. Uses 
	 * Dijkstra's shortest path algorithm to find the shortest path.
	 * 
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport the airport to arrive
	 * @param midways the array of airports to stop by
	 * @return the cost of the shortest path between departureAirport and
	 *         arrivalAirport, including both departureAirport and arrivalAirport and
	 *         all the midway airports
	 */
	public double getPathCostWithMidway(NodeType departureAirport, NodeType arrivalAirport, NodeType[] midways) {
		//Airport origin = new Airport(departureAirport);
		double totalDistance = 0.0;
		for (int i = 0; i < midways.length; i++) {
			if (i == 0) {
				totalDistance += getPathCost(departureAirport, midways[i]);
			}
			else {
				totalDistance += getPathCost(midways[i - 1], midways[i]);
			}
		}
		totalDistance += getPathCost(midways[midways.length - 1], arrivalAirport);
		return totalDistance;
	}
	/**
	 * Returns the cost of the path (sum over route distance) between
	 * departureAirport and arrivalAirport. Uses Dijkstra's shortest path algorithm
	 * to find the shortest path.
	 * 
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport the airport to arrive
	 * @return the cost of the shortest path between departureAirport and
	 *         arrivalAirport, including both departureAirport and arrivalAirport
	 */
	@Override
	public double getPathCost(NodeType departureAirport, NodeType arrivalAirport) {
		return dijkstrasShortestPath(departureAirport, arrivalAirport).distance;
	}

	/**
	 * Check if the aviation network is empty (does not contain any airports or
	 * routes).
	 * 
	 * @return true if the aviation network does not contain any airports or routes,
	 *         false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return flights.size() == 0;
	}

	/**
	 * Return the number of routes in the aviation network.
	 * 
	 * @return the number of routes in the aviation network
	 */
	@Override
	public int getRouteCount() {
		int routeCount = 0;
		for (Airport a : flights.values()) {
			routeCount += a.flightLine.size();
		}
		return routeCount;
	}

	/**
	 * Return the number of airports in the aviation network
	 * 
	 * @return the number of airport in the aviation network
	 */
	@Override
	public int getAirportCount() {
		return flights.size();
	}
	
	/**
	 * This method gives a list of all the airports in the network
	 * @return theList the list of airports
	 */
	public List<String> allAirports() {
    Enumeration<NodeType> e = flights.keys();
    LinkedList<String> theList = new LinkedList<>();

    while (e.hasMoreElements()) {
    	NodeType key = e.nextElement();
    	theList.add((String) key);
    }
    return theList;
	}

	/**
	 * This method searches for all the airports immediately adjacent to the given parameter
	 * 
	 * @param airport the airport
	 * @return neighbors the neighbors
	 */
	public List<String> neighboringAirports(NodeType airport) {
		Airport originating = flights.get(airport);
		LinkedList<String> neighbors = new LinkedList<>();

		for (int i = 0; i < originating.flightLine.size(); i++) {
			Flight flight = new Flight(originating.flightLine.get(i).destination);
			neighbors.add(flight.dataSequence.toString());
		}
		
		return neighbors;
	}
}
