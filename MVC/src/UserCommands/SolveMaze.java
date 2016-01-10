package UserCommands;

import Model.Model;
import view.View;

public class SolveMaze extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public SolveMaze(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) {
		String mazeName = msg[1];
		String algorithmName = msg[2];
		model.getSolveMaze(mazeName, algorithmName);
	}

}
