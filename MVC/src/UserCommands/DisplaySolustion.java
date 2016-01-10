package UserCommands;

import Model.Model;
import view.View;

public class DisplaySolustion extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public DisplaySolustion(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) {
		String mazeName = msg[2];
		model.getDisplaySolution(mazeName);

	}

}
