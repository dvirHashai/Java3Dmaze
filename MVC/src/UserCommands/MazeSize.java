package UserCommands;

import java.io.IOException;

import Model.Model;
import view.View;

public class MazeSize extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public MazeSize(View v, Model m) {
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
		model.getMazeSize(mazeName);
	}

}
