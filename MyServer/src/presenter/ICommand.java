package presenter;

import java.io.IOException;
import java.util.ArrayList;

public interface ICommand {

	/**
	 * @param msg
	 * @throws IOException
	 */
	void docommand(ArrayList<Object> msg) throws IOException;

}
