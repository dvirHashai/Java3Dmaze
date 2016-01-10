package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Class CommonSearcher.
 *
 * @param <T> the generic type
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	 /** The priority queue. */
	protected PriorityQueue<State<T>> openList;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;
	
	 /**
 	 * Instantiates a new common searcher and make the priority queue comparable to sort the states
 	 */
	public CommonSearcher() {
		Comparator<State<T>> stateComparator = new StateComparator<T>();
		openList = new PriorityQueue<State<T>>(stateComparator);
		evaluatedNodes = 0;
	}

	 /**
 	 * Pop one from the priority queue.
 	 * Rise by 1 the evaluated Nodes 
 	 * @return the state
 	 */
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;

	}
	
	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public abstract Solution<T> search(Searchable<T> s);

	/**
 	 * Back trace.
 	 * return to the begin from end,and make the solution
 	 * @param end
 	 * @param begin
 	 * @return the solution
 	 */
	protected Solution<T> backTrace(State<T> goal, State<T> start) {
		Solution<T> solution = new Solution<T>();

		solution.Recursive(goal, start);
		
		return solution;
	}

}
