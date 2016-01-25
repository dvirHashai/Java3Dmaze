package model;

import java.io.IOException;
import java.util.ArrayList;

import algorithms.mazeGenerator.Position;
import algorithms.search.State;

public interface Model {
	
	/**
	 * get the data after press.
	 */
	public String[] getupdateData();
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
	 * @throws IOException 
	 */
	void getSolveMaze(String mazeName) throws IOException;

	/**
	 * @param mazeName
	 */
	void getDisplaySolution(String mazeName);

	/**
	 * @param pathname
	 */
	void getDirPath(String pathname);
	public void sendState(ArrayList<State<Position>> solution);
	void exit();
	
}
