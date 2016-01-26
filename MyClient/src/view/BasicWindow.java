package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import presenter.ClientProperties;
import presenter.PropertiesHandler;

public abstract class BasicWindow extends Observable implements Runnable {

	/**
	 * Data Member display
	 */
	Display display;

	/**
	 * Data Member Shell
	 */
	Shell shell;

	ClientProperties clientProperties;
	
	ArrayList<String[]> commandsList;
	
	public BasicWindow(String title, int width, int height) {
		try {
			clientProperties = PropertiesHandler.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		this.commandsList = new ArrayList<>();
	}
	public Shell getShell(){
		return shell;
		
	}

	abstract void initWidgets();


	@Override
	public void run() {
		initWidgets();
		shell.open();

		while(!shell.isDisposed()){ 


			if(!display.readAndDispatch()){ 	
				display.sleep(); 			
			}

		} 

		display.dispose();
	}

}



