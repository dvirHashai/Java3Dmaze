package UserCommands;

import Model.Model;
import view.View;

public class DisplayMaze extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public DisplayMaze(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) {
		model.getDisplayMaze(msg[1]);

	}

}
