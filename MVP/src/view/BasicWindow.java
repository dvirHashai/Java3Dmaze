package view;

import java.util.ArrayList;
import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable {

	/**
	 * Data Member display
	 */
	Display display;

	/**
	 * Data Member Shell
	 */
	Shell shell;

	ArrayList<String[]> commandsList;
	
	public BasicWindow(String title, int width, int height) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		this.commandsList = new ArrayList<>();
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



