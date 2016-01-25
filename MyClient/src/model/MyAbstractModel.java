package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
import algorithms.search.Maze3dSearchable;
import algorithms.search.State;
import presenter.ClientProperties;
import presenter.PropertiesHandler;

public abstract class MyAbstractModel extends Observable implements Model,Closeable {
	
	/**
	 * data member HashMap to match every maze with he's name
	 */
	protected HashMap<String, Maze3d> mazeMap;

	
	ArrayList<Object> commandLine;
	/**
	 * data member HashMap to match every maze with he's solution
	 */
	protected HashMap<String, ArrayList<State<Position>>> solutionMap;
	
	/*PrintWriter outToServer;=new PrintWriter(theServer.getOutputStream());
	outToServer.println("get image\n");
	outToServer.flush();
	
	BufferedReader in;=new BufferedReader(new InputStreamReader(theServer.getInputStream()));*/
	/**
	 * contain the data after change;
	 */
	Socket theServer;;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;
	
	String clientIp ;
	
	int port ;
	ClientProperties clientProperties;
	protected String[] updateData;
	protected ExecutorService pool;
	boolean close = false;
	protected Timer timer;
	protected TimerTask task;
	protected Future<Maze3dSearchable<Position>> futureMaze ;
	public MyAbstractModel() throws UnknownHostException, IOException {
		try {
			clientProperties = PropertiesHandler.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = clientProperties.getClientPort();
		this.clientIp = clientProperties.getClientIp();
		this.commandLine = new ArrayList<>();
		this.mazeMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, ArrayList<State<Position>>>();
		this.pool = Executors.newCachedThreadPool();
		
	}
	
	

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getDirPath(java.lang.String)
	 */
	@Override
	abstract public void getDirPath(String pathname);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getGenrate3dMaze(java.lang.String, int, int, int)
	 */
	@Override
	abstract public void getGenrate3dMaze(String mazeName, int dim, int row, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getDisplayMaze(java.lang.String)
	 */
	@Override
	abstract public void getDisplayMaze(String mazeName);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getDisplayCrossSection(java.lang.String, int,
	 * java.lang.String)
	 */
	@Override
	abstract public void getDisplayCrossSection(String cross, int index, String mazeName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getSaveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	abstract public void getSaveMaze(String mazeName, String fileName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getLodeMaze(java.lang.String, java.lang.String)
	 */
	@Override
	abstract public void getLoadeMaze(String fileName, String mazeName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getMazeSize(java.lang.String)
	 */
	@Override
	abstract public void getMazeSize(String mazeName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getFileSize(java.lang.String)
	 */
	@Override
	abstract public void getFileSize(String mazeName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getSolveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	abstract public void getSolveMaze(String mazeName) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Model.Model#getDisplaySolution(java.lang.String)
	 */
	@Override
	abstract public void getDisplaySolution(String mazeName);

	@Override
	public String[] getupdateData() {
		return this.updateData;
		
		
	}

	@Override
	public abstract void exit();

	@Override
	public abstract void sendState(ArrayList<State<Position>> solution);



	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
