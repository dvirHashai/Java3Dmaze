package algorithms.mazeGenerator;

/**
 * @author Chen
 *
 *The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator {

	/**
	 * Generate.
	 * get 3 int's to create the maze
	 * @param dimension
	 * @param row
	 * @param column
	 * @return maze3d
	 */
	//Creates the maze
	public Maze3d generate(int dimension,int row , int column );
	
	/**
	 * Measure algorithm time.
	 * get 3 int's create maze and check how many time it take
	 * @param dimension
	 * @param row
	 * @param column
	 * @return string with time 
	 */
	//run time check
	public String measureAlgorithmTime(int dimension,int row, int column);
	
}
