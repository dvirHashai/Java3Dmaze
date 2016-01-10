package algorithms.search;

import java.util.ArrayList;

/**
 * The Class MazeManhattanDistance.
 */
public class ManhattanDistance implements Heuristic{
	
	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#getCostHeuristic(java.util.ArrayList, double)
	 */
	@Override
	public double getCostHeuristic(ArrayList<Integer> DisArray, double costmove) {
		double totalcost = 0;
		
		for (int i = 0; i < DisArray.size(); i++) {
			totalcost += DisArray.get(i);
		}
		
		return totalcost * costmove;
	}
}
