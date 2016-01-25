package boot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import org.eclipse.swt.SWT;

import model.MyModel;
import presenter.ClientProperties;
import presenter.MyPresenter;
import presenter.PropertiesHandler;
import view.CLI;
import view.Maze2D;
import view.MazeDisplayAdapter;
import view.MazeWindow;
import view.MyView;

public class RunClient {

	public static void main(String[] args) {
		try {
			
				ClientProperties properties;
				try {
					properties = PropertiesHandler.getInstance();
				} catch (FileNotFoundException e) {
					System.out.println("Could not find properties file, using default set");
					properties = new ClientProperties();
					try {
						PropertiesHandler.write(properties, "ClientProperties.xml");
					} catch (FileNotFoundException e1) {
						System.out.println("Could not save default properties file, please check manually");
					}
				}
				
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
			MazeDisplayAdapter painter = new MazeDisplayAdapter( new Maze2D(gui.getShell(), SWT.BORDER | SWT.DOUBLE_BUFFERED));
			gui.setMazePainter(painter.getMazeDisplayer());
			view.setMazeDisplayAdapter(painter);
			view.setBasicWindow(gui);
			painter.addObserver(view);
			gui.addObserver(view);
			client.addObserver(view);
			view.setClient(client);
			gui.run();
			//view.start();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	
		
	

	/*	MazeWindow run = new MazeWindow("mazeTest", 500, 300);
		run.run();*/
}
}
