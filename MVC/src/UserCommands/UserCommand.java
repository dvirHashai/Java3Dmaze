package UserCommands;

import java.io.IOException;

import Model.Model;
import view.View;

public abstract class UserCommand implements ICommand {

	/**
	 * data member model
	 */
	protected Model model;

	/**
	 * data member view
	 */
	protected View view;

	/**
	 * @param v
	 * @param m
	 */
	public UserCommand(View v, Model m) {
		model = m;
		view = v;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UserCommands.ICommand#docommand(java.lang.String[])
	 */
	@Override
	abstract public void docommand(String[] msg) throws IOException;

}
