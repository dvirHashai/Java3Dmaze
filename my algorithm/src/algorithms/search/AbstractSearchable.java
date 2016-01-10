package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

import algorithms.mazeGenerator.Maze3d;

public abstract class AbstractSearchable<T> implements Searchable<T>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2162116865668238326L;

	/** The maze. */
	protected Maze3d maze;
	
	/** The one move cost. */
	protected double costMove;
	
	/**
	 * Instantiates a new maze3d searchable.
	 * Constructor
	 * @param maze
	 * @param cost=1
	 */
	public AbstractSearchable(Maze3d maze) {
		this.maze = maze;
		this.costMove = 1;
	}

	/**
	 * Instantiates a new maze3d searchable.
	 * Constructor
	 * @param maze
	 * @param cost
	 */
	public AbstractSearchable(Maze3d maze, int costMove) {
		this.maze = maze;
		this.costMove = costMove;
	}
	
	@Override
	abstract public State<T> getInitialState();

	@Override
	abstract public State<T> getGoalState();

	@Override
	abstract public ArrayList<State<T>> getAllPossibleStates(State<T> s);

	@Override
	abstract public double getCostMove();

	@Override
	abstract public ArrayList<Integer> calcAirdis(State<T> s);

}
