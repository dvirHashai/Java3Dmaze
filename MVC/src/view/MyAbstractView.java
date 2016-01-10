package view;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import Controller.Controller;
import algorithms.mazeGenerator.Maze3d;

public abstract class MyAbstractView implements View {

	/**
	 * data member controller
	 */
	protected Controller controller;

	/**
	 * data member CLI
	 */
	protected CLI client;

	/**
	 * data member HashMap to match maze with he's name
	 */
	protected ConcurrentHashMap<String, Maze3d> mazeMap;

	/**
	 * @param c
	 */
	// constructor
	public MyAbstractView(Controller c) {
		this.controller = c;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#setClient(view.CLI)
	 */
	// to inisialize the CLI data member
	public void setClient(CLI client) {
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#start()
	 */
	// to start the program by calling the start of the client
	@Override
	public void start() {
		client.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#sendCommand(java.lang.String)
	 */
	@Override
	abstract public void sendCommand(String commandRegex);

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#sendDisplySolution(java.lang.String[])
	 */
	@Override
	abstract public void sendDisplySolution(String[] solution);

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#shutdown()
	 */
	@Override
	abstract public void shutdown();

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#sendMsg(java.lang.String[])
	 */
	@Override
	abstract public void sendMsg(String[] s) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#startRunable(java.lang.Runnable)
	 */
	@Override
	abstract public void startRunable(Runnable run);

}
