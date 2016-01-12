package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.MyPresenter;
import view.CLI;
import view.MazeWindow;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		try {
			
			MyModel model = new MyModel();
			MyView view = new MyView();
			MyPresenter presenter = new MyPresenter(model, view);
			model.addObserver(presenter);
			view.addObserver(presenter);
			CLI client = new CLI
						(new BufferedReader
						(new InputStreamReader(System.in)),
						new PrintWriter(System.out, true),
						presenter.getCommandsMap(), view);
			MazeWindow gui = new MazeWindow("GameWindow", 500, 300);
			view.setBasicWindow(gui);
			gui.addObserver(view);
			
			gui.run();
			client.addObserver(view);
			view.setClient(client);
			//view.start();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	
		
	}

	/*	MazeWindow run = new MazeWindow("mazeTest", 500, 300);
		run.run();*/
}
