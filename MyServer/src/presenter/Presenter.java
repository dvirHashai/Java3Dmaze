package presenter;

import java.util.HashMap;

public interface Presenter {
	void exit();
	void help();
	public HashMap<String, ICommand> setCommands();

}
