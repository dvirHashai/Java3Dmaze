package view;

import java.util.ArrayList;

public interface View {

	/**
	 * @param solution
	 */
	void sendDisplySolution(String[] solution);

	/**
	 * @param commandRegex
	 * @return
	 */
	ArrayList<Object> getUserCommand();
	void setUserCommand(ArrayList<Object> userCommnd);
	void setCommandRegex(String commandRegex);
	String getCommandRegex();

	//void DisplySolution(Object arg);

	void exit();

	void DisplySolution(Object arg,String string);

}
