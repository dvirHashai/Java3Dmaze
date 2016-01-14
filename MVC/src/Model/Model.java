package Model;

import java.io.IOException;

public interface Model {

	/**
	 * @param mazeName
	 * @param dim
	 * @param row
	 * @param col
	 */
	void getGenrate3dMaze(String mazeName, int dim, int row, int col);

	/**
	 * @param mazeName
	 */
	void getDisplayMaze(String mazeName);

	/**
	 * @param cross
	 * @param index
	 * @param mazeName
	 * @throws IOException
	 */
	void getDisplayCrossSection(String cross, int index, String mazeName) throws IOException;

	/**
	 * @param mazeName
	 * @param fileName
	 * @throws IOException
	 */
	void getSaveMaze(String mazeName, String fileName) throws IOException;

	/**
	 * @param fileName
	 * @param mazeName
	 * @throws IOException
	 */
	void getLoadeMaze(String fileName, String mazeName) throws IOException;

	/**
	 * @param mazeName
	 * @throws IOException
	 */
	void getMazeSize(String mazeName) throws IOException;

	/**
	 * @param mazeName
	 * @throws IOException
	 * 
	 */
	void getFileSize(String mazeName) throws IOException;

	/**
	 * @param mazeName
	 * @param algorithmName
	 */
	void getSolveMaze(String mazeName, String algorithmName);

	/**
	 * @param mazeName
	 */
	void getDisplaySolution(String mazeName);

	/**
	 * @param pathname
	 */
	void getDirPath(String pathname);
	

}
