package ServerPresenter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ServerModel.Model;
import ServerView.InterfaeServerView;
public abstract class MyAbstractPresenter implements ServerPresenter, Observer {
	/**
	 * Data member model
	 */
	protected Model model;
	/**
	 * Data member view
	 */
	protected InterfaeServerView view;
	/**
	 * Data member HashMap commands
	 */
	protected HashMap<String, ICommand> CommandsMap;
	/**
	 * Data member pool
	 */
	protected ExecutorService pool;
	
	public MyAbstractPresenter(Model m, InterfaeServerView v) {
		this.model = m;
		this.view = v;
		this.pool = Executors.newCachedThreadPool();
		setCommands();
	}
	public HashMap<String, ICommand> setCommands() {
		this.CommandsMap = new HashMap<String, ICommand>();
		CommandsMap.put("dir [^ \n]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				model.getDirPath((String) msg.get(1));
			}
		});
		CommandsMap.put("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(1);
				int dim =  (int) msg.get(2);
				int row = (int) msg.get(3);
				int col = (int) msg.get(4);
				model.getGenrate3dMaze(mazeName, dim, row, col);
			}
		});
		CommandsMap.put("display [^ \n]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				model.getDisplayMaze((String) msg.get(1));
			}
		});
		CommandsMap.put("display cross section by [XYZxyz] [0-9]{1,2} for [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String cross = (String) msg.get(4);
				int index = Integer.parseInt((String) msg.get(5));
				String mazeName = (String) msg.get(7);
				model.getDisplayCrossSection(cross, index, mazeName);
			}
		});
		CommandsMap.put("save maze [A-Za-z0-9]+ [^ \n]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(2);
				String fileName = (String) msg.get(3);
				model.getSaveMaze(mazeName, fileName);
			}
		});
		CommandsMap.put("load maze [^ \n]+ [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String fileName = (String) msg.get(2);
				String mazeName = (String) msg.get(3);
				model.getLoadeMaze(fileName, mazeName);
			}
		});
		CommandsMap.put("maze size [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(2);
				model.getMazeSize(mazeName);
			}
		});
		CommandsMap.put("file size [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(2);
				model.getFileSize(mazeName);
			}
		});
		CommandsMap.put("solve [A-Za-z0-9]+ [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(1);
				model.getSolveMaze(mazeName);
			}
		});
		CommandsMap.put("display solution [A-Za-z0-9]+", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				String mazeName = (String) msg.get(2);
				model.getDisplaySolution(mazeName);
			}
		});
		CommandsMap.put("help", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				 help();
			}
			
		});
		CommandsMap.put("exit", new ICommand() {
			@Override
			public void docommand(ArrayList<Object> msg) throws IOException {
				 exit();
			}
			
		});
		return CommandsMap;
	}
	public HashMap<String, ICommand> getCommandsMap() {
		return CommandsMap;
	}
	@Override
	abstract public void update(Observable o, Object arg);
	public void exit(){
		model.exit();
		//view.exit();
	}
	
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