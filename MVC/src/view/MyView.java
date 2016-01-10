package view;

import java.io.IOException;

import Controller.Controller;

public class MyView extends MyAbstractView {

	/**
	 * @param c
	 */
	public MyView(Controller c) {
		super(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MyAbstractView#sendMsg(java.lang.String[])
	 */
	// send message to the controller
	@Override
	public void sendMsg(String[] s) throws IOException {
		String[] msg = new String[s.length];
		msg = s;
		controller.viewMsg(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MyAbstractView#sendDisplySolution(java.lang.String[])
	 */
	// send our solution to the client
	@Override
	public void sendDisplySolution(String[] solution) {
		client.displySolution(solution);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MyAbstractView#sendCommand(java.lang.String)
	 */
	// send command to controller
	@Override
	public void sendCommand(String commandRegex) {
		controller.setCommand(commandRegex);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MyAbstractView#shutdown()
	 */
	// calling for controller to shut down the program (EXIT)
	@Override
	public void shutdown() {
		controller.exit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.MyAbstractView#startRunable(java.lang.Runnable)
	 */
	// run controller with thread
	@Override
	public void startRunable(Runnable run) {
		controller.runThread(run);
	}

}
