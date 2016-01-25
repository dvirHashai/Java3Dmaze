package view;

public interface View {

	/**
	 * @param solution
	 */
	void sendDisplySolution(String[] solution);

	/**
	 * @param commandRegex
	 * @return
	 */
	String[] getUserCommand();
	void setUserCommand(String[] userCommnd);
	void setCommandRegex(String commandRegex);
	String getCommandRegex();

	void DisplySolution(Object arg);

	void exit();

}
