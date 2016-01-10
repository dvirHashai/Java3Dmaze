package UserCommands;

import Model.Model;
import view.View;

public class Generat3dMaze extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public Generat3dMaze(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) {
		String mazeName = msg[3];
		int dim = Integer.parseInt(msg[4]);
		int row = Integer.parseInt(msg[5]);
		int col = Integer.parseInt(msg[6]);
		model.getGenrate3dMaze(mazeName, dim, row, col);

	}

}
