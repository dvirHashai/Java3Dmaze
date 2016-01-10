package Controller;

import java.io.IOException;
import java.util.HashMap;

import Model.Model;
import UserCommands.ICommand;
import view.View;

public interface Controller {

	/**
	 * @param model
	 */
	void setModel(Model model);

	/**
	 * @param view
	 */
	void setView(View view);

	/**
	 * @return HashMap
	 */
	HashMap<String, ICommand> getCommand();

	/**
	 * @return HashMap
	 */
	HashMap<String, ICommand> setHashMap();

	/**
	 * @param msg
	 * @throws IOException
	 */
	void viewMsg(String[] msg) throws IOException;

	/**
	 * @param solution
	 */
	void modelSolution(String[] solution);

	/**
	 * @param command
	 */
	void setCommand(String command);

	/**
	 * @param run
	 */
	void runThread(Runnable run);

	/**
	 * 
	 */
	void exit();

	/**
	 * @throws IOException
	 */
	public void help();

}
