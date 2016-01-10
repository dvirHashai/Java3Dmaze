package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Class BFS_Algorithem.
 *
 * @param <T> the generic type
 */
public class BFS<T> extends CommonSearcher<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	public Solution<T> search(Searchable<T> search) {
		openList.add(search.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (!(openList.isEmpty())) {
			State<T> state = popOpenList();// dequeue
			closedSet.add(state);

			if (state.equals(search.getGoalState()))
				// private method, back traces through the parents
				return backTrace(state, search.getInitialState());
			//Check the successors for index specific
			ArrayList<State<T>> successors = search.getAllPossibleStates(state);

			for (State<T> currentState : successors) {
				//Check for successors specific if it white add it to the priority queue
				if (!closedSet.contains(currentState) && !openList.contains(currentState)) {
					currentState.setCameFrom(state);
					currentState.setCost(state.getCost() + search.getCostMove());
					openList.add(currentState);
				}
				//Check if the Connection between the currentState and the specific index is bather then the cost that it all rady have 
				else if (state.getCost() + search.getCostMove() < currentState.getCost()) {
					if (!openList.contains(currentState)) {
						openList.add(currentState);
					}
					else {
						currentState.setCost(state.getCost() + search.getCostMove());
						currentState.setCameFrom(state);
					}
				}
			}
		}

		return null;
	}
}