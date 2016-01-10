package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Model;
import UserCommands.DirPath;
import UserCommands.DisplayCrossSection;
import UserCommands.DisplayMaze;
import UserCommands.DisplaySolustion;
import UserCommands.FileSize;
import UserCommands.Generat3dMaze;
import UserCommands.ICommand;
import UserCommands.LoadeMaze;
import UserCommands.MazeSize;
import UserCommands.SaveMaze;
import UserCommands.SolveMaze;
import view.View;


public abstract class MyAbstractController implements Controller {

	/**
	 * Data member model
	 */
	protected Model model;

	/**
	 * Data member view
	 */
	protected View view;

	/**
	 * Data member HashMap commands
	 */
	protected HashMap<String, ICommand> Commands;

	/**
	 * Data member pool
	 */
	protected ExecutorService pool;

	/**
	 * Constructor
	 */
	public MyAbstractController() {
		this.pool = Executors.newCachedThreadPool();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#setHashMap()
	 */
	// to initialize hash map(every name match to command)
	@Override
	public HashMap<String, ICommand> setHashMap() {
		this.Commands = new HashMap<String, ICommand>();
		Commands.put("dir [^ \n]+", new DirPath(view, model));
		Commands.put("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}", new Generat3dMaze(view, model));
		Commands.put("display [^ \n]+", new DisplayMaze(view, model));
		Commands.put("display cross section by [XYZxyz] [0-9]{1,2} for [A-Za-z0-9]+",
				new DisplayCrossSection(view, model));
		Commands.put("save maze [A-Za-z0-9]+ [^ \n]+", new SaveMaze(view, model));
		Commands.put("load maze [^ \n]+ [A-Za-z0-9]+", new LoadeMaze(view, model));
		Commands.put("maze size [A-Za-z0-9]+", new MazeSize(view, model));
		Commands.put("file size [A-Za-z0-9]+", new FileSize(view, model));
		Commands.put("solve [A-Za-z0-9]+ [A-Za-z0-9]+", new SolveMaze(view, model));
		Commands.put("display solution [A-Za-z0-9]+", new DisplaySolustion(view, model));
		Commands.put("help", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				help();

			}
		});
		return Commands;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#setModel(Model.Model)
	 */
	@Override
	public void setModel(Model m) {
		this.model = m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#setView(view.View)
	 */
	@Override
	public void setView(View v) {
		this.view = v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#getCommand()
	 */
	@Override
	public HashMap<String, ICommand> getCommand() {

		return this.Commands;
	}

	/**
	 * @param pool
	 */
	public void setPool(ExecutorService pool) {
		this.pool = pool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#viewMsg(java.lang.String[])
	 */
	@Override
	abstract public void viewMsg(String[] msg) throws IOException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#modelSolution(java.lang.String[])
	 */
	@Override
	abstract public void modelSolution(String[] s);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#setCommand(java.lang.String)
	 */
	@Override
	abstract public void setCommand(String command);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#exit()
	 */
	@Override
	abstract public void exit();

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#runThread(java.lang.Runnable)
	 */
	@Override
	abstract public void runThread(Runnable run);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controller.Controller#help()
	 */
	@Override
	abstract public void help();

}
