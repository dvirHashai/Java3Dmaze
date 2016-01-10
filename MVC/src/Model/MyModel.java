package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.Controller;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.mazeGenerator.Position;
import algorithms.search.AStar;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Maze3dSearchable;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends MyAbstractModel {

	/**
	 * @param controller
	 */
	public MyModel(Controller controller) {
		super(controller);
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getDirPath(java.lang.String)
	 */
	// to get all the files in the specific path
	@Override
	public void getDirPath(String pathname) {

		if (pathname.length() == 0) {
			controller.modelSolution("Invalid Path".split(" "));
			return;
		}
		File file = new File(pathname);
		if (!file.isDirectory()) {
			controller.modelSolution((pathname + "directory not exist").split(" "));
			return;
		}

		controller.modelSolution(file.list());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getGenrate3dMaze(java.lang.String, int, int,
	 * int)
	 */
	// to generate new maze with he's name dimensions, rows and columns
	@Override
	public void getGenrate3dMaze(String mazeName, int dimensions, int rows, int columns) {

		controller.runThread(new Runnable() {

			@Override
			public void run() {
				try {
					if (!mazeMap.containsKey(mazeName)) {
						Maze3d currentMaze = new MyMaze3dGenerator().generate(dimensions, rows, columns);
						mazeMap.put(mazeName, currentMaze);
						controller.modelSolution(("maze" + " " + mazeName + " " + "is ready" + "\n").split("\b"));
					}
					else {
						throw new RuntimeException("the maze is exist in database");
					}
				}
				catch (Exception e) {
					controller.modelSolution(e.toString().split("\b"));
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getDisplayMaze(java.lang.String)
	 */
	// to show any maze that was created
	@Override
	public void getDisplayMaze(String mazeName) {
		try {
			if (mazeMap.containsKey(mazeName)) {
				controller.modelSolution(
						("*Start Position* :" + mazeMap.get(mazeName).getStartPosition().toString() + "\n")
						.split("\b"));
				controller.modelSolution(
						("*Goal Position*  :" + mazeMap.get(mazeName).getGoalPosition().toString() + "\n").split("\b"));
				controller.modelSolution(mazeMap.get(mazeName).toString().split("\b"));
			}
			else {
				throw new RuntimeException("The maze is not exist in dataBase");
			}
		}

		catch (Exception e) {
			controller.modelSolution(e.toString().split("\b"));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getDisplayCrossSection(java.lang.String, int,
	 * java.lang.String)
	 */
	// to show the specific crossSection by X,Y or Z
	@Override
	public void getDisplayCrossSection(String cross, int index, String mazeName) throws IOException {
		try {
			if (mazeMap.containsKey(mazeName)) {
				Pattern crossByX = Pattern.compile("[Xx]");
				Pattern crossByY = Pattern.compile("[Yy]");
				Pattern crossByZ = Pattern.compile("[Zz]");
				Matcher columns = crossByX.matcher(cross);
				Matcher dimension = crossByY.matcher(cross);
				Matcher rows = crossByZ.matcher(cross);

				if (columns.lookingAt())
					controller.modelSolution(mazeMap.get(mazeName)
							.CrossToString(mazeMap.get(mazeName).getCrossSectionByX(index)).toString().split("\b"));
				if (dimension.lookingAt())
					controller.modelSolution(mazeMap.get(mazeName)
							.CrossToString(mazeMap.get(mazeName).getCrossSectionByY(index)).toString().split("\b"));
				if (rows.lookingAt())
					controller.modelSolution(mazeMap.get(mazeName)
							.CrossToString(mazeMap.get(mazeName).getCrossSectionByZ(index)).toString().split("\b"));

			}
			else {
				throw new RuntimeException("the index is outside the scope of the maze");

			}
		}
		catch (Exception e) {
			controller.modelSolution(((e.toString() + "\n" + "the index is outside the scope of the maze"
					+ "the sizes of the crosses are :" + "\n" + "X:" + mazeMap.get(mazeName).getColumn() + "\n" + "Y:"
					+ mazeMap.get(mazeName).getDimension() + "\n" + "Z:" + mazeMap.get(mazeName).getRow()).toString())
					.split("\b"));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getSaveMaze(java.lang.String,
	 * java.lang.String)
	 */
	// to save our zipped maze with specific name and in specific file name
	@Override
	public void getSaveMaze(String mazeName, String fileName) throws IOException {
		if (mazeMap.containsKey(mazeName)) {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName + ".maze"));
			out.write(mazeMap.get(mazeName).toByteArray());
			out.flush();
			out.close();
			controller.modelSolution(
					("the maze: " + mazeName + " " + "is saved successfully in file: " + fileName).split("\b"));
		}
		else {
			throw new RuntimeException("The maze is not exist in dataBase");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getLodeMaze(java.lang.String,
	 * java.lang.String)
	 */
	// to load our maze with he's specific name and from specific file
	@Override
	public void getLoadeMaze(String fileName, String mazeName) throws IOException {
		MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(new File(fileName + ".maze")));
		byte b[] = new byte[(int)Files.size(new File(fileName + ".maze").toPath()) + 34];
		in.read(b);
		in.close();
		mazeMap.put(mazeName, new Maze3d(b));
		if (mazeMap.containsKey(mazeName)) {
			controller.modelSolution(("The maze: " + mazeName + " " + "loaded successfully").split("\b"));
		}
		else {
			throw new RuntimeException(
					"The maze: " + mazeName + " " + "is not exist in database please check the file name");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getMazeSize(java.lang.String)
	 */
	// to display the size of the maze in the memory
	@Override
	public void getMazeSize(String mazeName) throws IOException {
		if (mazeMap.containsKey(mazeName)) {
			controller.modelSolution(("" + mazeMap.get(mazeName).getDimension() * mazeMap.get(mazeName).getRow()
					* mazeMap.get(mazeName).getColumn() * 4 + 12 + 12 + 12 + " " + "bit").split("\b"));
		}
		else {
			throw new RuntimeException(
					"The maze: " + mazeName + " " + "is not exist in database please check the file name");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getFileSize(java.lang.String)
	 */
	// to display the size of the maze in the file
	@Override
	public void getFileSize(String mazeName) {

		try {
			controller
			.modelSolution(("" + Files.size(new File(mazeName + ".maze").toPath()) + " " + "bit").split("\b"));
		}
		catch (IOException e) {
			throw new RuntimeException(
					"The file: " + mazeName + " " + "is not exist in database please check the name of the maze");

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getSolveMaze(java.lang.String,
	 * java.lang.String)
	 */
	// to solve our maze with BFS algorithm or Astar algorithm (with 2 heuristic
	// - ManhattanDistqance or AirDistance)
	@Override
	public void getSolveMaze(String mazeName, String algorithmName) {
		controller.runThread(new Runnable() {
			public void run() {
				Maze3d maze = mazeMap.get(mazeName);
				Searchable<Position> searchableMaze = new Maze3dSearchable<>(maze);
				Searcher<Position> algorithem;
				ArrayList<State<Position>> solution = new ArrayList<State<Position>>();
				Pattern BFS = Pattern.compile("[BFSbfs]");
				Pattern ManhattanDistance = Pattern.compile("[MANHATTANDISTANCEmanhattandistance]");
				Pattern AirDistance = Pattern.compile("[AIRDISTANCEairdistance]");
				Matcher m1 = BFS.matcher(algorithmName);
				Matcher m2 = ManhattanDistance.matcher(algorithmName);
				Matcher m3 = AirDistance.matcher(algorithmName);

				if (mazeMap.containsKey(mazeName)) {
					if (m1.lookingAt()) {
						algorithem = new BFS<Position>();
						solution = algorithem.search(searchableMaze).getSol();

					}

					if (m2.lookingAt()) {
						algorithem = new AStar<Position>(new ManhattanDistance());
						solution = algorithem.search(searchableMaze).getSol();

					}

					if (m3.lookingAt()) {
						algorithem = new AStar<Position>(new AirDistance());
						solution = algorithem.search(searchableMaze).getSol();

					}

					if (!solution.isEmpty()) {
						solutionMap.put(mazeName, solution);
						controller.modelSolution(("solution for: " + mazeName + "is ready").split("\b"));
					}

				}
				else {
					controller.modelSolution(
							("The maze: " + mazeName + " " + "is not exist in database please check the file name")
							.split("\b"));
				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.MyAbstractModel#getDisplaySolution(java.lang.String)
	 */
	// to display the solution step by step
	@Override
	public void getDisplaySolution(String mazeName) {
		if (mazeMap.containsKey(mazeName)) {

			if (solutionMap.containsKey(mazeName)) {
				ArrayList<State<Position>> solution = solutionMap.get(mazeName);

				String[] sol = new String[solution.size()];
				for (int i = 0; i < sol.length; i++) {
					sol[i] = solution.get(i).getState().toString();
				}

				controller.modelSolution(sol);

			}
			else {
				controller.modelSolution(("the maze: " + mazeName + " is not not have solution").split("\b"));
			}
		}
		else {
			controller.modelSolution(("the maze: " + mazeName + " is not exist").split("\b"));
		}

	}

}
