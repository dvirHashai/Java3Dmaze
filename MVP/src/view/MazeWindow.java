package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MazeWindow extends BasicWindow {
	KeyListener keyListener;
	MazeDisplayer mazePainter;
	GenerateWindow generatewindow;

	public MazeDisplayer getMaze() {
		return mazePainter;
	}

	Timer timer;
	TimerTask task;

	public MazeWindow(String title, int width, int height) {
		super(title, width, height);

	}

	public void paintConsole() {

		mazePainter.redraw();
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		shell.setText("Game Window");
		
		// Bar menu
		Menu menuButton = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuButton);

		// File button in the bar
		MenuItem fileItem = new MenuItem(menuButton, SWT.CASCADE);
		fileItem.setText("&File");

		// Drop down functions for file button
		Menu subMenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(subMenu);

		// load maze button in the sub menu
		MenuItem LoadMaze = new MenuItem(subMenu, SWT.PUSH);
		// Listener for load maze
		LoadMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});
		// Save maze button in the sub menu
		MenuItem SaveMaze = new MenuItem(subMenu, SWT.PUSH);
		// Listener for save maze
		SaveMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});
		LoadMaze.setText("LoadMaze\tCtrl+L");
		SaveMaze.setText("SaveMaze\tCtrl+S");
		LoadMaze.setAccelerator(SWT.MOD1 + 'A');
		SaveMaze.setAccelerator(SWT.MOD1 + 'A');
		// Help button in the bar
		MenuItem helpItem = new MenuItem(menuButton, SWT.CASCADE);
		helpItem.setText("&Help");
		// Drop down functions for help button
		Menu subMenu1 = new Menu(shell, SWT.DROP_DOWN);
		helpItem.setMenu(subMenu1);

		MenuItem item = new MenuItem(subMenu1, SWT.PUSH);
		item.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});
		MenuItem item1 = new MenuItem(subMenu, SWT.PUSH);
		item1.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});

		Button generateButton = new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1, 1));

		generateButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generatewindow = new GenerateWindow(shell);
				generatewindow.setTriggerOk(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						String[] generateline = { "generate", "3d", "maze", generatewindow.nameText.getText(),
								generatewindow.heightText.getText(), generatewindow.rowText.getText(),
								generatewindow.columnText.getText() };
						String[] regex = ("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}").split("\b");
						commandsList.add(regex);
						commandsList.add(generateline);
						setChanged();
						notifyObservers();
						commandsList.clear();
						generatewindow.generateshell.close();
						timer = new Timer();
						task = new TimerTask() {
							@Override
							public void run() {
								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										if (mazePainter.curentPosition.equals(mazePainter.goalPosition)){
											
											task.cancel();
											
										}
										else{
										mazePainter.redraw();
										mazePainter.setFocus();
										}
										//mazePainter.update();
									
									}
								});
							}
						};
						
						timer.scheduleAtFixedRate(task, 0, 1000);
				
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
		mazePainter = new Maze2D(shell, SWT.BORDER | SWT.DOUBLE_BUFFERED);
		mazePainter.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
	

		mazePainter.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP)
					mazePainter.moveCharacterUp();
				if (e.keyCode == SWT.ARROW_DOWN)
					mazePainter.moveCharacterDown();
				if (e.keyCode == SWT.ARROW_LEFT)
					mazePainter.moveCharacterLeft();
				if (e.keyCode == SWT.ARROW_RIGHT)
					mazePainter.moveCharacterRight();
				if (e.keyCode == SWT.PAGE_UP)
					mazePainter.moveCharacterUpFloor();
				if (e.keyCode == SWT.PAGE_DOWN)
					mazePainter.moveCharacterDownFloor();
			}
		});
		// mazePainter.setMaze(maze3d);
		shell.setSize(1300, 800);
		shell.open();

	
	}

}
