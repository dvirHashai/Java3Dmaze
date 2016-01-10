package UserCommands;

import java.io.IOException;

import Model.Model;
import view.View;

public class LoadeMaze extends UserCommand {

	/**
	 * @param v
	 * @param m
	 */
	public LoadeMaze(View v, Model m) {
		super(v, m);

	}

	@Override
	public void docommand(String[] msg) throws IOException {
		String fileName = msg[2];
		String mazeName = msg[3];
		model.getLoadeMaze(fileName, mazeName);

	}

}
