package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Model;
import view.View;

public abstract class MyAbstractPresenter implements Presenter, Observer {

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
	protected HashMap<String, ICommand> CommandsMap;

	/**
	 * Data member pool
	 */
	protected ExecutorService pool;
	

	public MyAbstractPresenter(Model m, View v) {
		this.model = m;
		this.view = v;
		this.pool = Executors.newCachedThreadPool();
		setCommands();
	}

	public HashMap<String, ICommand> setCommands() {
		this.CommandsMap = new HashMap<String, ICommand>();
		CommandsMap.put("dir [^ \n]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				model.getDirPath(msg[1]);

			}
		});
		CommandsMap.put("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[3];
				int dim = Integer.parseInt(msg[4]);
				int row = Integer.parseInt(msg[5]);
				int col = Integer.parseInt(msg[6]);
				model.getGenrate3dMaze(mazeName, dim, row, col);
			}
		});
		CommandsMap.put("display [^ \n]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				model.getDisplayMaze(msg[1]);

			}
		});
		CommandsMap.put("display cross section by [XYZxyz] [0-9]{1,2} for [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String cross = msg[4];
				int index = Integer.parseInt(msg[5]);
				String mazeName = msg[7];
				model.getDisplayCrossSection(cross, index, mazeName);

			}
		});
		CommandsMap.put("save maze [A-Za-z0-9]+ [^ \n]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[2];
				String fileName = msg[3];
				model.getSaveMaze(mazeName, fileName);

			}
		});
		CommandsMap.put("load maze [^ \n]+ [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String fileName = msg[2];
				String mazeName = msg[3];
				model.getLoadeMaze(fileName, mazeName);

			}
		});
		CommandsMap.put("maze size [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[2];
				model.getMazeSize(mazeName);

			}
		});
		CommandsMap.put("file size [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[2];
				model.getFileSize(mazeName);

			}
		});
		CommandsMap.put("solve [A-Za-z0-9]+ [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[1];
				String algorithmName = msg[2];
				model.getSolveMaze(mazeName, algorithmName);

			}
		});
		CommandsMap.put("display solution [A-Za-z0-9]+", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				String mazeName = msg[2];
				model.getDisplaySolution(mazeName);

			}
		});
		CommandsMap.put("help", new ICommand() {

			@Override
			public void docommand(String[] msg) throws IOException {
				 help();

			}
		});
		return CommandsMap;
	}

	public HashMap<String, ICommand> getCommandsMap() {
		return CommandsMap;
	}


	@Override
	abstract public void update(Observable o, Object arg);
	
	
	public void help() {
		String s = new String();
		s += "dir <path>" + "\n";
		s += "generate 3d maze <name> <int> <int> <int>" + "\n";
		s += "display <name>" + "\n";
		s += "display  cross section by {X,Y,Z} <int> for <name>" + "\n";
		s += "save maze <name> <file name>" + "\n";
		s += "load maze <file name> <name>" + "\n";
		s += "maze size <name>" + "\n";
		s += "file size <name>" + "\n";
		s += "solve <name> {Air,Manhattan,Bfs}" + "\n";
		s += "display solution <name>" + "\n";
		s += "exit";
		view.sendDisplySolution(s.split("\b"));
	}
}
