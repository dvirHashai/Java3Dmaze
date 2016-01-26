package presenter;

import java.util.HashMap;

public interface Presenter {
	/**
	 * to exit clearly from all process
	 */
	void exit();

	/**
	 * to get the commands the we can use
	 */
	void help();
	
	/**
	 * @return 
	 * to set commands into the hash map
	 */
	public HashMap<String, ICommand> setCommands();

}
