package view;


import java.awt.Paint;
import java.io.DataInputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MazeWindow extends BasicWindow {

	MazeDisplayer maze;
	public MazeDisplayer getMaze() {
		return maze;
	}

	Timer timer;
	TimerTask task;
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
		
	}

	public void paintConsole(){
		
		maze.redraw();
	}
	private void drawRectangles(Event e) {

        GC gc = e.gc;

        
        Color c1 = new Color(e.display, 50, 50, 200);
        gc.setBackground(c1);
        gc.fillRectangle(5, 5, 90, 90);

        Color c2 = new Color(e.display, 105, 90, 60);
        gc.setBackground(c2);
        gc.fillRectangle(130, 15, 90, 60);

        Color c3 = new Color(e.display, 33, 200, 100);
        gc.setBackground(c3);
        gc.fillRectangle(250, 15, 90, 60);

        c1.dispose();
        c2.dispose();
        c3.dispose();
    }

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		shell.setText("Game Window");

		Button paintButton = new Button(shell, SWT.BORDER);
		paintButton.setText("tamir");
		paintButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		paintButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				display.addListener(SWT.Paint,event -> drawRectangles(event));
				
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
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
				Shell generateshell=new Shell(shell, SWT.TITLE|SWT.SYSTEM_MODAL| SWT.CLOSE | SWT.MAX);
				generateshell.setLayout(new GridLayout(2,false));
				generateshell.setSize(500, 200);
				
				
				generateshell.setText("mazE Generate Window");
				generateshell.setLayout(new GridLayout(2, false));

				

				
				Group dialogFieldsGroup = new Group(generateshell, SWT.SHADOW_ETCHED_IN);
				dialogFieldsGroup.setText("Maze Generate Window" + " Properties");
				dialogFieldsGroup.setLayout(new GridLayout(2, true));
				dialogFieldsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				
				Label nameLabel=new Label(dialogFieldsGroup, SWT.NONE);
				nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
				nameLabel.setText("maze name :");
				Text nameText=new Text(dialogFieldsGroup, SWT.None);
				nameText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));
				
				Label dimensionLabel=new Label(dialogFieldsGroup, SWT.NONE);
				dimensionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
				dimensionLabel.setText("dimension :");
				Text heightText=new Text(dialogFieldsGroup, SWT.None);
				heightText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));
				
				Label rowLabel=new Label(dialogFieldsGroup, SWT.NONE);
				rowLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
				rowLabel.setText("rows :");
				Text rowText=new Text(dialogFieldsGroup, SWT.None);
				rowText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));
				
				Label columnLabel=new Label(dialogFieldsGroup, SWT.NONE);
				columnLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
				columnLabel.setText("column :");
				Text columnText=new Text(dialogFieldsGroup, SWT.None);
				columnText.setLayoutData(new GridData(SWT.NONE, SWT.TOP, false, true, 1, 1));
				
				Button generateButton=new Button(dialogFieldsGroup, SWT.PUSH);
				generateButton.setText("Generate");
				generateButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
                generateButton.addSelectionListener(new SelectionListener() {
					
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						String[] generateline={"generate","3d","maze",nameText.getText(), heightText.getText(), columnText.getText(), rowText.getText()};
						String[] regex = ("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}").split("\b");
						commandsList.add(regex);
						commandsList.add(generateline);
						setChanged();
						notifyObservers(maze);
						timer=new Timer();
						task=new TimerTask() {
							@Override
							public void run() {
								display.syncExec(new Runnable() {
									@Override
									public void run() {
										paintConsole();
										
										
									}
								});
							}
						};				
						timer.scheduleAtFixedRate(task, 0, 100);
					
						
						generateshell.close();
			}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				generateshell.setSize (300, 200);
				generateshell.open ();
				
			
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		
		shell.setSize(800, 600);
		shell.open();

		/*
		 * final Image image = new Image (display, 20, 20); Color color =
		 * display.getSystemColor (SWT.COLOR_RED); GC gc = new GC (image);
		 * gc.setBackground (color); gc.fillRectangle (image.getBounds ());
		 * gc.dispose ();
		 * 
		 * 
		 * shell.setLayout (new FillLayout ()); Group group = new Group (shell,
		 * SWT.NONE); group.setLayout (new FillLayout ()); group.setText (
		 * "a square"); Canvas canvas = new Canvas (group, SWT.NONE);
		 * canvas.addPaintListener (new PaintListener () {
		 * 
		 * @Override public void paintControl (PaintEvent e) { e.gc.drawImage
		 * (image, 50, 50); } });
		 * 
		 * shell.pack (); shell.open (); while (!shell.isDisposed ()) { if
		 * (!display.readAndDispatch ()) display.sleep (); } image.dispose ();
		 * display.dispose ();
		 */
	}





}
