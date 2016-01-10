package algorithms.mazeGenerator;


import java.util.Random;

/**
 * The Class SimpleMaze3dGenerator.
 */
public class SimpleMaze3dGenerator extends AbstractMaze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerator.AbstractMaze3dGenerator#generate(int, int, int)
	 */
	//Creates the maze with simple algorithm
	@Override
	public Maze3d generate(int dimension, int column, int row) {

		this.maze = new Maze3d(dimension, column, row);

		DissolveWalls(this.maze);

		this.setRandomStartPosition();
		do {
			this.setRandomGoalPosition();
		} while ((this.maze.getGoalPosition().equals(this.maze.getStartPosition())
				|| (this.maze.getStartPosition().getDimension() == this.maze.getGoalPosition().getDimension())));

		this.maze.setMaze3dIndex(this.maze.getGoalPosition(), 0);

		RandomWalk(this.maze);

		System.out.println(this.maze);

		return this.maze;
	}

	/**
	 * init method to initialize all of positions in the maze to 1
	 */
	public void DissolveWalls(Maze3d maze) {

		Random rand = new Random();
		for (int dimension = 0; dimension < maze.getDimension(); dimension++) {
			for (int row = 0; row < maze.getRow(); row++) {
				for (int column = 0; column < maze.getColumn(); column++) {
					maze.getMaze3d()[dimension][row][column] = 1;

				}
			}
		}

		/**
		 *  init method to initialize the internal of the maze to 0 or 1
		 */
		for (int dimension = 1; dimension < maze.getDimension() - 1; dimension++) {
			for (int row = 2; row < maze.getRow() - 2; row++) {
				for (int column = 2; column < maze.getColumn() - 2; column++) {
					maze.getMaze3d()[dimension][row][column] = rand.nextInt(2);
				}
			}
		}

	}

	
	
	/**
	 * @param maze
	 * @return the maze after arandom walk with safe path to the end
	 */
	public Maze3d RandomWalk(Maze3d maze) {

		Position diggerPostition = new Position(this.maze.getStartPosition());
		while (!(diggerPostition.equals(maze.getGoalPosition()))) {

			while (diggerPostition.getRows() != maze.getGoalPosition().getRows()) {
				if (diggerPostition.getRows() < maze.getGoalPosition().getRows()) {
					diggerPostition.setRows(diggerPostition.rows + 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}
				if (diggerPostition.getRows() > maze.getGoalPosition().getRows()) {
					diggerPostition.setRows(diggerPostition.rows - 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}
			}
			while (diggerPostition.getDimension() != maze.getGoalPosition().getDimension()) {
				if (diggerPostition.getDimension() < maze.getGoalPosition().getDimension()) {
					diggerPostition.setDimension(diggerPostition.dimension + 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}
				if (diggerPostition.getDimension() > maze.getGoalPosition().getDimension()) {
					diggerPostition.setDimension(diggerPostition.dimension - 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}
			}

			while (diggerPostition.getColumns() != maze.getGoalPosition().getColumns()) {
				if (diggerPostition.getColumns() < maze.getGoalPosition().getColumns()) {
					diggerPostition.setColumns(diggerPostition.columns + 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}
				if (diggerPostition.getColumns() > maze.getGoalPosition().getColumns()) {
					diggerPostition.setColumns(diggerPostition.columns - 1);
					if (maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] == 1)
						maze.getMaze3d()[diggerPostition.dimension][diggerPostition.rows][diggerPostition.columns] = 0;
				}

			}

		}

		return this.maze;
	}

}
