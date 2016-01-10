package algorithms.search;

import java.util.ArrayList;

/**
 * The Interface Searchable.
 *
 * @param <T> the generic type
 */
public interface Searchable<T> {

	/**
	 * Gets the begin state.
	 * 
	 * @return the begin state
	 */
	State<T> getInitialState();

	/**
	 * Gets the end state.
	 *
	 * @return the end state
	 */
	State<T> getGoalState();

	/**
	 * Gets the all possible state.
	 * 
	 * @param State<T>
	 * @return the all possible state
	 */
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	
	/**
	 * Gets the move.
	 *
	 * @return the move cost
	 */
	double getCostMove();
	
	/**
	 * Gets the dis array.
	 * calculate the distance from State to the end State
	 * @param State<T>
	 * @return the distance array
	 */
	ArrayList<Integer> calcAirdis(State<T> s);
}
