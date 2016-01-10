package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Class Astar_Algorithem.
 *
 * @param <T> the generic type
 */
public class AStar<T> extends CommonSearcher<T> {

	/** The Heuristic. */
	private Heuristic heuristic;

	/**
	 * Instantiates the specific heuristic for astar.
	 * Contractor
	 * @param Heuristic
	 */
	public AStar(Heuristic H) {
		this.heuristic = H;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> search) {
		
		openList.add(search.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (!(openList.isEmpty())) {
			State<T> state = popOpenList();
			closedSet.add(state);

			 // private method, back traces through the parents
			if (state.equals(search.getGoalState()))
				return backTrace(state, search.getInitialState());
			
			//Check the successors for index specific
			ArrayList<State<T>> successors = search.getAllPossibleStates(state);

			for (State<T> currentState : successors) {
				//Check for successors specific if it white add it to the priority queue
				if (!closedSet.contains(currentState) && !openList.contains(currentState)) {
					currentState.setCameFrom(state);
					// calculat the cost of the currentState together with the heuristic
					currentState.setCost(state.getCost() +  heuristic.getCostHeuristic(search.calcAirdis(currentState), search.getCostMove()));
					openList.add(currentState);
				}
				//Check if the Connection between the currentState and the specific index is bather then the cost that it all ready have 
				else if (state.getCost() + heuristic.getCostHeuristic(search.calcAirdis(currentState), search.getCostMove()) < currentState.getCost()) {
					if (!openList.contains(currentState)) {
						openList.add(currentState);
					}
					else {
						currentState.setCost(state.getCost() + heuristic.getCostHeuristic(search.calcAirdis(currentState), search.getCostMove()));
						currentState.setCameFrom(state);
					}
				}
			}
		}

		return null;
	}
}
