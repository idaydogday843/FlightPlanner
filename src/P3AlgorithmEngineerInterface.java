// --== CS400 Fall 2022 File Header Information ==--
// Name: Yuchen Wang
// Email: wang2673@wisc.edu
// Team: CL
// TA: ABHINAV AGARWAL
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This interface aims to mimic the aviation network.
 * It represents a directed graph data structure with positive edge weights. 
 * Each node/vertex is an airport.
 * Each edge is a route from one airport to another airport.
 * Edge weight is the distance between two airports.
 *
 * @param NodeType the data type stored at each graph vertex
 * @param EdgeType the data type stored at each graph edge as a Number whose doubleValue() method returns a value >=0.0
 */
public interface P3AlgorithmEngineerInterface<NodeType,EdgeType extends Number> {

    /**
     * Insert a new airport into the aviation network.
     * 
     * @param airport
     * @return true if the airport can be inserted as a new airport
     *             false if it is already in the aviation network
     * @throws NullPointerException if airport is null
     */
    public boolean insertAirport(NodeType airport);
    
    /**
     * Insert a new directed route with a positive distance into the aviation network.
     * 
     * @param departureAirport
     * @param arrivalAirport
     * @param distance between departureAirport and arrivalAirport
     * @return true if the Route could be inserted or its distance updated, 
     *             false if the Route with the same distance, departureAirport, arrivalAirport was already in the aviation network
     * @throws IllegalArgumentException if either departureAirport or arrivalAirport or both are not in the graph, or distance is < 0
     * @throws NullPointerException if either departureAirport or arrivalAirport or both are null
     */
    public boolean insertRoute(NodeType departureAirport, NodeType arrivalAirport, EdgeType distance);



    /**
     * Check if the aviation network contains an Airport with name *Airport*.
     * 
     * @param Airport, the Airport to check check for
     * @return true if Airport is stored in the aviation network, false otherwise
     * @throws NullPointerException if *Airport* is null
     */
    public boolean containsAirport(NodeType Airport);
    
    /**
     * Check if Route is in the aviation network.
     * 
     * @param departureAirport
     * @param arrivalAirport
     * @return true if the route is in the aviation network, 
     *             false if it is not in the aviation network
     * @throws NullPointerException if either departureAirport or arrivalAirport or both are null
     */
    public boolean containsRoute(NodeType departureAirport, NodeType arrivalAirport);

    /**
     * Return the distance of a route.
     * 
     * @param departureAirport
     * @param arrivalAirport
     * @return the distance of the route (0 or positive integer)
     * @throws IllegalArgumentException if either departureAirport or arrivalAirport or both are not in the graph
     * @throws NullPointerException if either departureAirport or arrivalAirport or both are null
     * @throws NoSuchElementException if route is not in the aviation network
     */
    public EdgeType getDistance(NodeType departureAirport, NodeType arrivalAirport);
    
    /**
     * Returns the shortest path between departureAirport and arrivalAirport.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param departureAirport
     * @param arrivalAirport
     * @return list of the shortest path between departureAirport and arrivalAirport, including both departureAirport and arrivalAirport
     */
    public List<NodeType> shortestPath(NodeType departureAirport, NodeType arrivalAirport);

    /**
     * Returns the cost of the path (sum over route distance) between departureAirport and arrivalAirport.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param departureAirport
     * @param arrivalAirport
     * @return the cost of the shortest path between departureAirport and arrivalAirport, including both departureAirport and arrivalAirport
     */
    public double getPathCost(NodeType departureAirport, NodeType arrivalAirport);

    /**
     * Check if the aviation network is empty (does not contain any airports or routes).
     * 
     * @return true if the aviation network does not contain any airports or routes, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Return the number of routes in the aviation network.
     * 
     * @return the number of routes in the aviation network
     */
    public int getRouteCount();

    /**
     * Return the number of airports in the aviation network
     * 
     * @return the number of airport in the aviation network
     */
    public int getAirportCount();

}