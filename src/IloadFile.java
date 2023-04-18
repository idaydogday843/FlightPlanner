// --== CS400 Fall 2022 File Header Information ==--
// Name: tianrui yang
// Email: tyang325
// Team: CL
// TA:Abhinav Agarwal
// Lecturer: Gary's Lectures
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to load book data from a CSV file.
 */
public interface IloadFile<T> {

    /**
     * This method loads the list of flight paths described within a json file.
     *
     * @param DOTpath is the path of DOT file needed
     * @return a list of paths(which contain two airports' name and distance between them)
     */
    public AirportGraph<String, Integer> loadFilePath(String DOTpath) throws FileNotFoundException;

}