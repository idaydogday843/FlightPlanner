// --== CS400 P3W3 BackendDeveloper Tests Official ==--
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BackendDeveloperTests {
  private AirportGraph<String,Double> _graph;
  private PathFinderBackend _backendInstance = null;
	private Path _dataInstance = null;
  
  //private final PrintStream standardOutput = System.out;
  //private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  // BeforeEach annotation makes a method invoked automatically
  // before each test
  @BeforeEach
  public void createInstance() {
        	_graph = new AirportGraph<String, Double>();
          _backendInstance = new PathFinderBackend(_graph, _dataInstance);
         // System.setOut(new PrintStream(outputStream));
  }
  
	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest1() {
		_graph = new AirportGraph<String, Double>();
		_dataInstance = new Path("Origin1", "Destination1", 10.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);

		_graph.insertAirport("Origin1");
		_graph.insertAirport("Destination1");
		_backendInstance.addPath(_dataInstance);
		
		boolean expectedResult = true;

		boolean actual = _graph.containsAirport("Origin1");
		
		Assert.assertEquals(expectedResult, actual);		

	}
	

	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest2() {
		_graph = new AirportGraph<String, Double>();
		
		_graph.insertAirport("Origin1");
		_graph.insertAirport("Origin2");
		_graph.insertAirport("Origin3");
		_graph.insertAirport("Origin4");
		_graph.insertAirport("Destination1");
		
		_dataInstance = new Path("Origin1", "Origin2", 3.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin2", "Destination1", 5.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin1", "Origin3", 1.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin3", "Origin4", 4.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin4", "Origin1", 7.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		
		String expectedResult = "5.0";

		double value = _graph.getPathCost("Origin1", "Origin4");
		String actual = String.valueOf(value);

		Assert.assertEquals(actual, expectedResult);		

	}
	
	
	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest3() {
		_graph = new AirportGraph<String, Double>();
		
		_graph.insertAirport("Origin1");
		_graph.insertAirport("Origin2");
		_graph.insertAirport("Origin3");
		_graph.insertAirport("Origin4");
		_graph.insertAirport("Destination1");
		
		_dataInstance = new Path("Origin1", "Origin2", 3.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin2", "Destination1", 5.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin1", "Origin3", 1.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin3", "Origin4", 4.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin4", "Origin1", 7.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		
		String actual = "[Origin1, Origin2, Destination1]";
		List<String> theShortestPath =_backendInstance.findShortestPath("Origin1", "Destination1");
		
		Assert.assertEquals(String.valueOf(theShortestPath), actual);
	}
	
	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest4() {
		_graph = new AirportGraph<String, Double>();
		
		_graph.insertAirport("Origin1");
		_graph.insertAirport("Origin2");
		_graph.insertAirport("Origin3");
		_graph.insertAirport("Origin4");
		_graph.insertAirport("Destination1");
		
		_dataInstance = new Path("Origin1", "Origin2", 3.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin2", "Destination1", 5.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin1", "Origin3", 1.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		
		String actual = "[[Origin2], [Origin3]]";

		List<String> neighbors = _backendInstance.findAllNeighbors("Origin1");
		
		Assert.assertEquals(neighbors.toString(), actual);
	}
	
	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest5() {
		_graph = new AirportGraph<String, Double>();
		
		_graph.insertAirport("Origin1");
		_graph.insertAirport("Origin2");
		_graph.insertAirport("Origin3");
		_graph.insertAirport("Origin4");
		_graph.insertAirport("Destination1");
		
		_dataInstance = new Path("Origin1", "Origin2", 3.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin2", "Destination1", 5.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin1", "Origin3", 1.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
			
		String actual = "[Destination1, Origin4, Origin3, Origin2, Origin1]";
		
		Assert.assertEquals(_graph.allAirports().toString(), actual);
	}
	
	/**
	 * This test method checks the implementation of the user interface given a custom input string.
	 */
	@Test
	public void roleTest6() {
		_graph = new AirportGraph<String, Double>();
		
		_graph.insertAirport("Origin1");
		_graph.insertAirport("Origin2");
		_graph.insertAirport("Origin3");
		_graph.insertAirport("Origin4");
		_graph.insertAirport("Destination1");
		
		_dataInstance = new Path("Origin1", "Origin2", 3.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin2", "Destination1", 5.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		_dataInstance = new Path("Origin1", "Origin3", 1.0);
		_backendInstance = new PathFinderBackend(_graph, _dataInstance);
		_backendInstance.addPath(_dataInstance);
		
		LinkedList<String> list = new LinkedList<>();
		list.add("Origin2");
		
		System.out.println(_backendInstance.findShortestPathWithLayover("Origin1", "Destination1", list));
		
			
		String actual = "[Destination1, Origin4, Origin3, Origin2, Origin1]";
		
		Assert.assertEquals(_graph.allAirports().toString(), actual);
	}
	
}
