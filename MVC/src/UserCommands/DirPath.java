package UserCommands;

import Model.Model;
import view.View;

public class DirPath extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public DirPath(View v, Model m) {
		super(v, m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) {
		model.getDirPath(msg[1]);

	}

}
