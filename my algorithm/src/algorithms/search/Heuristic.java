/**
 * 
 */
package algorithms.search;

import java.util.ArrayList;

/**
 * The Interface Heuristic.
 */
public interface Heuristic {
	
	
	/**
	 * Gets the cost.
	 * calculate the distance with air distance algorithm
	 * @param ArrayList<Integer>
	 * @param one move in the problem
	 * @return the cost
	 */
	double getCostHeuristic(ArrayList<Integer> DisArray, double d);
}
