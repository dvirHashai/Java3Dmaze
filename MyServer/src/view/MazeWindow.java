package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MazeWindow extends BasicWindow {
	KeyListener keyListener;
	MazeDisplayAdapter mazePainterAdapter;
	MazeDisplayer mazePainter;
	GenerateWindow generatewindow;
	MouseWheelListener mouseZoomlListener;
	Clip music;
	Clip sound;
	MenuItem exit;
	String mazeName;
	int counter = 0;

	public MazeDisplayer getMaze() {
		return mazePainter;
	}

	Timer timer;
	TimerTask task;
	
	/*public MazeDisplayer getMazeDisplayer(){
		return mazePainter;
	}*/
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
		
	}
	public void setMazePainter(MazeDisplayer mazePainter){
		this.mazePainter = mazePainter;
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

		/*
		 * MenuItem properties = new MenuItem(subMenu, SWT.PUSH);
		 * properties.setText("Open Properties"); // Listener for load maze
		 * properties.addListener(SWT.Selection, new Listener() {
		 * 
		 * @Override public void handleEvent(Event arg0) { FileDialog fd = new
		 * FileDialog(shell, SWT.OPEN); fd.setText("Open Properties");
		 * fd.setFilterPath(""); String[] filterExt = { "*.txt", "*.java",
		 * "*.xml","*.maze", "*.*" }; fd.setFilterExtensions(filterExt); String
		 * selected = fd.open(); int counter = 0; char[] chen =
		 * selected.toCharArray(); for (int i = 0; i < chen.length; i++) {
		 * if("c".equals(chen[i])){ counter++; } System.out.println(); }
		 * 
		 * System.out.println(counter);
		 * 
		 * 
		 * 
		 * String[] regexLine = {"load maze [^ \n]+ [A-Za-z0-9]+"};
		 * commandsList.add(regexLine); //commandsList.add(selected);
		 * setChanged(); notifyObservers(); commandsList.clear();
		 * 
		 * } });
		 */

		// load maze button in the sub menu
		MenuItem LoadMaze = new MenuItem(subMenu, SWT.PUSH);
		LoadMaze.setText("LoadMaze\tCtrl+L");
		// Listener for load maze
		LoadMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});
		// Save maze button in the sub menu
		MenuItem SaveMaze = new MenuItem(subMenu, SWT.PUSH);
		SaveMaze.setText("SaveMaze\tCtrl+S");
		// Listener for save maze
		SaveMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				System.out.println("Select All");

			}
		});
		
		// exit maze button in the sub menu
		exit = new MenuItem(subMenu, SWT.PUSH);
		exit.setText("EXIT");
		// Listener for exit maze
		
		exit.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				commandsList.add("exit".split("\b"));
				commandsList.add("null".split("\b"));
				commandsList.add("null".split("\b"));
				setChanged();
				notifyObservers();
				mazePainterAdapter.mazePainter.closePaint = true;
				//mazePainter.getDisplay().getThread().;
				/*shell.getDisplay().close();
				shell.dispose();*/
				
				
			}
		});
		

		/*
		 * LoadMaze.setAccelerator(SWT.MOD1 + 'A');
		 * SaveMaze.setAccelerator(SWT.MOD1 + 'A');
		 */

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
						mazeName = generatewindow.nameText.getText();
						String[] regex = ("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}").split("\b");
						commandsList.add(regex);
						commandsList.add(generateline);
						mazePainterAdapter.in = true;
						setChanged();
						notifyObservers();
						mazePainterAdapter.in = false;
						commandsList.clear();
						generatewindow.generateshell.close();
						//mazeCanvas.mazePainter.redraw();
						mazePainterAdapter.mazePainter.setFocus();

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
		
		
		mazePainter.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		mazePainterAdapter =  new MazeDisplayAdapter(mazePainter);
		System.out.println("fffg");
		Button solve = new Button(shell, SWT.PUSH);
		solve.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1, 1));
		solve.setText("solve");
		solve.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] line = ("solve" + " " + mazeName + " " + "airdistance").split(" ");
				String[] regexSolve = { "solve [A-Za-z0-9]+ [A-Za-z0-9]+" };
				commandsList.add(regexSolve);
				commandsList.add(line);
				mazePainterAdapter.in = true;
				setChanged();
				notifyObservers();
				mazePainterAdapter.in = false;
		/*		timer = new Timer();
				task = new TimerTask() {
					@Override
					public void run() {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {

								if (mazePainterAdapter.mazePainter.curentPosition.equals(mazePainterAdapter.mazePainter.goalPosition)) {
									task.cancel();
								}

								if (!(mazePainterAdapter.mazePainter.solList.isEmpty()) && (mazePainterAdapter.mazePainter.solList.get(counter) != null)) {
									mazePainterAdapter.mazePainter.setFocus();
									mazePainterAdapter.mazePainter.curentPosition = mazePainterAdapter.mazePainter.solList.get(counter).getState();
									mazePainterAdapter.mazePainter.redraw();

								}

							}

						});
					}
				};

				timer.scheduleAtFixedRate(task, 0, 500);*/
			}
		});
		
		
		Button music = new Button(shell, SWT.PUSH);
		music.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1, 1));
		music.setText("music");
		music.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				playMusic(new File("mario.wav"));
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mouseZoomlListener = new MouseWheelListener() {

			@Override
			public void mouseScrolled(MouseEvent e) {
				// if both ctrl and wheel are being operated
				if ((e.stateMask & SWT.CTRL) != 0)
					mazePainterAdapter.mazePainter.setSize(mazePainterAdapter.mazePainter.getSize().x + e.count, mazePainterAdapter.mazePainter.getSize().y + e.count);

			}
		};
		shell.addMouseWheelListener(mouseZoomlListener);
		mazePainterAdapter.mazePainter.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP)
					mazePainterAdapter.mazePainter.moveCharacterUp();
				if (e.keyCode == SWT.ARROW_DOWN)
					mazePainterAdapter.mazePainter.moveCharacterDown();
				if (e.keyCode == SWT.ARROW_LEFT)
					mazePainterAdapter.mazePainter.moveCharacterLeft();
				if (e.keyCode == SWT.ARROW_RIGHT)
					mazePainterAdapter.mazePainter.moveCharacterRight();
				if (e.keyCode == SWT.PAGE_UP)
					mazePainterAdapter.mazePainter.moveCharacterUpFloor();
				if (e.keyCode == SWT.PAGE_DOWN)
					mazePainterAdapter.mazePainter.moveCharacterDownFloor();
			}
		});
		// mazePainter.setMaze(maze3d);
		shell.setSize(1300, 800);
		shell.open();
		
		//	TODO
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				
			}
		});
		shell.addKeyListener(new KeyListener() {
		
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CLOSE){
				commandsList.add("exit".split("\b"));
				commandsList.add("null".split("\b"));
				commandsList.add("null".split("\b"));
				setChanged();
				notifyObservers();
				mazePainterAdapter.mazePainter.closePaint = true;
				//mazePainter.getDisplay().getThread().;
				shell.dispose();
				//shell.getDisplay().dispose();
				}

			}
		});

	}
	private void playMusic(File file) {

		try {
			music = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			music.open(inputStream);
			// loop infinitely
			music.setLoopPoints(0, -1);
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void playSound(File file) {
		try {
			sound = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			sound.open(inputStream);
			sound.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





}
