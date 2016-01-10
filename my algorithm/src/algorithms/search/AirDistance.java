package algorithms.search;

import java.util.ArrayList;

/**
 * The Class MazeAirDistance.
 */
public class AirDistance implements Heuristic{
	
	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#getCostHeuristic(java.util.ArrayList, double)
	 */
	@Override
	public double getCostHeuristic(ArrayList<Integer> DisArray, double costmove) {
		double totalcost = 0;
		
		for (int i = 0; i < DisArray.size(); i++) {
			totalcost += Math.pow(DisArray.get(i), 2);	
		}
		
		return Math.sqrt(totalcost) * costmove;
	}
}
