package algorithms.search;

import java.util.ArrayList;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

/**
 * The Class Maze3dSearchable.
 */


public class Maze3dSearchable<T> extends AbstractSearchable<Position> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1852579272575868526L;

	public Maze3dSearchable(Maze3d maze, int costMove) {
		super(maze, costMove);
		
	}
	
	public Maze3dSearchable(Maze3d maze) {
		super(maze);
		
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getInitialState()
	 */
	@Override
	public  State<Position> getInitialState() {
		State<Position> start = new State<Position>(maze.getStartPosition());
		start.setCost(0);
		start.setCameFrom(null);
		return start;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<Position> getGoalState() {
		State<Position> goal = new State<Position>(maze.getGoalPosition());
		return goal;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<Position> poss = maze.PossibleMoves(s.getState());

		ArrayList<State<Position>> state = new ArrayList<>();

		for (int i = 0; i < poss.size(); i++) {
			state.add(new State<Position>(poss.get(i)));
			state.get(i).setCameFrom(s);
			state.get(i).setCost(0);
		}

		return state;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getCostMove()
	 */
	@Override
	public double getCostMove() {
		return this.costMove;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#calcAirdis(algorithms.search.State)
	 */
	@Override
	public ArrayList<Integer> calcAirdis(State<Position> s){
		ArrayList<Integer> air =  new ArrayList<>();
		
		State<Position> pos = new State<Position>(s.getState());
		
		air.add(Math.abs(maze.getGoalPosition().getDimension() - pos.getState().getDimension()));
		air.add(Math.abs(maze.getGoalPosition().getColumns() - pos.getState().getColumns()));
		air.add(Math.abs(maze.getGoalPosition().getRows() - pos.getState().getRows()));
		
		return air;	
	}


}
