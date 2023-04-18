// --== CS400 File Header Information ==--
// Name: Yuchen Wang
// Email: wang2673@wisc.edu
// Team: CL
// TA: ABHINAV AGARWAL
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class AlgorithmEngineerTests {

    private AirportGraph<String,Integer> graph;
    
    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new AirportGraph<>();
        // insert vertices A-F
        graph.insertAirport("ORD");
        graph.insertAirport("BOS");
        graph.insertAirport("JFK");
        graph.insertAirport("SFO");
        graph.insertAirport("LAX");
        graph.insertAirport("MIA");
        // insert edges
        graph.insertRoute("ORD","BOS",6);
        graph.insertRoute("ORD","JFK",2);
        graph.insertRoute("ORD","SFO",5);
        graph.insertRoute("BOS","LAX",1);
        graph.insertRoute("BOS","JFK",2);
        graph.insertRoute("JFK","BOS",3);
        graph.insertRoute("JFK","MIA",1);
        graph.insertRoute("SFO","LAX",3);
        graph.insertRoute("LAX","ORD",4);
        graph.insertRoute("MIA","ORD",1);
        graph.insertRoute("MIA","SFO",1);
    }

    /**
     * Checks the distance from MIA to LAX
     */
    @Test
    public void testPathCostORDtoLAX() {
        assertTrue(graph.getPathCost("MIA", "LAX") == 4);
    }

    /**
     * Checks the ordered sequence of airport code from  
     * MIA to LAX.
     */
    @Test
    public void testPathMIAtoLAX() {
        assertTrue(graph.shortestPath("MIA", "LAX").toString().equals(
            "[MIA, SFO, LAX]"
        ));
    }
    
    /**
     * Checks the distance from MIA to LAX with the stop point BOS
     */
    @Test
    public void testPathCostMIAtoLAXStopBOS() {
    	String[] midways = {"BOS"};
    	assertTrue(graph.getPathCostWithMidway("MIA", "LAX", midways) == 7);
    }

    /**
     * Checks the ordered sequence of airport code from
     * MIA to LAX with the stop point BOS
     */
    @Test
    public void testPathMIAtoLAXStopBOS() {
    	String[] midways = {"BOS"};
        assertTrue(graph.shortestPathWithMidway("MIA", "LAX", midways).toString().equals(
            "[MIA, ORD, JFK, BOS, LAX]"
        ));
    }
    
    /**
     * Checks the ordered sequence of airport code from
     * ORD to LAX with the stop point SFO
     */
    @Test
    public void testPathORDtoLAXStopSFO() {
    	String[] midways = {"SFO"};
        assertTrue(graph.shortestPathWithMidway("ORD", "LAX", midways).toString().equals(
            "[ORD, JFK, MIA, SFO, LAX]"
        ));
    }
    
    /**
     * 
     */
    @Test
    public void integrationTest1() {
    	
    }
    
    /**
     * 
     */
    @Test
    public void integrationTest2() {
    	
    }
    
    /**
     * 
     */
    @Test
    public void CodeReviewOfROLE1() {
    	
    }
    
    /**
     * 
     */
    @Test
    public void CodeReviewOfROLE2() {
    	
    }
}