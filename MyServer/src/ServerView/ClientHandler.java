package ServerView;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	 *  get final product 
	 * @param arg
	 * @param string
	 * @throws IOException
	 */
	void getResponse(Object arg,String string) throws IOException;
	
	/**
	 * @return
	 */
	Boolean checkClose();
	/**
	 * @return
	 */
	public Boolean getClose() ;
	
}