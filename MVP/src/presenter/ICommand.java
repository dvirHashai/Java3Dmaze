package presenter;

import java.io.IOException;

public interface ICommand {

	/**
	 * @param msg
	 * @throws IOException
	 */
	void docommand(String[] msg) throws IOException;

}
