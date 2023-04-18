// --== CS400 P3W3 BackendDeveloper: DWPlaceholder class ==--
// Name: <Thomas Sun>
// CSL Username: <tsun>
// Email: <twsun@wisc.edu>
// Team: CL
// TA: Abhinav Agarwal
// Lecture #: <002>
// Notes to Grader: <any optional extra notes to your grader>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class acts as the placeholder for the data wrangler, creating an object that
 * contains the origin and destination airport, as well as the path between them.
 */
public class Path implements IPath {
	public String originName, destinationName;
	public double distance;

	/**
	 * This is the constructor that creates an object to store an origin and destination
	 * airport, and their paths
	 * @param originName
	 * @param destinationName
	 * @param distance
	 */
	public Path(String originName, String destinationName, double distance) {
		this.originName = originName;
		this.destinationName = destinationName;
		this.distance = distance;
	}

	@Override
	public String getOrigin() {
		// TODO Auto-generated method stub
		return this.originName;
	}

	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return this.destinationName;
	}

	@Override
	public double getDistance() {
		// TODO Auto-generated method stub
		return this.distance;
	}
}
