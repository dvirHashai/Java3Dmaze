package algorithms.mazeGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * The Class MyMaze3dGenerator.
 */
public class MyMaze3dGenerator extends AbstractMaze3dGenerator {

	

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerator.AbstractMaze3dGenerator#generate(int, int, int)
	 */
	//Creates the maze with DFS
	@Override
	public Maze3d generate(int dimensions, int rows, int columns) {
		this.maze = new Maze3d(dimensions, rows, columns);

		init();
		init2();

		Stack<Position> visited = new Stack<>();
		ArrayList<Position> possibleAdjecents = new ArrayList<>(); 
		visited.push(this.setRandomStartPosition());
		int randomAdjacent;

		// run until visited array is empty
		while (!visited.isEmpty()) {
			// get the possible move for index
			//peek()- return the top of the stack
			possibleAdjecents = getPossibleAdjecents(visited.peek());

			// check if the array is empty
			if (!possibleAdjecents.isEmpty()) {
				while (!possibleAdjecents.isEmpty()) {
					// random position and put in the index 0
					Random rand = new Random();
					randomAdjacent = rand.nextInt(possibleAdjecents.size());
					Position position = possibleAdjecents.get(randomAdjacent);
					possibleAdjecents.remove(randomAdjacent);
					this.maze.setMaze3dIndex(position, 0);
					visited.push(position);
				}
			} else {
				visited.pop();
			}
		}
		
		this.setRandomGoalPosition();
		
		return this.maze;
	}

	
	/**
	 * init method to initialize all of positions in the maze to 2
	 */
	private void init() {
		for (int j = 0; j < maze.getDimension(); j++) {
			for (int j2 = 0; j2 < maze.getRow(); j2++) {
				for (int k = 0; k < maze.getColumn(); k++) {
					this.maze.getMaze3d()[j][j2][k] = 2;
				}
			}
		}
	}

	
	
	/**
	 *  init method to initialize the internal of the maze to 1
	 */
	private void init2() {
		for (int dimension = 1; dimension < maze.getDimension() - 1; dimension++) {
			for (int row = 2; row < maze.getRow() - 2; row++) {
				for (int column = 2 ; column < maze.getColumn() - 2; column++) {
					maze.getMaze3d()[dimension][row][column] = 1;
				}
			}
		}
	}

	
	/**
	 * Possible move.
	 * check if near index's are possible move
	 * @param maze3d
	 * @param Position
	 * @return the array list with the possible position
	 */
	public ArrayList<Position> getPossibleAdjecents(Position position) {
		ArrayList<Position> possibleMoveList = new ArrayList<Position>();
		
		Position positionUp = Position.MergerPos(position, Position.UP);
		Position positionDown = Position.MergerPos(position, Position.DOWN);
		Position positionRight = Position.MergerPos(position, Position.RIGHT);
		Position positionLeft = Position.MergerPos(position, Position.LEFT);
		Position positionBackward = Position.MergerPos(position, Position.BACKWARD);
		Position positionForward = Position.MergerPos(position, Position.FORWARD);

		if (maze.getmaze3dIndex(positionForward) == 1) {
			if (moveChecker(maze, positionForward)) {
				possibleMoveList.add(positionForward);
			}
		}

		if (maze.getmaze3dIndex(positionBackward) == 1) {
			if (moveChecker(maze, positionBackward))
				possibleMoveList.add(positionBackward);
		}

		if (maze.getmaze3dIndex(positionUp) == 1 ) {
			if (moveChecker(maze, positionUp))
				possibleMoveList.add(positionUp);
		}

		if (maze.getmaze3dIndex(positionDown) == 1) {
			if (moveChecker(maze, positionDown))
				possibleMoveList.add(positionDown);
		}

		if (maze.getmaze3dIndex(positionRight) == 1){
			if (moveChecker(maze, positionRight))
				possibleMoveList.add(positionRight);
		}

		if (maze.getmaze3dIndex(positionLeft) == 1) {
			if (moveChecker(maze, positionLeft))
				possibleMoveList.add(positionLeft);
		}

		return possibleMoveList;
	}

	/**
	 * Check.
	 * check if the possible move is really possible
	 * @param maze3d
	 * @param Position
	 * @return true if the position have only 1 near position that the position value is 0
	 */
	//check if in the next index value is not wall to be sure the position is really possible move 
	public boolean moveChecker(Maze3d maze, Position position) {
		int zeroCounter = 0;
		
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.FORWARD)) == 0)
			zeroCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.BACKWARD)) == 0)
			zeroCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.RIGHT)) == 0)
			zeroCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.LEFT)) == 0)
			zeroCounter++;
		
		// "<=" : because if we are in the start position there is no adjacent with 0. 
		if (zeroCounter <= 1)
			return true;
		else
			return false;
	}

}
