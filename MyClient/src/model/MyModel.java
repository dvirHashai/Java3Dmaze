package model;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends MyAbstractModel {

	/**
	 * @throws IOException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             constructor for my model to read the solution zip and maze3d
	 *             zip
	 */
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
				mazeMap = (HashMap<String, Maze3d>) o;
			}

			in.close();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MyAbstractModel#getDirPath(java.lang.String)
	 */
	// to get the path of any file
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
	// to send the server all the parameters like the name,
	// dimensions,rows,columns.
	// when we get our input we send it with the mvp architecture and generating
	// the maze
	@Override
	public void getGenrate3dMaze(String mazeName, int dimensions, int rows, int columns) {

		System.out.println("Client Side");

		System.out.println("in");
		Maze3d currentMaze = null;

		try {
			theServer = new Socket(clientIp, port);
			System.out.println("connected to server!");

			PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
			outToServer.println("Get Generate\n");
			outToServer.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			System.out.println(in.readLine());// ok

			toServer = new ObjectOutputStream(theServer.getOutputStream());

			fromServer = new ObjectInputStream(theServer.getInputStream());

			commandLine.add("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}");
			commandLine.add(mazeName);
			commandLine.add(dimensions);
			commandLine.add(rows);
			commandLine.add(columns);
			System.out.println(commandLine.get(1).toString());
			toServer.writeObject(commandLine);
			toServer.flush();
			toServer.reset();
			
			commandLine.clear();
			try {

				currentMaze = (Maze3d) fromServer.readObject();

				savedName = mazeName;
				mazeMap.put(savedName, currentMaze);
				setChanged();
				notifyObservers(currentMaze);
				
				toServer.close();
				fromServer.close();
				theServer.close();
			} catch (EOFException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				updateData = (("*Start Position* :" + mazeMap.get(mazeName).getStartPosition().toString() + "\n"
						+ "*Goal Position*  :" + mazeMap.get(mazeName).getGoalPosition().toString() + "\n"
						+ mazeMap.get(mazeName).toString()).split("\b"));
				setChanged();
				notifyObservers();
			} else {
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

			} else {
				throw new RuntimeException("the index is outside the scope of the maze");

			}
		} catch (Exception e) {
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
		if (mazeMap.containsKey(savedName)) {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName /* + ".maze" */));
			out.write(mazeMap.get(savedName).toByteArray());
			out.flush();
			out.close();
			updateData = (("the maze: " + savedName + " " + "is saved successfully in file: " + fileName).split("\b"));
			setChanged();
			notifyObservers();
		} else {
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
		MyDecompressorInputStream in = new MyDecompressorInputStream(
				new FileInputStream(new File(fileName /* + ".maze" */)));
		byte b[] = new byte[(int) Files.size(new File(fileName/* + ".maze" */).toPath()) + 34];
		in.read(b);
		in.close();
		System.out.println(savedName);
		mazeMap.put(savedName, new Maze3d(b));
		if (mazeMap.containsKey(savedName)) {
			updateData = (("The maze: " + savedName + " " + "loaded successfully").split("\b"));
			setChanged();
			notifyObservers(mazeMap.get(savedName));

		} else {
			throw new RuntimeException(
					"The maze: " + savedName + " " + "is not exist in database please check the file name");
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
		} else {
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
		} catch (IOException e) {
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
	@SuppressWarnings("unchecked")
	// to solve our maze with BFS algorithm or Astar algorithm (with 2 heuristic
	// - ManhattanDistqance or AirDistance)
	// in this case we solve it with AirDistance because this is the default
	// solution that we made in the xml file
	@Override
	public void getSolveMaze(String mazeName) throws IOException {
		System.out.println("Client Side");

		System.out.println("in");
		ArrayList<State<Position>> sol = new ArrayList<>();

		try {
			theServer = new Socket(clientIp, port);
			System.out.println("connected to server!");

			PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
			outToServer.println("get solution\n");
			outToServer.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			System.out.println(in.readLine());// ok

			toServer = new ObjectOutputStream(theServer.getOutputStream());
			fromServer = new ObjectInputStream(theServer.getInputStream());

			commandLine.add("solve [A-Za-z0-9]+ [A-Za-z0-9]+");
			commandLine.add(mazeName);

			toServer.writeObject(commandLine);
			toServer.flush();
			commandLine.clear();

			try {
				sol = (ArrayList<State<Position>>) fromServer.readObject();
				for (State<Position> state : sol) {
					System.out.println(state.toString());
				}

				System.out.println("i got it");// ok
				sendState((ArrayList<State<Position>>) sol);
				toServer.close();
				fromServer.close();
				theServer.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MyAbstractModel#sendState(java.util.ArrayList)
	 */
	// the sendState method that send for us state of solution on by one from
	// the beginning to the end

	@Override
	public void sendState(ArrayList<State<Position>> solution) {
		timer = new Timer();
		task = new TimerTask() {
			int count = 0;

			@Override
			public void run() {
				if (solution.size() == count || close) {

					task.cancel();
					timer.cancel();

					System.out.println("2222");
					return;

				} else {
					State<Position> state = solution.get(count);
					count++;
					setChanged();
					notifyObservers(state);

				}
			}

		};

		timer.scheduleAtFixedRate(task, 0, 500);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MyAbstractModel#getDisplaySolution(java.lang.String)
	 */
	// to get all the solution and print him to the Command Line Interface
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

			} else {
				updateData = (("the maze: " + mazeName + " is not not have solution").split("\b"));
				setChanged();
				notifyObservers();
			}
		} else {
			updateData = (("the maze: " + mazeName + " is not exist").split("\b"));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MyAbstractModel#exit()
	 */
	// exit method to free all the open threads safely and to close our server
	// safely.
	@Override
	public void exit() {
		close = true;
		try {
			theServer = new Socket(clientIp, port);
			System.out.println("connected to the server and dispose operation");
			PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
			outToServer.println("Dispos Your Self\n");
			outToServer.flush();
			
			BufferedReader in=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			System.out.println(in.readLine());// ok
			toServer = new ObjectOutputStream(theServer.getOutputStream());
			fromServer = new ObjectInputStream(theServer.getInputStream());
			
			commandLine.add("exit");
			commandLine.add("null");
			
			toServer.writeObject(commandLine);
			toServer.flush();
			commandLine.clear();
			String line;
			while(( line = in.readLine()).equals("Your Command Is My Wish \n")){
				System.out.println(line);
				return;
			}
			System.out.println(in.readLine());
			toServer.close();
			fromServer.close();
			theServer.close();
			System.out.println("Client dispose Connection with the server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pool.shutdown();
			
		try {
			if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
				pool.shutdownNow();
			/*	if (!pool.awaitTermination(10, TimeUnit.SECONDS))
					setChanged();
					notifyObservers("Pool did not terminate");*/
			}
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			//Thread.currentThread().interrupt();
		}
	}

}