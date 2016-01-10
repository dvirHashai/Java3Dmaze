package view;

import java.io.IOException;

public interface View {

	/**
	 * start method
	 */
	void start();

	/**
	 * @param client
	 */
	void setClient(CLI client);

	/**
	 * @param s
	 * @throws IOException
	 */
	void sendMsg(String[] s) throws IOException;

	/**
	 * @param solution
	 */
	void sendDisplySolution(String[] solution);

	/**
	 * @param commandRegex
	 */
	void sendCommand(String commandRegex);

	/**
	 * ShutDown method
	 */
	void shutdown();

	/**
	 * @param run
	 */
	void startRunable(Runnable run);

}
