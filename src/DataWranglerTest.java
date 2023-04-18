// --== CS400 Fall 2022 File Header Information ==--
// Name: tianrui yang
// Email: tyang325
// Team: CL
// TA:Abhinav Agarwal
// Lecturer: Gary's Lectures
// Notes to Grader: none

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tester for DataWrangler classes. Peer review tests for Frontend Developer is in another file
 * named DataWranglerPeerCodeTests since it uses javafx. While these tests work, I can't seem to
 * get the Peer Code tests to work as I have documented in the file.
 */
class DataWranglerTest {
	/**
     * run all junit test
     */
    public static class TestAll {
        /**
         * test1:test the basic insert
         *
         * @return the result of test
         */
        @Test
        public void test1() {
            try {

                AirportGraph<String, Double> file = new loadFile().loadFilePath("Small.dot");
                System.out.println();

                assertEquals(file.getVertexCount(), 6);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        
        /**
         * Tester for loading in a large file
         */
        @Test
        public void test2() {
            try {

            	AirportGraph<String, Double> file = new loadFile().loadFilePath("Flight.dot");
                System.out.println();

                assertEquals(file.getVertexCount(), 302);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Tester for loading in a large file and the vertex(e.g.LAX, SEA) had load into cs400 graph
         */
        @Test
        public void test3() {
            try {

            	AirportGraph<String, Double> file = new loadFile().loadFilePath("Flight.dot");

                assertEquals(file.getDistance("LAX", "SEA"), 954);


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        /**
         * Tester for loading in a large file and could use the node in the graph to find the shortest path
         */
        @Test
        public void test4() {
            try {
            	AirportGraph<String, Double> file = new loadFile().loadFilePath("Flight.dot");

                assertEquals(file.shortestPath("LAX", "SEA").get(0), "LAX");


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Tester for loading in a large file and test the number of edge
         */
        @Test
        public void test5() {
            try {
            	AirportGraph<String, Double> file = new loadFile().loadFilePath("Flight.dot");

                assertEquals(file.getEdgeCount(), 2811);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
