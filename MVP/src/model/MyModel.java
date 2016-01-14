package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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

	@SuppressWarnings("unchecked")
	public MyModel() throws IOException, IOException, ClassNotFoundException {

		File solution = new File("solution" + ".zip");
		File maze = new File("maze3d" + ".zip");
		if (solution.exists() && solutionMap.isEmpty()) {
			ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream("solution" + ".zip")));
			Object o = in.readObject();

			if (o instanceof HashMap<?, ?>) {
				solutionMap = (HashMap<String, ArrayList<State<Position>>>) o;
			}

			in.close();

		}

		if (maze.exists() && mazeMap.isEmpty()) {
			ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream("maze3d" + ".zip")));
			Object o = in.readObject();

			if (o instanceof HashMap<?, ?>) {
				mazeMap =  (HashMap<String, Maze3d>) o;
			}

			in.close();

		}

	}

	@Override
	public void getDirPath(String pathname) {
		if (pathname.length() == 0) {
			this.updateData = "Invalid Path".split(" ");
			setChanged();
			notifyObservers();
			return;
		}
		File file = new File(pathname);
		if (!file.isDirectory()) {
			this.updateData = (pathname + "directory not exist").split(" ");
			setChanged();
			notifyObservers();
			return;
		}

		this.updateData = file.list();
		setChanged();
		notifyObservers();
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

		this.futureMaze = this.pool.submit(new Callable<Maze3dSearchable<Position>>() {

			@Override
			public Maze3dSearchable<Position> call() throws Exception {
				try {
					if (dimensions > 1) {
						if (!mazeMap.containsKey(mazeName)) {
							Maze3d currentMaze = new MyMaze3dGenerator().generate(dimensions, rows, columns);
							mazeMap.put(mazeName, currentMaze);
							updateData = (("maze" + " " + mazeName + " " + "is ready" + "\n").split("\b"));
							ObjectOutputStream out = new ObjectOutputStream(
									new GZIPOutputStream(new FileOutputStream(new File("maze3d" + ".zip"))));
							out.writeObject(mazeMap);
							out.close();
							setChanged();
							notifyObservers(currentMaze);
						}
						else {
							throw new RuntimeException("the maze is exist in database");
						}

					}

					else {
						throw new RuntimeException(
								"The dimensions of the maze : " + mazeName + " " + " have to be bigger than 1 ...");

					}
				}
				catch (Exception e) {
					//updateDataData = mazeMap.containsKey(mazeName).
					updateData = (e.toString().split("\b"));
					setChanged();
					notifyObservers(mazeMap.get(mazeName));
				}
				pool.submit(new Runnable() {
					
					@Override
					public void run() {
						try {
							futureMaze.get();
						}
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				return null;
				
			}

		});
	}

	/*
	 * new Thread(new Runnable() {
	 * 
	 * @Override public void run() { try { if (!mazeMap.containsKey(mazeName)) {
	 * Maze3d currentMaze = new MyMaze3dGenerator().generate(dimensions, rows,
	 * columns); mazeMap.put(mazeName, currentMaze); updateDataData = (("maze" + " "
	 * + mazeName + " " + "is ready" + "\n").split("\b")); setChanged();
	 * notifyObservers(); } else { throw new RuntimeException(
	 * "the maze is exist in database"); } } catch (Exception e) { updateDataData
	 * =(e.toString().split("\b")); setChanged(); notifyObservers(); } }
	 * }).start();; }
	 */

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
				updateData = (("*Start Position* :" + mazeMap.get(mazeName).getStartPosition().toString() + "\n"
						+ "*Goal Position*  :" + mazeMap.get(mazeName).getGoalPosition().toString() + "\n"
						+ mazeMap.get(mazeName).toString()).split("\b"));
				setChanged();
				notifyObservers();
			}
			else {
				throw new RuntimeException("The maze is not exist in dataBase");

			}
		}

		catch (Exception e) {
			updateData = (e.toString().split("\b"));
			setChanged();
			notifyObservers();
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

				if (columns.lookingAt()) {
					updateData = (mazeMap.get(mazeName).CrossToString(mazeMap.get(mazeName).getCrossSectionByX(index))
							.toString().split("\b"));
					setChanged();
					notifyObservers();
				}
				if (dimension.lookingAt()) {
					updateData = (mazeMap.get(mazeName).CrossToString(mazeMap.get(mazeName).getCrossSectionByY(index))
							.toString().split("\b"));
					setChanged();
					notifyObservers();
				}
				if (rows.lookingAt()) {
					updateData = (mazeMap.get(mazeName).CrossToString(mazeMap.get(mazeName).getCrossSectionByZ(index))
							.toString().split("\b"));
					setChanged();
					notifyObservers();
				}

			}
			else {
				throw new RuntimeException("the index is outside the scope of the maze");

			}
		}
		catch (Exception e) {
			updateData = (((e.toString() + "\n" + "the index is outside the scope of the maze"
					+ "the sizes of the crosses are :" + "\n" + "X:" + mazeMap.get(mazeName).getColumn() + "\n" + "Y:"
					+ mazeMap.get(mazeName).getDimension() + "\n" + "Z:" + mazeMap.get(mazeName).getRow()).toString())
					.split("\b"));
			setChanged();
			notifyObservers();

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
			updateData = (("the maze: " + mazeName + " " + "is saved successfully in file: " + fileName).split("\b"));
			setChanged();
			notifyObservers();
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
		byte b[] = new byte[(int) Files.size(new File(fileName + ".maze").toPath()) + 34];
		in.read(b);
		in.close();
		mazeMap.put(mazeName, new Maze3d(b));
		if (mazeMap.containsKey(mazeName)) {
			updateData = (("The maze: " + mazeName + " " + "loaded successfully").split("\b"));
			setChanged();
			notifyObservers(mazeMap.get(mazeName));
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
			updateData = (("" + mazeMap.get(mazeName).getDimension() * mazeMap.get(mazeName).getRow()
					* mazeMap.get(mazeName).getColumn() * 4 + 12 + 12 + 12 + " " + "bit").split("\b"));
			setChanged();
			notifyObservers();
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
			updateData = (("" + Files.size(new File(mazeName + ".maze").toPath()) + " " + "bit").split("\b"));
			setChanged();
			notifyObservers();
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
		this.futureMaze = pool.submit(new Callable<Maze3dSearchable<Position>>() {

			@Override
			public Maze3dSearchable<Position> call() throws Exception {
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
				if (solutionMap.containsKey(mazeName)) {
					updateData = (("The maze: " + mazeName + " " + "is allrady solve").split("\b"));
					setChanged();
					notifyObservers();

				}
				else {
					if (!solutionMap.containsKey(mazeName)) {
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

								ObjectOutputStream out = new ObjectOutputStream(
										new GZIPOutputStream(new FileOutputStream(new File("solution" + ".zip"))));
								out.writeObject(solutionMap);

								System.out.println("good");
								out.flush();
								out.close();

								updateData = (("solution for: " + mazeName + " " + "is ready").split("\b"));
								setChanged();
								notifyObservers(solution);
							}

						}
						else {
							updateData = (("The maze: " + mazeName + " "
									+ "is not exist in database please check the file name").split("\b"));
							setChanged();
							notifyObservers(solutionMap.get(mazeName));
						}
					}

					else {
						updateData = (("The maze: " + mazeName + " " + "is allrady solve").split("\b"));
						setChanged();
						notifyObservers(solution);

					}
				}

				return futureMaze.get();
			}

		});
	}

	/*
	 * new Thread(new Runnable() { public void run() { Maze3d maze =
	 * mazeMap.get(mazeName); Searchable<Position> searchableMaze = new
	 * Maze3dSearchable<>(maze); Searcher<Position> algorithem;
	 * ArrayList<State<Position>> solution = new ArrayList<State<Position>>();
	 * Pattern BFS = Pattern.compile("[BFSbfs]"); Pattern ManhattanDistance =
	 * Pattern.compile("[MANHATTANDISTANCEmanhattandistance]"); Pattern
	 * AirDistance = Pattern.compile("[AIRDISTANCEairdistance]"); Matcher m1 =
	 * BFS.matcher(algorithmName); Matcher m2 =
	 * ManhattanDistance.matcher(algorithmName); Matcher m3 =
	 * AirDistance.matcher(algorithmName);
	 * 
	 * if (mazeMap.containsKey(mazeName)) { if (m1.lookingAt()) { algorithem =
	 * new BFS<Position>(); solution =
	 * algorithem.search(searchableMaze).getSol();
	 * 
	 * }
	 * 
	 * if (m2.lookingAt()) { algorithem = new AStar<Position>(new
	 * ManhattanDistance()); solution =
	 * algorithem.search(searchableMaze).getSol();
	 * 
	 * }
	 * 
	 * if (m3.lookingAt()) { algorithem = new AStar<Position>(new
	 * AirDistance()); solution = algorithem.search(searchableMaze).getSol();
	 * 
	 * }
	 * 
	 * if (!solution.isEmpty()) { solutionMap.put(mazeName, solution);
	 * updateDataData =(("solution for: " + mazeName + "is ready").split("\b"));
	 * setChanged(); notifyObservers(); }
	 * 
	 * } else { updateDataData frown emoticon ("The maze: " + mazeName + " " +
	 * "is not exist in database please check the file name") .split("\b"));
	 * setChanged(); notifyObservers(); }
	 * 
	 * } }).start();;
	 * 
	 * }
	 */

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

				updateData = (sol);
				setChanged();
				notifyObservers();

			}
			else {
				updateData = (("the maze: " + mazeName + " is not not have solution").split("\b"));
				setChanged();
				notifyObservers();
			}
		}
		else {
			updateData = (("the maze: " + mazeName + " is not exist").split("\b"));

		}

	}

}