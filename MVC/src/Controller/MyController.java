package Controller;

import java.io.IOException;

import UserCommands.ICommand;

public class MyController extends MyAbstractController {

	/**
	 * data member commandRegex
	 */
	protected String commandRegex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.MyAbstractController#viewMsg(java.lang.String[])
	 */
	// to get the message from view
	@Override
	public void viewMsg(String[] msg) throws IOException {
		ICommand usercommand = Commands.get(this.commandRegex);
		usercommand.docommand(msg);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.MyAbstractController#modelSolution(java.lang.String[])
	 */
	// to bring back the solution to view
	@Override
	public void modelSolution(String[] solution) {
		view.sendDisplySolution(solution);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.MyAbstractController#setCommand(java.lang.String)
	 */
	// to initialize data member commandRegex
	@Override
	public void setCommand(String commandName) {
		commandRegex = commandName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.MyAbstractController#runThread(java.lang.Runnable)
	 */
	@Override
	public void runThread(Runnable run) {
		pool.execute(run);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.MyAbstractController#exit()
	 */
	@Override
	public void exit() {
		view.sendDisplySolution(("exiting...").split("\b"));
		pool.shutdown();

	}

	public void help() {
		String s = new String();
		s += "dir <path>" + "\n";
		s += "generate 3d maze <name> <int> <int> <int>" + "\n";
		s += "display <name>" + "\n";
		s += "display  cross section by {X,Y,Z} <int> for <name>" + "\n";
		s += "save maze <name> <file name>" + "\n";
		s += "load maze <file name> <name>" + "\n";
		s += "maze size <name>" + "\n";
		s += "file size <name>" + "\n";
		s += "solve <name> {Air,Manhattan,Bfs}" + "\n";
		s += "display solution <name>" + "\n";
		s += "exit";
		view.sendDisplySolution(s.split("\b"));
	}

}
