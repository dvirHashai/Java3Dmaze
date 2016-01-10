package algorithms.search;

import java.util.ArrayList;

/**
 * The Class Solution.
 *
 * @param <T> the generic type
 */
public class Solution<T> {

	/** The solution stack. */
	private ArrayList<State<T>> sol;

	
	/**
	 * @param goal
	 * @param start
	 * recursive method to print the best way from the start to goal 
	 */
	public void Recursive(State<T> goal, State<T> start){
		
		if (goal.equals(start)) {
			sol.add(goal);
			//System.out.println(goal.getState().toString());
			return;
		}
		
		Recursive(goal.getCameFrom(), start);
		sol.add(goal);
		//System.out.println(goal.getState().toString());
	}
	
	/**
	 * Instantiates a new solution.
	 * Default constructor
	 */
	public Solution() {
		this.setSol(new ArrayList<State<T>>());
	}

	public ArrayList<State<T>> getSol() {
		return sol;

	}

	public void setSol(ArrayList<State<T>> sol) {
		this.sol = sol;
	}

}