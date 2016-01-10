package UserCommands;

import java.io.IOException;

import Model.Model;
import view.View;

public class SaveMaze extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public SaveMaze(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) throws IOException {
		String mazeName = msg[2];
		String fileName = msg[3];
		model.getSaveMaze(mazeName, fileName);

	}

}
