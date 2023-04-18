// --== CS400 Fall 2022 File Header Information ==--
// Name: tianrui yang
// Email: tyang325
// Team: CL
// TA:Abhinav Agarwal
// Lecturer: Gary's Lectures
// Notes to Grader: none

/**
 * Instances of classes that implement this interface represent a single airport and it's information.
 */
public interface IPath {

    // constructor args (String originAirport'sName, String destinationAirport'sName, int distanceBetweenThem)

    /**
     * get the origin airport name
     * @return origin airport name
     */
    public String getOrigin();

    /**
     * get the destination airport name
     * @return destination airport name
     */
    public String getDestination();

    /**
     * get the distance between two airport
     * @return distance
     */
    public double getDistance();
}