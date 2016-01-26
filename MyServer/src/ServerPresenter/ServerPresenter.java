package ServerPresenter;

import java.util.HashMap;

public interface ServerPresenter {
	void exit();
	void help();
	public HashMap<String, ICommand> setCommands();

}
