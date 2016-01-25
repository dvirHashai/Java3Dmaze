package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class MyAbstractView extends Observable implements View, Observer {

	protected String commandRegex;
	protected ArrayList<Object> userCommand;
	protected MyServer client;
    protected MazeDisplayAdapter mazePainterAdapter;
	protected MazeWindow mazeWindow;
	protected MyClientHandler ch;
	public MyAbstractView() {
	}

	public void setBasicWindow(MazeWindow mazeWindow) {
		this.mazeWindow = mazeWindow;
	}
	public void setMazeDisplayAdapter(MazeDisplayAdapter mazePainterAdapter) {
		this.mazePainterAdapter = mazePainterAdapter;
	}

	/**
	 * start method
	 */
	public void start() {
		//this.client.start();
	}

	public void setUserCommand(ArrayList<Object> object) {
		
		this.userCommand = object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#getUserCommand(java.lang.String[])
	 */
	@Override
	public ArrayList<Object> getUserCommand() {

		return this.userCommand;
	}

	
	public void setClientHandler(MyClientHandler clientHandler){
		
		this.ch = clientHandler;
	}
	
	
	@Override
	public void setCommandRegex(String commandRegex) {
		this.commandRegex = commandRegex;

	}

	public String getCommandRegex() {
		return commandRegex;
	}

	/**
	 * @param client
	 */
	public void setClient(MyServer cli) {
		this.client = cli;
	}

	/**
	 * ShutDown method
	 */
	public void shutdown() {
	}

	/**
	 * @param run
	 */
	abstract public void startRunable(Runnable run);

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#sendDisplySolution(java.lang.String[])
	 */
	@Override
	abstract public void sendDisplySolution(String[] solution);

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		/*if (o == client) {
             //System.out.println(client.commandsList.get(0)[0]);
			setCommandRegex(client.commandsList.get(0)[0]);
			setUserCommand(client.commandsList.get(1));
			setChanged();
            notifyObservers();
		}*/
		if(o == ch){
			setCommandRegex(((ArrayList<Object>)arg).get(0).toString());
			setUserCommand(((ArrayList<Object>)arg));
			
			setChanged();
            notifyObservers();
		}
		if(o == mazeWindow){
		/*	setCommandRegex(mazeWindow.commandsList.get(0)[0]);
			setUserCommand(mazeWindow.commandsList.get(1));
			
			setChanged();
            notifyObservers();*/
		}
		if(o == mazePainterAdapter){
			System.out.println("aaaaa");
			
			mazePainterAdapter.paintMaze();
			
			
		}

	}
	

	@Override
	public void DisplySolution(Object arg,String string) {
/*		if (mazeDisplayer == null)
			mazeDisplayer = new Maze3D(mazeWindow.shell, SWT.BORDER);*/
		    //mazeDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		//mazePainterAdapter.setPainter(arg);
		ch.getResponse(arg,string);
		
		
	}

	@Override
	public void exit() {
		mazePainterAdapter.in = false;
		mazePainterAdapter.mazePainter.dispose();
		mazeWindow.shell.dispose();
		
	}
	
			
		
		//mazeWindow.paintConsole();
	}





