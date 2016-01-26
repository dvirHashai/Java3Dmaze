package ServerView;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
import algorithms.search.State;
// TODO: Auto-generated Javadoc
/**
 * The Class MyClientHandler.
 */
public class MyClientHandler extends Observable implements ClientHandler, Serializable, Closeable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Boolean closeSocket = false;
	/** The out to client. */
	ObjectOutputStream ToClient;
	/** The in from client. */
	ObjectInputStream FromClient;
	String Ready;
	Thread thread;
	/** The pool. */
	@SuppressWarnings("unused")
	private ExecutorService pool;
	Boolean check = false;
	Boolean close = false;
	/** The memory. */
	HashMap<String, ArrayList<State<Position>>> solutionMap;
	Object response;
	/**
	 * Instantiates a new my client handler.
	 */
	public MyClientHandler() {
		solutionMap = new HashMap<>();
		pool = Executors.newFixedThreadPool(5);
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see view.ClientHandler#handleClient(java.io.InputStream,
	 * java.io.OutputStream)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleClient(InputStream in, OutputStream out) throws IOException, Exception {
		
		BufferedReader incomuniction = new BufferedReader(new InputStreamReader(in));
		System.out.println(incomuniction.readLine().toString());
		
		PrintWriter outToServer = new PrintWriter(out);
		outToServer.println("ok\n");
		outToServer.flush();
		
		FromClient = new ObjectInputStream(in);
		ToClient = new ObjectOutputStream(out);
		ArrayList<Object> sol;
		sol = (ArrayList<Object>) FromClient.readObject();
		System.out.println(sol.get(0).toString());
		closeSocket = false;
		setChanged();
		notifyObservers(sol);
		sol.clear();
  // while for send the response from server
		try {
			while(true) {
				if(response == null){
					System.out.println("tr");
				}
				while ((response != null) && (check)) {
					ToClient.writeObject(response);
					if(response instanceof ArrayList<?> ){
						for (State<Position> state : (ArrayList<State<Position>>)response) {
							System.out.println(state.getState().toString());
						}
					}
					else if(response instanceof Maze3d ){
						//System.out.println(((Maze3d)response).toString());
					}
					response = null;
					check = false;
					
					ToClient.flush();
					ToClient.reset();
					FromClient.close();
					ToClient.close();
					closeSocket=true;
					return;
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
		
	
	/* (non-Javadoc)
	 * @see view.ClientHandler#getResponse(java.lang.Object, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void getResponse(Object arg, String string) {
		response = arg;
		Ready = string;
		check = true;
		
		if(arg instanceof ArrayList<?> ){
			for (State<Position> state : (ArrayList<State<Position>>)response) {
				System.out.println(state.getState().toString());
			}
		}
		else if(arg instanceof Maze3d ){
			//System.out.println(((Maze3d)response).toString());
		}
		else if((arg != null) && (Ready == "Pool is terminate")){
			try {
				close = true;
				close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		FromClient.close();
		ToClient.close();
	}
	/* (non-Javadoc)
	 * @see view.ClientHandler#checkClose()
	 */
	@Override
	public Boolean checkClose() {
		return false;
	}
	/* (non-Javadoc)
	 * @see view.ClientHandler#getClose()
	 */
	public Boolean getClose() {
		return close;
	}
	public Boolean getCloseSocket() {
		return closeSocket;
	}
}