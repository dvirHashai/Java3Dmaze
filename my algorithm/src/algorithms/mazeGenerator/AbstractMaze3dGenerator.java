package algorithms.mazeGenerator;

import java.util.Random;

/**
 * @author Chen
 *
 *The Class AbstractMaze3dGenerator
 */
public abstract class AbstractMaze3dGenerator implements Maze3dGenerator {

	protected Maze3d maze;
	

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerator.Maze3dGenerator#generate(int, int, int)
	 */
	abstract public Maze3d generate(int dimension, int row, int column);

	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerator.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 */
	@Override
	public String measureAlgorithmTime(int dimension, int row, int column) {
		double start = System.currentTimeMillis();
		generate(dimension, column, row);
		double finish = System.currentTimeMillis();
		return Double.toString((finish - start) * (Math.pow(10, -3)));
	}

	/**
	 * Random index.
	 *
	 * @param create a position and insert random numbers to the position (for begin position)
	 * @return the position
	 */
	//random position for begin
	public Position setRandomStartPosition() {
		int a;
		Position p = null;
		Random rand = new Random();
		int i = rand.nextInt(maze.getDimension() - 2) + 1;
		int z = rand.nextInt(4);

		switch (z) {
		    //the start position will be in the first row +1 without the corners and the hidden walls(row = 1)
		case 0:
			a = rand.nextInt(maze.getRow() - 4) + 2;
			p = new Position(i, 1, a);
			break;
			//the start position will be in the first column+1 without the corners and the hidden walls (column = 1)
		case 1:
			a = rand.nextInt(maze.getRow() - 4) + 2;
			p = new Position(i, this.maze.getRow() - 2, a);
			break;
			//the start position will be in the last row-1 without the corners and the hidden walls
		case 2:
			a = rand.nextInt(maze.getColumn() - 4) + 2;
			p = new Position(i, a, 1);
			break;
			//the start position will be in the last column-1 without the corners and the hidden walls
		case 3:
			a = rand.nextInt(maze.getColumn() - 4) + 2;
			p = new Position(i, a, this.maze.getColumn() - 2);
			break;
		default:
			break;
		}

		this.maze.setStartPosition(p);
		this.maze.getMaze3d()[p.dimension][p.rows][p.columns] = 0;

		return p;
	}

	/**
	 * Random index.
	 *
	 * @param create a position and insert random numbers to the position (for goal position)
	 * @return the position
	 */
	//random position for goal
	public Position setRandomGoalPosition() {
		int a;
		Position p = null;

		do {
			Random rand = new Random();
			int i = rand.nextInt(maze.getDimension() - 2) + 1;
			int z = rand.nextInt(4);
			switch (z) {
			
			case 0:
				a = rand.nextInt(maze.getRow() - 4) + 2;
				p = new Position(i, 1, a);
				break;
			//the start position will be in the first column+1 without the corners and the hidden walls (column = 1)
			case 1:
				a = rand.nextInt(maze.getRow() - 4) + 2;
				p = new Position(i, this.maze.getRow() - 2, a);
				break;
			//the start position will be in the last row-1 without the corners and the hidden walls
			case 2:
				a = rand.nextInt(maze.getColumn() - 4) + 2;
				p = new Position(i, a, 1);
				break;
				//the start position will be in the last column-1 without the corners and the hidden walls
			case 3:
				a = rand.nextInt(maze.getColumn() - 4) + 2;
				p = new Position(i, a, this.maze.getColumn() - 2);
				break;
			default:
				break;
			}
		}
		while (!isValidGoal(p));

		this.maze.setGoalPosition(p);
		this.maze.getMaze3d()[p.dimension][p.rows][p.columns] = 0;
		
		return p;
	}
	
	private boolean isValidGoal(Position position) {
		boolean isValid = false;
		int adjacentsCounter = 0;
		
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.FORWARD)) == 0)
			adjacentsCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.BACKWARD)) == 0)
			adjacentsCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.RIGHT)) == 0)
			adjacentsCounter++;
		if (maze.getmaze3dIndex(Position.MergerPos(position, Position.LEFT)) == 0)
			adjacentsCounter++;
		
		if (adjacentsCounter == 1 && position.dimension != maze.getStartPosition().dimension)
			isValid = true;
		
		return isValid;
	}
}
