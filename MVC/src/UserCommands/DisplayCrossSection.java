package UserCommands;

import java.io.IOException;

import Model.Model;
import view.View;

public class DisplayCrossSection extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public DisplayCrossSection(View v, Model m) {
		super(v, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.UserCommand#docommand(java.lang.String[])
	 */
	@Override
	public void docommand(String[] msg) throws IOException {
		String cross = msg[4];
		int index = Integer.parseInt(msg[5]);
		String mazeName = msg[7];
		model.getDisplayCrossSection(cross, index, mazeName);
	}

}
