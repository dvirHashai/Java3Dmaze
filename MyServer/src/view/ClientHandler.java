package view;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import algorithms.mazeGenerator.Position;
import algorithms.search.State;
// TODO: Auto-generated Javadoc
/**
 * The Interface ClientHandler.
 */
public interface ClientHandler {
	
	/**
	 * Handle client.
	 *
	 * @param inFromClient the in from client
	 * @param outToClient the out to client
	 * @throws IOException 
	 * @throws Exception 
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException, Exception;
	
	/**
	 * Gets the memory.
	 *
	 * @return the memory
	 */
	public HashMap<String, ArrayList<State<Position>>> getsolutionMap();
	
	/**
	 * Sets the memory.
	 *
	 * @param memory the memory
	 */
	public void setSolution( HashMap<String, ArrayList<State<Position>>> solution);
	
	void getResponse(Object arg,String string);
	
	Boolean checkClose();
}