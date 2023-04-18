// --== CS400 P3W3 BackendDeveloper: AEPlaceholder class ==--
// Name: <Thomas Sun>
// CSL Username: <tsun>
// Email: <twsun@wisc.edu>
// Team: CL
// TA: Abhinav Agarwal
// Lecture #: <002>
// Notes to Grader: <any optional extra notes to your grader>
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Enumeration;

public class AEPlaceholderForBED<NodeType, EdgeType extends Number> implements P3AlgorithmEngineerInterface<NodeType, EdgeType> {
	protected Hashtable<NodeType, Airport> tableOfFlights;

	public AEPlaceholderForBED() {
		tableOfFlights = new Hashtable<>();
	}

	/**
	 * Airport objects with the three-letter code name of the airport and the flights the airport has
	 */
	protected class Airport {
		public NodeType code;
		public LinkedList<FlightRoute> routes;

		public Airport(NodeType code) {
			this.code = code;
			this.routes = new LinkedList<>();
		}
	}

	/**
	 * Flight Route objects with the destination airport and the distance from the origin airport to the
	 * destination airport
	 */
	protected class FlightRoute {
		public Airport destination;
		public EdgeType distance;

		public FlightRoute(Airport target, EdgeType distance) {
			this.destination = target;
			this.distance = distance;
		}
	}

	/**
	 * Insert a new airport into the aviation network.
	 * 
	 * @param airport
	 * @return true if the airport can be inserted, false if it already exists in the network.
	 * @throws NullPointerException if argument is null
	 */
	@Override
	public boolean insertAirport(NodeType airport) {
		if (airport == null) {
			throw new NullPointerException("Error. Parameter is null.");
		}
		if (tableOfFlights.containsKey(airport)) {
			return false;
		}

		tableOfFlights.put(airport, new Airport(airport));
		return true;
	}

	/**
	 * Inserts a new directed route with a positive distance into the aviation network.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param distance         the distance/cost between departureAirport and arrivalAirport
	 * @return true if the route could be inserted or its distance updated, false if the route already
	 *         exists
	 * @throws IllegalArgumentException if either argument or both are not in the graph, or distance is
	 *                                  negative
	 * @throws NullPointerException     if either argument or both are null
	 */
	@Override
	public boolean insertRoute(NodeType departureAirport, NodeType arrivalAirport, EdgeType distance) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Error. Either or both airport arguments are null.");
		}
		Airport ofOrigin = this.tableOfFlights.get(departureAirport);
		Airport ofDestination = this.tableOfFlights.get(arrivalAirport);

		if (ofOrigin == null || ofDestination == null || distance.doubleValue() < 0) {
			throw new IllegalArgumentException(
					"Error. Attempting to add nonexistent airports or a negative distance or both.");
		}

		for (FlightRoute x : ofOrigin.routes) {
			if (x.destination == ofDestination) {
				if (x.distance.doubleValue() == distance.doubleValue()) {
					return false;
				} else {
					x.distance = distance;
				}
				return true;
			}
		}
		ofOrigin.routes.add(new FlightRoute(ofDestination, distance));
		return true;
	}

	/**
	 * Check if the aviation network contains an Airport with name *Airport*.
	 * 
	 * @param airport the airport to check for in the hash table
	 * @return true if airport is found, false otherwise
	 * @throws NullPointerException if the argument is null
	 */
	@Override
	public boolean containsAirport(NodeType airport) {
		if (airport == null) {
			throw new NullPointerException("Error. The airport does not exist.");
		}
		return tableOfFlights.containsKey(airport);
	}

	/**
	 * Check if Route is in the aviation network.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @return true if the route is in the aviation network, false if otherwise
	 * @throws NullPointerException if either arguments or both are null
	 */
	@Override
	public boolean containsRoute(NodeType departureAirport, NodeType arrivalAirport) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Error. Either airport or both do not exist.");
		}
		Airport ofOrigin = tableOfFlights.get(departureAirport);
		Airport ofDestination = tableOfFlights.get(arrivalAirport);

		if (ofOrigin == null) {
			return false;
		}
		for (FlightRoute x : ofOrigin.routes) {
			if (x.destination == ofDestination) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the distance of a route.
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @return the distance of the route (0 or larger)
	 * @throws IllegalArgumentException if either arguments or both are not in the graph
	 * @throws NullPointerException     if either arguments or both are null
	 * @throws NoSuchElementException   if route is not in the network
	 */
	@Override
	public EdgeType getDistance(NodeType departureAirport, NodeType arrivalAirport) {
		if (departureAirport == null || arrivalAirport == null) {
			throw new NullPointerException("Error. Either argument or both are null.");
		}
		Airport origin = tableOfFlights.get(departureAirport);
		Airport destination = tableOfFlights.get(arrivalAirport);
		if (origin == null || destination == null) {
			throw new IllegalArgumentException("Error. Either argument's respective directed edge or both are null.");
		}
		for (FlightRoute a : origin.routes) {
			if (a.destination == destination) {
				return a.distance;
			}
		}
		throw new NoSuchElementException("Error. No directed edge found between these nodes.");
	}

	/**
	 * Path objects store a discovered path of vertices and the overall distance of cost of the weighted
	 * directed edges along this path. Path objects can be copied and extended to include new edges and
	 * vertices using the extend constructor. In comparison to a predecessor table which is sometimes
	 * used to implement Dijkstra's algorithm, this eliminates the need for tracing paths backwards from
	 * the destination vertex to the starting vertex at the end of the algorithm.
	 */
	protected class Flight implements Comparable<Flight> {
		public Airport origin; // first vertex within path
		public double distance; // summed weight of all edges in path
		public List<NodeType> dataSequence; // ordered sequence of data from vertices in path
		public Airport destination; // last vertex within path

		/**
		 * Creates a new path containing a single vertex. Since this vertex is both the start and end of the
		 * path, it's initial distance is zero.
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
		 * This extension constructor makes a copy of the path passed into it as an argument without
		 * affecting the original path object (copyPath). The path is then extended by the Edge object
		 * extendBy. Use the doubleValue() method of extendBy's weight field to get a double representation
		 * of the edge's weight.
		 * 
		 * @param copyPath is the path that is being copied
		 * @param extendBy is the edge the copied path is extended by
		 */
		public Flight(Flight originalFlight, FlightRoute extendBy) {
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
		 * Allows the natural ordering of paths to be increasing with path distance. When path distance is
		 * equal, the string comparison of end vertex data is used to break ties.
		 * 
		 * @param other is the other path that is being compared to this one
		 * @return -1 when this path has a smaller distance than the other, +1 when this path has a larger
		 *         distance that the other, and the comparison of end vertex data in string form when these
		 *         distances are tied
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
	 * Uses Dijkstra's shortest path algorithm to find and return the shortest flight between two
	 * airports. This path contains an ordered list of the code name of each airport on this flight, and
	 * also the distance or cost of all routes that are a part of this flight.
	 * 
	 * @param origin      the airport to depart from
	 * @param destination the airport to arrive to
	 * @return finishedPaths.peek() the shortest flight from origin to destination
	 * @throws NoSuchElementException if origin airport or destination airport is not in the flights
	 *                                hash table, or the flight already contains origin
	 */
	protected Flight dijkstrasShortestPath(NodeType origin, NodeType destination) throws NoSuchElementException {
		if (!tableOfFlights.containsKey(origin)) {
			throw new NoSuchElementException("No vertex containing \"" + origin.toString() + "\" is found");
		}
		if (!tableOfFlights.containsKey(destination)) {
			throw new NoSuchElementException("No vertex containing \"" + destination.toString() + "\" is found");
		}

		Airport originating = tableOfFlights.get(origin);
		Airport terminal = tableOfFlights.get(destination);
		Airport nextVertex = originating;
		Airport currentVertex = originating;

		LinkedList<NodeType> passedVertices = new LinkedList<>();
		PriorityQueue<Flight> allPaths = new PriorityQueue<>();
		PriorityQueue<Flight> finishedPaths = new PriorityQueue<>();

		Flight completePath = new Flight(originating);
		Flight possiblePath = new Flight(originating);

		while (!originating.equals(terminal)) {
			for (int i = 0; i < currentVertex.routes.size(); i++) {
				nextVertex = currentVertex.routes.get(i).destination;
				possiblePath = new Flight(completePath, currentVertex.routes.get(i));
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
				possiblePath = allPaths.poll();
				completePath = possiblePath;
				if (completePath.destination.equals(terminal)) {
					finishedPaths.add(completePath);
					possiblePath = allPaths.poll();
					currentVertex = possiblePath.destination;
				}
				if (!completePath.destination.equals(terminal)) {
					passedVertices.clear();
					for (int i = 0; i < completePath.dataSequence.size(); i++) {
						passedVertices.add(completePath.dataSequence.get(i));
					}
					currentVertex = completePath.destination;
				}

			} catch (NullPointerException e3) {
				break;
			}
		}
		return finishedPaths.peek();
	}

	/**
	 * This method searches for all the airports immediately adjacent to the given parameter
	 * 
	 * @param airport the airport
	 * @return neighbors the neighbors
	 */
	public List<String> neighboringAirports(NodeType airport) {
		Airport originating = tableOfFlights.get(airport);
		LinkedList<String> neighbors = new LinkedList<>();

		for (int i = 0; i < originating.routes.size(); i++) {
			Flight flight = new Flight(originating.routes.get(i).destination);
			neighbors.add(flight.dataSequence.toString());
		}
		
		return neighbors;
	}

	/**
	 * This method gives a list of all the airports in the network
	 * @return theList the list of airports
	 */
	public List<String> allAirports() {
    Enumeration<NodeType> e = tableOfFlights.keys();
    LinkedList<String> theList = new LinkedList<>();

    while (e.hasMoreElements()) {
    	NodeType key = e.nextElement();
    	theList.add((String) key);
    }
    return theList;
	}
	
	/**
	 * Returns the shortest path between departureAirport and arrivalAirport. Uses Dijkstra's shortest
	 * path algorithm to find the shortest path.
	 * 
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport   the airport to arrive
	 * @return list of the shortest path between departureAirport and arrivalAirport, including both
	 *         departureAirport and arrivalAirport
	 */
	@Override
	public List<NodeType> shortestPath(NodeType departureAirport, NodeType arrivalAirport) {
		return dijkstrasShortestPath(departureAirport, arrivalAirport).dataSequence;
	}

	/**
	 * Returns the cost of the path (sum over route distance) between departureAirport and
	 * arrivalAirport. Uses Dijkstra's shortest path algorithm to find the shortest path.
	 * 
	 * @param departureAirport the airport to departure from
	 * @param arrivalAirport   the airport to arrive
	 * @return the cost of the shortest path between departureAirport and arrivalAirport, including both
	 *         departureAirport and arrivalAirport
	 */
	@Override
	public double getPathCost(NodeType departureAirport, NodeType arrivalAirport) {
		return dijkstrasShortestPath(departureAirport, arrivalAirport).distance;
	}

	/**
	 * Check if the aviation network is empty (does not contain any airports or routes).
	 * 
	 * @return true if the aviation network does not contain any airports or routes, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return tableOfFlights.size() == 0;
	}

	/**
	 * Return the number of routes in the aviation network.
	 * 
	 * @return the number of routes in the aviation network
	 */
	@Override
	public int getRouteCount() {
		int count = 0;
		for (Airport x : tableOfFlights.values()) {
			count += x.routes.size();
		}
		return count;
	}

	/**
	 * Return the number of airports in the aviation network
	 * 
	 * @return the number of airport in the aviation network
	 */
	@Override
	public int getAirportCount() {
		return tableOfFlights.size();
	}

}
